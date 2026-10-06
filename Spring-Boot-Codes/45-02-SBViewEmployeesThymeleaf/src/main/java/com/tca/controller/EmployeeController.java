package com.tca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tca.entity.Employee;
import com.tca.service.EmployeeService;

@Controller
public class EmployeeController 
{
	@Autowired
	EmployeeService service;
	
	@GetMapping(value="/index")
	public String getIndexPage()
	{
		return "index";
	}
	
	@GetMapping(value="/view")
	public String viewEmployees(Model model)
	{
		List<Employee> employees = service.fetchEmployees();
		model.addAttribute("employees", employees);
		return "view";
	}
	
}
