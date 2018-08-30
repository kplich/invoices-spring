package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.service.*;
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

	@GetMapping
	public String viewAllInvoices(Model model) {
	    model.addAttribute("invoices", service.getAllInvoices());
        model.addAttribute("unusedOrders", service.getOrdersWithInvoice(null));
        model.addAttribute("clearInvoice", new InvoiceInputDTO(new Invoice(), new ArrayList<>()));

		return "viewInvoices";
	}

	@GetMapping(path = "/get")
	public String viewInvoice(@RequestParam String id, Model model) {

	    Invoice invoice = service.getInvoice(id);
	    InvoiceOutputDTO outputDTO = new InvoiceOutputDTO(invoice, service.getInvoiceDTOOrders(invoice));

		model.addAttribute("invoiceDTO", outputDTO);

		return "viewInvoice";
	}

	@PostMapping(path = "/add")
	public String addInvoice(@ModelAttribute InvoiceInputDTO invoiceDTO,
                             BindingResult result,
                             Model model) {

	    if(result.hasErrors())  {
	        for(ObjectError error: result.getAllErrors()) {
	            //TODO: better logging
                System.out.println(error.toString());
            }
        }
        else {
            service.saveInvoice(invoiceDTO.getInvoice(), invoiceDTO.getOrders());
        }

        model.addAttribute("invoices", service.getAllInvoices());
        model.addAttribute("unusedOrders", service.getOrdersWithInvoice(null));
        model.addAttribute("clearInvoice", new InvoiceInputDTO(new Invoice(), new ArrayList<>()));

        return "viewInvoices";
	}

	@GetMapping(path = "/delete")
	public String deleteInvoice(@RequestParam String id, Model model) {
		service.deleteInvoice(id);

        model.addAttribute("invoices", service.getAllInvoices());
        model.addAttribute("unusedOrders", service.getOrdersWithInvoice(null));
        model.addAttribute("clearInvoice", new InvoiceInputDTO(new Invoice(), new ArrayList<>()));

        return "viewInvoices";
	}
}
