package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
	private OrderRepository repository;

	public OrderController(OrderRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/get")
	public Optional<TransportOrder> getByNumber(@RequestParam int number) {
		return repository.findById(number);
	}

	@GetMapping(path = "/get/all")
	public Iterable<TransportOrder> getAll() {
		return repository.findAll();
	}

	@PostMapping(path = "/add")
	public boolean addOrUpdate(@ModelAttribute TransportOrder order) {
		boolean result = false;

		try {
			repository.save(order);
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

		Optional<TransportOrder> toBeDeleted = repository.findById(number);

		if(toBeDeleted.isPresent()) {
			repository.delete(toBeDeleted.get());
			result = true;
		}

		return result;
	}
}
