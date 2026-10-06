package com.tca.friend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.friend.entity.FriendEntity;
import com.tca.friend.repository.FriendRepository;

@Service
public class FriendService {
	
	@Autowired
	FriendRepository  repository;
	

	public FriendEntity addFriendContact(FriendEntity friend) {
		
		Integer  count=repository.checkFriendContact(friend.getPhoneNumber(), friend.getFriendNumber());
		if(count==0) 
		{
			FriendEntity newFriend = repository.saveAndFlush(friend);
			return  newFriend;
		}
		else 
		{
			return null;
		}
	}

	public List<Object[]> readFriendsContacts(Long phoneNumber) {
		
		return  repository.findFriendsContactNumbers(phoneNumber);
	}

}
