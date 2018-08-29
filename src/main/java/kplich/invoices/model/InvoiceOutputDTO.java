package kplich.invoices.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.stream.StreamSupport;

public class InvoiceOutputDTO {
    private final Invoice invoice;
    private final Iterable<TransportOrderDTO> orders;

    private final LocalDate paymentDate;
    private final BigDecimal nettoSum;
    private final BigDecimal taxSum;
    private final BigDecimal bruttoSum;

    public InvoiceOutputDTO(Invoice invoice, Iterable<TransportOrderDTO> orders) {
        this.invoice = invoice;
        this.orders = orders;

        paymentDate = this.invoice.getSaleDate().plusDays(this.invoice.getPaymentDays());

        nettoSum = StreamSupport.stream(orders.spliterator(), false)
                           .map(TransportOrderDTO::getOrder)
                           .map(TransportOrder::getValue)
                           .reduce(BigDecimal.ZERO, BigDecimal::add);

        taxSum = StreamSupport.stream(orders.spliterator(), false)
                         .map(TransportOrderDTO::getTaxValue)
                         .reduce(BigDecimal.ZERO, BigDecimal::add);

        bruttoSum = nettoSum.add(taxSum);
    }

    public Invoice getInvoice() {

        return invoice;
    }

    public Iterable<TransportOrderDTO> getOrders() {

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
