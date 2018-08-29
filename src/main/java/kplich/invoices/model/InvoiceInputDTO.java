package kplich.invoices.model;

import java.util.List;

public class InvoiceInputDTO {
    private Invoice invoice;
    private List<TransportOrder> orders;

    public InvoiceInputDTO(Invoice invoice, List<TransportOrder> orders) {

        this.invoice = invoice;
        this.orders = orders;
    }

    public Invoice getInvoice() {

        return invoice;
    }

    public void setInvoice(Invoice invoice) {

        this.invoice = invoice;
    }

    public List<TransportOrder> getOrders() {

        return orders;
    }

    public void setOrders(List<TransportOrder> orders) {

        this.orders = orders;
    }
}
