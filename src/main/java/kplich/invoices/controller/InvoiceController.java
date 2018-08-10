package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import kplich.invoices.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/invoices")
public class InvoiceController {
	private MainService service;

	public InvoiceController(MainService service) {
		this.service = service;
	}

	@GetMapping (path = "/get/all")
    @ResponseBody
	public Iterable<Invoice> getAll() {
		return service.getInvoiceRepository().findAll();
	}

	@GetMapping(path = "/get")
    @ResponseBody
	public Optional<Invoice> getById(@RequestParam String id) {
		return service.getInvoiceRepository().findById(id);
	}

	//TODO: do we need the methods to return anything?
	@PutMapping(path = "/add")
    @ResponseBody
	public boolean add(@ModelAttribute Invoice invoice) {
		boolean result = false;

		try {
			service.getInvoiceRepository().save(invoice);
			result = true;
		}
		catch (Exception e) {
			e.printStackTrace(); //TODO: how about this?
		}

		return result;
	}

	@DeleteMapping(path = "/delete")
	public void deleteById(@RequestParam String id) {
		service.getInvoiceRepository().deleteById(id);
	}
}
