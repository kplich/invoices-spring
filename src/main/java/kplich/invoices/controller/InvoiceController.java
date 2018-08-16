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
	public String getAll(Model model) {

	    model.addAttribute("invoices", service.getInvoiceRepository().findAll());

		return "viewInvoices";
	}

	@GetMapping(path = "/get")
    @ResponseBody
	public Optional<Invoice> getById(@RequestParam String id) {
		return service.getInvoiceRepository().findById(id);
	}

    @GetMapping(path = "/add")
    public String getForm(Model model) {

        model.addAttribute("unusedOrders", service.getInvoiceOrders(null));
        model.addAttribute("chosenOrders", new ChosenOrdersDTO(new ArrayList<>()));
        model.addAttribute("invoice", new Invoice());

        return "addInvoice";
    }

	@PostMapping(path = "/add")
	public String addInvoice(@ModelAttribute Invoice invoice,
                             BindingResult result,
                             @ModelAttribute ChosenOrdersDTO chosenOrders,
                             Model model) {

	    if(result.hasErrors())  {
	        for(ObjectError error: result.getAllErrors()) {
	            //TODO: better logging
                System.out.println(error.toString());
            }
        }
        else {
            service.saveInvoice(invoice, chosenOrders.getChosenOrders());
        }

        model.addAttribute("invoices", service.getInvoiceRepository().findAll());

        return "viewInvoices";
	}

	@DeleteMapping(path = "/delete")
	public void deleteById(@RequestParam String id) {
		service.deleteInvoice(id);
	}
}
