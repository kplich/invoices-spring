package kplich.invoices.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

public class InvoiceOutputDTO {
    private static final BigDecimal TAX_VALUE = new BigDecimal("0.23");

    private final Invoice invoice;
    private final Iterable<TransportOrder> orders;

    private final LocalDate paymentDate;
    private final BigDecimal nettoSum;
    private final BigDecimal taxSum;
    private final BigDecimal bruttoSum;

    public InvoiceOutputDTO(Invoice invoice, Iterable<TransportOrder> orders) {
        this.invoice = invoice;
        this.orders = orders;

        paymentDate = this.invoice.getSaleDate().plusDays(this.invoice.getPaymentDays());



        //TODO: value sums
        nettoSum = StreamSupport.stream(orders.spliterator(), false)
                           .map(TransportOrder::getValue)
                           .reduce(BigDecimal.ZERO, BigDecimal::add);

        taxSum = StreamSupport.stream(orders.spliterator(), false)
                         .map(TransportOrder::getValue)
                         .map(d -> d.multiply(TAX_VALUE, new MathContext(2)))
                         .reduce(BigDecimal.ZERO, BigDecimal::add);

        bruttoSum = nettoSum.add(taxSum);
    }

    public InvoiceOutputDTO(InvoiceInputDTO invoiceDTO) {
        this.invoice = invoiceDTO.getInvoice();
        this.orders = invoiceDTO.getOrders();

        paymentDate = this.invoice.getSaleDate().plusDays(this.invoice.getPaymentDays());

        //TODO: value sums
        nettoSum = BigDecimal.ZERO;
        taxSum = BigDecimal.ZERO;
        bruttoSum = BigDecimal.ZERO;
    }

    public Invoice getInvoice() {

        return invoice;
    }

    public Iterable<TransportOrder> getOrders() {

        return orders;
    }

    public LocalDate getPaymentDate() {

        return paymentDate;
    }

    public BigDecimal getNettoSum() {

        return nettoSum;
    }

    public BigDecimal getTaxSum() {

        return taxSum;
    }

    public BigDecimal getBruttoSum() {

        return bruttoSum;
    }
}
