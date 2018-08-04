package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/app")
public class MainController {
	private InvoiceRepository repository;

	public MainController(InvoiceRepository repository) {
		this.repository = repository;
	}

	@ResponseBody
	@GetMapping(path = "/hello")
	public String hello() {
		return "hello";
	}

	@ResponseBody
	@GetMapping(path = "/invoices/all")
	public Iterable<Invoice> getAllInvoices() {
		return repository.findAll();
	}
}
