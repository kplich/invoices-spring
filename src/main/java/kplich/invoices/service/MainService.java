package kplich.invoices.service;

import kplich.invoices.model.Invoice;
import kplich.invoices.model.TransportOrder;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;

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

	public String viewOrders(Model model) {

		model.addAttribute("orders", orderRepository.findAll());
		model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}


}
