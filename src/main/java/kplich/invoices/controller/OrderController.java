package kplich.invoices.controller;

import kplich.invoices.model.*;
import kplich.invoices.service.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/orders")
public class OrderController {
	private ApplicationService service;

	public OrderController(ApplicationService service) {
		this.service = service;
	}

    @GetMapping
    public String viewAllOrders(Model model) {

        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("invoices", service.getAllInvoices());
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

    @PostMapping(path = "/update")
    public String updateOrder(@ModelAttribute TransportOrder order,
                              @RequestParam int oldNumber,
                              Model model) {

	    service.updateOrder(oldNumber, order);

        model.addAttribute("orders", service.getAllOrders());
        model.addAttribute("invoices", service.getAllInvoices());
        model.addAttribute("newOrder", new TransportOrder());

        return "viewOrders";
    }

	@GetMapping(path = "/edit")
    public String editOrder(@RequestParam int number, Model model) {

	    model.addAttribute("order", service.getOrder(number));
        model.addAttribute("invoices", service.getAllInvoices());
        model.addAttribute("oldNumber", number);

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
