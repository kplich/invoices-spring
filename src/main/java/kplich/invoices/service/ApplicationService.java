package kplich.invoices.service;

import kplich.invoices.model.Invoice;
import kplich.invoices.model.TransportOrder;
import kplich.invoices.model.TransportOrderDTO;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
	private InvoiceRepository invoiceRepository;
	private OrderRepository orderRepository;

	public ApplicationService(InvoiceRepository invoiceRepository, OrderRepository orderRepository) {
		this.invoiceRepository = invoiceRepository;
		this.orderRepository = orderRepository;
	}

	//----------------------GET ALL DATA---------------------------------------

	public Iterable<TransportOrder> getAllOrders() {
	    return orderRepository.findAll();
    }

    public Iterable<Invoice> getAllInvoices() {
	    return invoiceRepository.findAll();
    }

    //--------------------------ORDER METHODS----------------------------------

	public TransportOrder getOrder(int number) {
	    Optional<TransportOrder> result = orderRepository.findById(number);

	    if(!result.isPresent()) {
	        throw new IllegalArgumentException("There's no order with number " + number);
        }

        return result.get();
    }

    public Iterable<TransportOrder> getOrdersWithInvoice(Invoice invoice) {

        return orderRepository.findByInvoice(invoice);
    }

    public Iterable<TransportOrderDTO> getOrderDTOsWithInvoice(Invoice invoice) {

        ArrayList<TransportOrderDTO> orderDTOS = new ArrayList<>();

        for (TransportOrder order : orderRepository.findByInvoice(invoice)) {
            orderDTOS.add(new TransportOrderDTO(order));
        }

        return orderDTOS;
    }

    public void addOrder(TransportOrder order) {
        int orderNumber = order.getNumber();

        if(orderRepository.findById(orderNumber).isPresent()) {
            throw new IllegalArgumentException("Order with ID " + orderNumber + " already exists!");
        }

        orderRepository.save(order);
    }

    public void updateOrder(int oldNumber, TransportOrder order) {
        if(order.getNumber() == oldNumber) {
            orderRepository.save(order);
        }
        else {
            this.addOrder(order);
        }
    }

    public void deleteOrder(int orderNumber) {

        Optional<TransportOrder> toBeDeleted = orderRepository.findById(orderNumber);

        if (toBeDeleted.isPresent()) {
            TransportOrder found = toBeDeleted.get();
            orderRepository.delete(found);
        }
        else {
            throw new IllegalArgumentException("There's no order with number " + orderNumber);
        }
    }

    //----------------------INVOICE METHODS------------------------------------

    public Invoice getInvoice(String id) {
        Optional<Invoice> result = invoiceRepository.findById(id);

        if(!result.isPresent()) {
            throw new IllegalArgumentException("There's no invoice with ID " + id);
        }

        return result.get();
    }

    public void addInvoice(Invoice invoice, List<TransportOrder> orders) {

	    String id = invoice.getInvoiceId();
        Optional<Invoice> shouldNotBePresent = invoiceRepository.findById(id);

        if(shouldNotBePresent.isPresent()) {
            throw new IllegalArgumentException("Invoice with given ID already exists!");
        }
        else {
            invoiceRepository.save(invoice);


            for(TransportOrder order: orders) {
                order.setInvoice(invoice);
                orderRepository.save(order);
            }
        }
    }

    public void deleteInvoice(String id) {
	    Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

	    if(optionalInvoice.isPresent()) {
	        Invoice found = optionalInvoice.get();

	        Iterable<TransportOrder> orders = orderRepository.findByInvoice(found);

	        for(TransportOrder order: orders) {
	            order.setInvoice(null);
	            orderRepository.save(order);
            }

            invoiceRepository.delete(found);

        } else {
	        throw new IllegalArgumentException("Invoice with ID " + id + " doesn't exist.");
        }
    }
}
