package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/invoices")
public class InvoiceController {
	private InvoiceRepository repository;

	public InvoiceController(InvoiceRepository repository) {
		this.repository = repository;
	}

	@GetMapping (path = "/get/all")
	public Iterable<Invoice> getAll() {
		return repository.findAll();
	}

	@GetMapping(path = "/get")
	public Optional<Invoice> getById(@RequestParam String id) {
		return repository.findById(id);
	}

	//TODO: do we need the methods to return anything?
	@PutMapping(path = "/add")
	public boolean add(@ModelAttribute Invoice invoice) {
		boolean result = false;

		try {
			repository.save(invoice);
			result = true;
		}
		catch (Exception e) {
			e.printStackTrace(); //TODO: how about this?
		}

		return result;
	}

	@DeleteMapping(path = "/delete")
	public void deleteById(@RequestParam String id) {
		repository.deleteById(id);
	}
}
