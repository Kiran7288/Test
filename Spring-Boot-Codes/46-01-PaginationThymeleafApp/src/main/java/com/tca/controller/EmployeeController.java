package com.tca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String viewEmployees(@RequestParam(defaultValue="0") int pageNumber, Model model) // [30:40]
	{
		int pageSize = 5;
		Page<Employee> employeesPage = service.fetchEmployees(pageNumber,pageSize);
		model.addAttribute("employees", employeesPage.getContent());
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("hasPrevious",employeesPage.hasPrevious());
		model.addAttribute("hasNext",employeesPage.hasNext());
		return "view";
	}
	
}
