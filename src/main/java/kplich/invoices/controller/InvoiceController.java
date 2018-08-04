package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/invoices")
public class InvoiceController {
	private InvoiceRepository repository;

	public InvoiceController(InvoiceRepository repository) {
		this.repository = repository;
	}

	@ResponseBody
	@GetMapping (path = "/all")
	public Iterable<Invoice> getAllInvoices() {
		return repository.findAll();
	}
}
