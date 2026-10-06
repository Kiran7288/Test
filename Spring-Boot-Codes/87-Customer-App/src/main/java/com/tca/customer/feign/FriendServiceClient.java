package com.tca.customer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="FRIENDAPP")
public interface FriendServiceClient 
{
	@GetMapping(value="/friend/{phoneNumbers}")
	List<Object[]> fetchFriendsContacts(@PathVariable Long phoneNumbers);
}
