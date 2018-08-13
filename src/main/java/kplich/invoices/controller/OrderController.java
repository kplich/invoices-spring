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

	@GetMapping(path = "/")
	public String getAll(Model model) {
	    model.addAttribute("orders", service.getOrderRepository().findAll());
	    model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}

	@GetMapping(path = "/edit")
    public String editOrder(@RequestParam int number, Model model) {
	    Optional<TransportOrder> shouldBePresent = service.getOrderRepository().findById(number);

	    if(shouldBePresent.isPresent()) {
	        model.addAttribute("order", shouldBePresent.get());
        }
        else {
            throw new IllegalArgumentException("There's no order with number " + number);
        }

        return "editOrder";
    }

	@GetMapping(path = "/get/invoice")
	@ResponseBody
	public Iterable<TransportOrder> getByInvoice(@RequestParam String invoiceId) {
		Optional<Invoice> invoice = service.getInvoiceRepository().findById(invoiceId);

		if(invoice.isPresent()) {
			return invoice.get().getOrders();
		}
		else {
			throw new IllegalArgumentException("There's no invoice with given ID.");
		}
	}

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
		model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}

	@DeleteMapping(path = "/delete")
	public String delete(@RequestParam int number, Model model) {
		Optional<TransportOrder> toBeDeleted = service.getOrderRepository().findById(number);

        toBeDeleted.ifPresent(transportOrder -> service.getOrderRepository().delete(transportOrder));

		model.addAttribute("orders", service.getOrderRepository().findAll());
		model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}
}
