package com.tca.friend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tca.friend.entity.FriendEntity;

public interface FriendRepository extends JpaRepository<FriendEntity, Integer> {
	
	@Query(value="SELECT COUNT(*) FROM FRIEND WHERE  PHONE_NUMBER=?  AND  FRIEND_NUMBER=?", nativeQuery=true)
	Integer  checkFriendContact(Long phoneNumber, Long friendNumber);
	
	@Query(value="SELECT  FRIEND_NUMBER, FRIEND_NAME  FROM  FRIEND  WHERE  PHONE_NUMBER=?", nativeQuery=true)
	List<Object[]>  findFriendsContactNumbers(Long phoneNumber);

}