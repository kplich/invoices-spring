package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path = "/orders")
public class OrderController {
	private MainService service;

	public OrderController(MainService service) {
		this.service = service;
	}

	//TODO: delete this?
	/*@GetMapping(path = "/get")
	public String getByNumber(@RequestParam int number, Model model) {
		Optional<TransportOrder> result = service.getOrderRepository().findById(number);

		if(result.isPresent()) {
		    model.addAttribute("order", result.get());
        }
		model.addAttribute("order", result.get());
		return "viewOrder";
	}*/

	@GetMapping
	public String getAll(Model model) {

        model.addAttribute("orders", service.getOrderRepository().findAll());
        model.addAttribute("invoices", service.getInvoiceRepository().findAll());
        model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}

	@GetMapping(path = "/edit")
    public String editOrder(@RequestParam int number, Model model) {
	    Optional<TransportOrder> shouldBePresent = service.getOrderRepository().findById(number);

	    if(shouldBePresent.isPresent()) {
	        TransportOrder order = shouldBePresent.get(); //get the chosen order

	        service.getOrderRepository().delete(order); //delete it from the repository

	        order.setInvoice(null); //clear its invoice

	        model.addAttribute("order", order); //put it into the form
        }
        else {
            throw new IllegalArgumentException("There's no order with number " + number);
        }

        return "editOrder";
    }

    //TODO: delete this too?
	/*@GetMapping(path = "/get/invoice")
	@ResponseBody
	public Iterable<TransportOrder> getByInvoice(@RequestParam String invoiceId) {
		Optional<Invoice> invoice = service.getInvoiceRepository().findById(invoiceId);

		if(invoice.isPresent()) {
			return invoice.get().getOrders();
		}
		else {
			throw new IllegalArgumentException("There's no invoice with given ID.");
		}
	}*/

	//TODO: delete this too?
	/*@GetMapping(path = "/add")
	public String addOrUpdate(Model model) {
	    model.addAttribute("newOrder", new TransportOrder());

		return "editOrder";
	}*/

	@PostMapping(path = "/add")
	public String addOrUpdate(@ModelAttribute TransportOrder order, Model model) {
		try {
			service.getOrderRepository().save(order);
		}
		catch (Exception e) {
			e.printStackTrace(); //TODO no to jak to logowac?
		}

        model.addAttribute("orders", service.getOrderRepository().findAll());
        model.addAttribute("invoices", service.getInvoiceRepository().findAll());
        model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}

	@GetMapping(path = "/delete")
	public String delete(@RequestParam int number, Model model) {
		service.deleteOrder(number);

        model.addAttribute("orders", service.getOrderRepository().findAll());
        model.addAttribute("invoices", service.getInvoiceRepository().findAll());
        model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}
}
