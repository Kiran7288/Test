package com.tca.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student 
{
	@Id
	private Integer rno;
	private String name;
	private Double per;
	
}
