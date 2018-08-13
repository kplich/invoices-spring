package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/orders")
public class OrderController {
	private MainService service;

	public OrderController(MainService service) {
		this.service = service;
	}

	@GetMapping(path = "/get")
	public String getByNumber(@RequestParam int number, Model model) {
		Optional<TransportOrder> result = service.getOrderRepository().findById(number);
		model.addAttribute("order", result.get());
		return "viewOrder";
	}

	@GetMapping(path = "/")
	public String getAll(Model model) {
        return service.viewOrders(model);
	}

	@GetMapping(path = "/get/invoice")
	@ResponseBody
	public Iterable<TransportOrder> getByInvoice(@RequestParam String invoiceId) {
		Optional<Invoice> invoice = service.getInvoiceRepository().findById(invoiceId);

		if(invoice.isPresent()) {
			return invoice.get().getOrders();
		}
		else {
			throw new IllegalArgumentException("There's no invoice with given ID.");
		}
	}

	@PostMapping(path = "/add")
	public String addOrUpdate(@ModelAttribute TransportOrder order, Model model) {
		try {
			service.getOrderRepository().save(order);
		}
		catch (Exception e) {
			e.printStackTrace(); //TODO no to jak to logowac?
		}

        return service.viewOrders(model);
	}

	@GetMapping(path = "/delete")
	public String delete(@RequestParam int number, Model model) {
		Optional<TransportOrder> toBeDeleted = service.getOrderRepository().findById(number);

		toBeDeleted.ifPresent(transportOrder -> service.getOrderRepository().delete(transportOrder));

		return service.viewOrders(model);
	}


}
