package kplich.invoices.service;

import kplich.invoices.model.TransportOrder;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;

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


}
