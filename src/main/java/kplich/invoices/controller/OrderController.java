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

	@GetMapping(path = "/get")
	public String getByNumber(@RequestParam int number, Model model) {
		Optional<TransportOrder> result = service.getOrderRepository().findById(number);
		model.addAttribute("order", result.get());
		return "viewOrder";
	}

	@GetMapping(path = "/get/all")
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

	@GetMapping(path = "/add")
	public String addOrUpdate(Model model) {
	    model.addAttribute("newOrder", new TransportOrder());

		return "editOrder";
	}

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
	@ResponseBody
	public boolean delete(@RequestParam int number) {
		boolean result = false;

		Optional<TransportOrder> toBeDeleted = service.getOrderRepository().findById(number);

		if(toBeDeleted.isPresent()) {
			service.getOrderRepository().delete(toBeDeleted.get());
			result = true;
		}

		return result;
	}
}
