package com.tca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	private Integer id;
	private String title;
	private Double price;

}
