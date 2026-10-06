package com.tca.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.tca.model.Employee;
import com.tca.util.UrlUtils;

import reactor.core.publisher.Mono;

@Component
public class MyAppRunner implements ApplicationRunner 
{

	@Override
	public void run(ApplicationArguments args) throws Exception 
	{
/*	
		// Test Case - Display Employe for Given ID
		// Create a WebClient Object
		WebClient webClient = WebClient.create();
		
		Mono<Employee> employeeMono = webClient.get()
								  .uri(UrlUtils.GET_EMP_BY_ID_URL,101)
								  .retrieve()
								  .bodyToMono(Employee.class);
		
		//employeeMono.subscribe(e -> System.out.println(e));
		employeeMono.subscribe(System.out::println);
*/

//--------------------------------------------------------------------------
		
/*		
		//45:21
		// Test Case - Display All Employes 
		WebClient webClient = WebClient.create();
		Flux<Employee> employeeFlux = 	webClient.get()
										.uri(UrlUtils.GET_ALL_EMPLOYEES)
										.retrieve()
										.bodyToFlux(Employee.class);
		
		employeeFlux.subscribe(e->System.out.println(e));
*/		
		
		//55:22
		// Test Case - Store One Employee Information
		
		Employee emp = new Employee();
		emp.setEmpno(1105);
		emp.setEname("AAA");
		emp.setSal(10000);
		emp.setDeptno(111);
		
		WebClient webClient = WebClient.create();
		
		Mono<Employee> mono = webClient.post()
								.uri(UrlUtils.POST_NEW_EMPLOYEE)
								.contentType(MediaType.APPLICATION_JSON)   // 01:02:00 its by default even if we dont write it is thery, but we are providing in terms of xml then its needed
								.bodyValue(emp)
								.retrieve()
								.bodyToMono(Employee.class);
		
		mono.subscribe(e-> System.out.println(e));
		
		
		/*
		 * Note: All above cases are designed considering success
		 * On Success you get an Employee from server but on failure you get String message
		 * Listen from [01:12:00], it says take help of previous application
		 * and write try-catch for it
		 */
	}

}
