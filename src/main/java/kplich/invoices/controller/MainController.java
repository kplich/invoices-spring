package kplich.invoices.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/app")
public class MainController {

	@ResponseBody
	@GetMapping(path = "/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping(path = "")
    public String getMainPage() {
	    return "mainPage";
    }
}
