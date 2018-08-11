package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.repository.*;
import kplich.invoices.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	@PostMapping(path = "/add")
	public void add(@ModelAttribute Invoice invoice, BindingResult result) {

	    if(result.hasErrors())  {
	        for(ObjectError error: result.getAllErrors()) {
	            //TODO: better logging
                System.out.println(error.toString());
            }
        }
        else {
            service.saveInvoice(invoice);
        }
	}

	@GetMapping(path = "/add")
	public String getForm(Model model) {
		model.addAttribute("unusedOrders", service.getOrderRepository().findByInvoice(null));
		model.addAttribute("invoice", new Invoice());

		return "addInvoice";
	}

	@DeleteMapping(path = "/delete")
	public void deleteById(@RequestParam String id) {
		service.getInvoiceRepository().deleteById(id);
	}
}
