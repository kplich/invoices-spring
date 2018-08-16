package kplich.invoices.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class InvoiceOutputDTO {
    private final Invoice invoice;
    private final List<TransportOrder> orders;

    private final LocalDate paymentDate;
    private final BigDecimal nettoSum;
    private final BigDecimal taxSum;
    private final BigDecimal bruttoSum;

    public InvoiceOutputDTO(Invoice invoice, List<TransportOrder> orders) {
        this.invoice = invoice;
        this.orders = orders;

        paymentDate = this.invoice.getSaleDate().plusDays(this.invoice.getPaymentDays());

        //TODO: value sums
        nettoSum = new BigDecimal(BigInteger.ZERO);
        taxSum = new BigDecimal(BigInteger.ZERO);
        bruttoSum = new BigDecimal(BigInteger.ZERO);
    }

    public InvoiceOutputDTO(InvoiceInputDTO invoiceDTO) {
        this.invoice = invoiceDTO.getInvoice();
        this.orders = invoiceDTO.getOrders();

        paymentDate = this.invoice.getSaleDate().plusDays(this.invoice.getPaymentDays());

        //TODO: value sums
        nettoSum = new BigDecimal(BigInteger.ZERO);
        taxSum = new BigDecimal(BigInteger.ZERO);
        bruttoSum = new BigDecimal(BigInteger.ZERO);
    }

    public Invoice getInvoice() {

        return invoice;
    }

    public List<TransportOrder> getOrders() {

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
