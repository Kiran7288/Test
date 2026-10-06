package com.tca.friend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tca.friend.entity.FriendEntity;
import com.tca.friend.service.FriendService;

@RestController
public class FriendRestController {
	
	@Autowired
	FriendService  service;
	
	@Autowired
	Environment env;
	
	@PostMapping(value = "/friend/add")
	public  ResponseEntity<FriendEntity> addFriend(@RequestBody FriendEntity  friend) {
		FriendEntity newEntity = service.addFriendContact(friend);
		if ( newEntity != null )
			return  new ResponseEntity<>(newEntity, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			//return ResponseEntity.badRequest().build();
	}
	
	@GetMapping( value = "/friend/{phoneNumber}")
	public  List<Object[]>  getFriendsContacts(@PathVariable Long phoneNumber) {
		
		String port = env.getProperty("server.port");
		System.out.println("port ------------> " + port);
		
		return service.readFriendsContacts(phoneNumber);
	}

}
