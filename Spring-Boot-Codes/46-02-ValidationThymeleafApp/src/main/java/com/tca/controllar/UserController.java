package com.tca.controllar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tca.model.User;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@GetMapping( value = "/form" )
	public String getFormPage(Model model) {
		System.out.println("**************hello**********");
		User user = new User();   //form object
		model.addAttribute("user", user);
		return "User";  //logical view name
	}
	
	@PostMapping( value = "/register")
	public String handleForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) // @Valid validate 'user'parameter
	{																  // BindingResult is used to store validation result
		
		if(result.hasErrors())
		{
			return "User";
		}
		else
		{
			System.out.println(user);
		
			model.addAttribute("username",user.getUsername());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("mobile", user.getMobile());
			model.addAttribute("gender", user.getGender());
			model.addAttribute("dob", user.getDob());
			return "UserDetails";
		}
	}

}
