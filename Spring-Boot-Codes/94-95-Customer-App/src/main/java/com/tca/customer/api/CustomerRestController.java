package com.tca.customer.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tca.customer.entity.CustomerEntity;
import com.tca.customer.entity.PlanEntity;
import com.tca.customer.feign.FriendServiceClient;
import com.tca.customer.feign.PlanServiceClient;
import com.tca.customer.model.CustomerResponse;
import com.tca.customer.model.LoginRequest;
import com.tca.customer.service.CustomerService;

@RestController
public class CustomerRestController {

	//private static final String PLAN_URL = "http://PLANAPP/plans/{id}";
	//private static final String FRIEND_URL = "http://FRIENDAPP/friend/{phoneNumber}";
	
	//private static final String PLAN_URL = "http://localhost:8081/plans/{id}";
	//private static final String FRIEND_URL = "http://localhost:8082/friend/{phoneNumber}";
	
	@Autowired
	CustomerService service;

/*	
	@Autowired
	RestTemplate restTemplate;
*/	
	
	@Autowired
	PlanServiceClient planServiceClient;
	
	@Autowired
	FriendServiceClient friendServiceClient;
	
	
	
	@PostMapping(value = "/customer/register")
	public boolean addCustomer(@RequestBody CustomerEntity customer) {
		return service.registerCustomer(customer);
	}

	@PostMapping(value = "/customer/login")
	public boolean loginCustomer(@RequestBody LoginRequest loginRequest) {
		return service.loginCustomer(loginRequest);
	}

	@GetMapping("/customer/profile/{phoneNumber}")
	public CustomerResponse showProfile(@PathVariable Long phoneNumber) {

		CustomerEntity customerEntity = service.readCustomer(phoneNumber);
		
		CustomerResponse customerResponse = new CustomerResponse();

		BeanUtils.copyProperties(customerEntity, customerResponse);
		
		
/*   // Commenting this code bcz we are not using RestTemplate instead we are using FeignClient
 
		// calling plan microservice
		ResponseEntity<PlanEntity> re = restTemplate.getForEntity(PLAN_URL, PlanEntity.class,
				customerEntity.getPlanId());
		
		PlanEntity planEntity = re.getBody();
		BeanUtils.copyProperties(planEntity, customerResponse);

		// calling friend microservice
		//List<Object[]> friendsContactNumbers = restTemplate.getForObject(FRIEND_URL, List.class, phoneNumber);
		
		ParameterizedTypeReference<List<Object[]>> typeRef = new ParameterizedTypeReference<List<Object[]>>() {};
		
		ResponseEntity<List<Object[]>> re2 = restTemplate.exchange(FRIEND_URL, HttpMethod.GET, null, typeRef, phoneNumber);
		List<Object[]> friendsContactNumbers = re2.getBody();
		customerResponse.setFriendsContactNumbers(friendsContactNumbers);
*/		
		// Calling plan microservice with Feign client
		PlanEntity planEntity = planServiceClient.fetchPlanById(customerEntity.getPlanId());
		BeanUtils.copyProperties(planEntity, customerResponse);
		
		// Calling friend microservice with Feign client
		List<Object[]> friendContacts = friendServiceClient.fetchFriendsContacts(phoneNumber);
		customerResponse.setFriendsContactNumbers(friendContacts);
				
		return customerResponse;
	}

}
