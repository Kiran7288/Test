package com.tca.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title="Employee REST Endpoints",
				version="1.0",
				description="REST API endpoints for CRUD Operations",
				contact = @Contact(name="support", email="support@tca.com"),
				license = @License(name="TCA", url="https://technocompacademy.com")
				),
		servers = @Server(description = "Local Server", url="http://localhost:8081")	
			
		)
public class AppConfig 
{

}
