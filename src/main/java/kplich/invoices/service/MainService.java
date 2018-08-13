package kplich.invoices.service;

import kplich.invoices.model.Invoice;
import kplich.invoices.model.TransportOrder;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
public class MainService {
	private InvoiceRepository invoiceRepository;
	private OrderRepository orderRepository;

	public MainService(InvoiceRepository invoiceRepository, OrderRepository orderRepository) {
		this.invoiceRepository = invoiceRepository;
		this.orderRepository = orderRepository;
	}

	public InvoiceRepository getInvoiceRepository() {
		return invoiceRepository;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void saveInvoice(Invoice invoice) {

	    String id = invoice.getInvoiceId();
        Optional<Invoice> shouldNotBePresent = invoiceRepository.findById(id);

        if(shouldNotBePresent.isPresent()) {
            throw new IllegalArgumentException("Invoice with given ID already exists!");
        }
        else {
            for (TransportOrder order : invoice.getOrders()) {
                order.setInvoice(invoice);
            }

            invoiceRepository.save(invoice);
        }
    }

    public void deleteOrder(int orderNumber) {

        Optional<TransportOrder> toBeDeleted = orderRepository.findById(orderNumber);

        if (toBeDeleted.isPresent()) {
            TransportOrder found = toBeDeleted.get();

            //if the order was in an invoice, delete it from its invoice first
            if (found.getInvoice() != null) {
                found.getInvoice().getOrders().remove(found);
            }

            //and then delete the order itself
            orderRepository.delete(found);
        }
        else {
            throw new IllegalArgumentException("There's no order with number " + orderNumber);
        }
    }


}
