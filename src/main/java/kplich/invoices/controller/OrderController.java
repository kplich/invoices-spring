package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/orders")
public class OrderController {
	private OrderRepository repository;

	public OrderController(OrderRepository repository) {
		this.repository = repository;
	}

	@ResponseBody
	@RequestMapping("/all")
	public Iterable<TransportOrder> getAll() {
		return repository.findAll();
	}
}
