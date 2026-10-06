package com.tca.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {
	
		
	@NotBlank		//@NonEmpty will accept spaces e.g "   " wheareas NotBlank will not allows
	private String username;
	
	@NotBlank
	@Email
	private String email;
	
	
	@Pattern(regexp="\\d{10}") // only 10 digits are allowed, no alphabet & special characters
	private String mobile;	// @Pattern 
	
	@NotBlank
	private String gender;
	
	@Past   // date of birth should not be past madhali
	private LocalDate dob;
}
