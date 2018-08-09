package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
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

	@GetMapping(path = "/get/all")
	@ResponseBody
	public Iterable<TransportOrder> getAll() {
		return service.getOrderRepository().findAll();
	}

	@GetMapping(path = "/get/invoice")
	@ResponseBody
	public Iterable<TransportOrder> getByInvoice(@RequestParam String invoiceId) {
		Optional<Invoice> invoice = service.getInvoiceRepository().findById(invoiceId);

		if(invoice.isPresent()) {
			return service.getOrderRepository().findByInvoice(invoice.get());
		}
		else {
			throw new IllegalArgumentException("There's no invoice with given ID.");
		}
	}

	@GetMapping(path = "/add")
	public String addOrUpdate() {
		return "addOrder";
	}

	@PostMapping(path = "/add")
	public String addOrUpdate(@Valid @ModelAttribute TransportOrder order,
						      @RequestParam(required = false, defaultValue = "") String invoiceId,
							  Model model) {
		try {
			Optional<Invoice> invoice = service.getInvoiceRepository().findById(invoiceId);

			invoice.ifPresent(order::setInvoice); //woah, nice! ;)

			service.getOrderRepository().save(order);
		}
		catch (Exception e) {
			e.printStackTrace(); //TODO no to jak to logowac?
		}

		model.addAttribute("order", order);
		return "viewOrder";
	}

	@DeleteMapping(path = "/delete")
	@ResponseBody
	public boolean delete(@RequestParam int number) {
		boolean result = false;

		Optional<TransportOrder> toBeDeleted = service.getOrderRepository().findById(number);

		if(toBeDeleted.isPresent()) {
			service.getOrderRepository().delete(toBeDeleted.get());
			result = true;
		}

		return result;
	}
}
