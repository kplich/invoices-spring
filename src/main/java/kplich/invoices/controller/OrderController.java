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

    @GetMapping
    public String viewAllOrders(Model model) {

        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("invoices", service.getAllOrders());
        model.addAttribute("newOrder", new TransportOrder());

        return "viewOrders";
    }

	@GetMapping(path = "/get")
	public String viewOrder(@RequestParam int number, Model model) {

		model.addAttribute("order", service.getOrder(number));

		return "viewOrder";
	}

    @PostMapping(path = "/add")
    public String addOrder(@ModelAttribute TransportOrder order, Model model) {

	    service.addOrder(order);

        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("invoices", service.getAllInvoices());
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

	@GetMapping(path = "/delete")
	public String deleteOrder(@RequestParam int number, Model model) {
		service.deleteOrder(number);

        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("invoices", service.getAllInvoices());
        model.addAttribute("newOrder", new TransportOrder());

		return "viewOrders";
	}
}
