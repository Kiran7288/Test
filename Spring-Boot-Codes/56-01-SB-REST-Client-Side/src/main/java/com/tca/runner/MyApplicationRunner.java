package com.tca.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.tca.modal.Employee;
import com.tca.util.ApplicationUtils;

@Component
public class MyApplicationRunner implements ApplicationRunner {

	@Autowired
	RestTemplate restTemplate;
	@Override
	public void run(ApplicationArguments args) throws Exception 
	{

/*
		// Test Code for getForObject() method
		Employee e = restTemplate.getForObject(ApplicationUtils.GET_EMP_BY_ID_URL, Employee.class, 101);
		System.out.println("Employee Received From Server : " +  e);
*/
		
/*
		// Test Code for getForEntity() method
		ResponseEntity<Employee> re = restTemplate.getForEntity(ApplicationUtils.GET_EMP_BY_ID_URL, Employee.class, 101);
		
		HttpStatusCode status = re.getStatusCode();
		
		if( status.is2xxSuccessful())
		{
			Employee e = re.getBody();
			System.out.println("Employee Received From Server : " +  e);
		}
*/		
		
		
		// Test Code for exchange() method for GET
		// @param1 : request url
		// @param2 : HTTP Request method (GET/POST)
		// @param3 : request entity (Used to send an object in the request body)
		// @param4 : response type
		// @param5 : values to set in uri variables / path variables

/*
 * 		// This Part is giving exception(HttpClientErrorException | HttpServerErrorException ex)  in Lec-56, which was Solved in Lec-57
 * 		// See next block to updated code
 * 
		ResponseEntity<Employee> re = restTemplate.exchange(ApplicationUtils.GET_EMP_BY_ID_URL,HttpMethod.GET, null, Employee.class, 101);
		
		HttpStatusCode status = re.getStatusCode();
		
		if( status.is2xxSuccessful())
		{
			Employee e = re.getBody();
			System.out.println("Employee Received From Server : " +  e);
		}
*/
		ResponseEntity<?> re;
		try
		{
			re = restTemplate.exchange(ApplicationUtils.GET_EMP_BY_ID_URL,HttpMethod.GET, null, Employee.class, 102);
			HttpStatusCode status = re.getStatusCode();
			
			if( status.is2xxSuccessful())
			{
				Employee e = (Employee) re.getBody();
				System.out.println("Employee Received From Server : " +  e);
			}
		}
		catch(HttpClientErrorException | HttpServerErrorException ex)
		{
			re = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
			System.out.println("Error Response :");
			System.out.println("Body :" + re.getBody());
			System.out.println("Status Code :" + re.getStatusCodeValue());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

/*		
		// Test Code for exchange() method for POST
		// @param1 : request url
		// @param2 : HTTP Request method (GET/POST)
		// @param3 : request entity (Used to send an object in the request body)
		// @param4 : response type
		// @param5 : values to set in uri variables / path variables
		
		// Creating emp object to save/post 
		Employee emp = new Employee();
		emp.setEmpno(1103);
		emp.setEname("PQR");
		emp.setSal(4000.0);
		emp.setDeptno(10);
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", "application/json" );
		requestHeaders.add("Accept", "application/xml");
		                                                             //body  headers
		HttpEntity<Employee> requestEntity = new HttpEntity<Employee>(emp,requestHeaders); // 3rd param request entity 
		
		
			ResponseEntity<Employee> re = restTemplate.exchange(ApplicationUtils.POST_EMP_URL,HttpMethod.POST, requestEntity, Employee.class);
		
			HttpStatusCode status = re.getStatusCode();
			
			if( status.is2xxSuccessful())
			{
				System.out.println("Created : Employee is created into Database !!");
		        System.out.println("Employee Received From Server : " + re.getBody());
			}
*/		
		
	}

}
