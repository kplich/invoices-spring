package kplich.invoices.service;

import kplich.invoices.model.Invoice;
import kplich.invoices.model.TransportOrder;
import kplich.invoices.model.TransportOrderDTO;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
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

	public void saveInvoice(Invoice invoice, List<TransportOrder> orders) {

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

    /*public Iterable<TransportOrder> getInvoiceOrders(String invoiceId) {
	    Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);

	    if(invoice.isPresent()) {
	        return getInvoiceOrders(invoice.get());
        }
        else {
            throw new IllegalArgumentException("No invoice with ID " + invoiceId + " found.");
        }
    }*/

    public Iterable<TransportOrder> getInvoiceOrders(Invoice invoice) {
	   return orderRepository.findByInvoice(invoice);
    }

    public Iterable<TransportOrderDTO> getInvoiceDTOOrders(Invoice invoice) {

        ArrayList<TransportOrderDTO> orderDTOS = new ArrayList<>();

        for(TransportOrder order: orderRepository.findByInvoice(invoice)) {
            orderDTOS.add(new TransportOrderDTO(order));
        }

        return orderDTOS;
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

    public void injectData(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("invoices", invoiceRepository.findAll());
        model.addAttribute("newOrder", new TransportOrder());
    }


}
