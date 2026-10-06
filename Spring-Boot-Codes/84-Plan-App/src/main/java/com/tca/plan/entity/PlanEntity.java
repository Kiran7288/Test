package com.tca.plan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PLAN_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanEntity {
	
	@Id
	private String planId;
	
	private String planName;
	
	private String description;
	
	private String validity;
	
	

}
