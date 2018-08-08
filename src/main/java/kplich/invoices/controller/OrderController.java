package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	private MainService service;

	public OrderController(MainService service) {
		this.service = service;
	}

	@GetMapping(path = "/get")
	public Optional<TransportOrder> getByNumber(@RequestParam int number) {
		return service.getOrderRepository().findById(number);
	}

	@GetMapping(path = "/get/all")
	public Iterable<TransportOrder> getAll() {
		return service.getOrderRepository().findAll();
	}

	@PostMapping(path = "/add")
	public boolean addOrUpdate(@ModelAttribute TransportOrder order, @RequestParam(required = false, defaultValue = "") String invoiceId) {
		boolean result = false;

		try {
			service.getOrderRepository().save(order);
			result = true;
		}
		catch (Exception e) {
			e.printStackTrace(); //TODO no to jak to logowac?
		}

		return result;
	}

	@DeleteMapping(path = "/delete")
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
