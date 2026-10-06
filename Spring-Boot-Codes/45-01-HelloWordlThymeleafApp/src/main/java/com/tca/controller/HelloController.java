package com.tca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@GetMapping(value="/hello")
	public String sayHello(@RequestParam String username, Model model)
	{
		model.addAttribute("name", username);
		return "hello";
	}
}
