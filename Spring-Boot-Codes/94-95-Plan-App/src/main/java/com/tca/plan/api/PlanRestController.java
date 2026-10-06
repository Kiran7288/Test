package com.tca.plan.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tca.plan.entity.PlanEntity;
import com.tca.plan.service.PlanService;

@RestController
public class PlanRestController {
	
	@Autowired
	PlanService planService;
	
	@GetMapping( value = "/plans" , produces = "application/json" )
	public ResponseEntity<List<PlanEntity>> getAllPlans() {
		return new ResponseEntity<>(planService.fetchAll(), HttpStatus.OK);
	}
	
	@GetMapping( value = "/plans/{id}" , produces = "application/json" )
	public ResponseEntity<PlanEntity>  getPlanById(@PathVariable( value = "id") String planId) {
		PlanEntity entity = planService.fetchById(planId);
		if ( entity == null ) {
			return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(entity, HttpStatus.OK);
		}
		
	}

}
