package com.tca.friend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "FRIEND")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FriendEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer  id;
	
	private  Long  phoneNumber;
	
	private  Long  friendNumber;
	
	private  String friendName;
	

}
