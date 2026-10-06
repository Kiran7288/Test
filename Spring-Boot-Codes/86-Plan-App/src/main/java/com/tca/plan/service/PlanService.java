package com.tca.plan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.plan.entity.PlanEntity;
import com.tca.plan.repository.PlanRepository;

@Service
public class PlanService {
	
	@Autowired
	PlanRepository planRepo;
	
	public List<PlanEntity>  fetchAll() {
		return planRepo.findAll();
	}
	
	public PlanEntity fetchById(String planId) {
		Optional<PlanEntity> opt = planRepo.findById(planId);
		if ( opt.isPresent() )
			return opt.get();
		return null;
	}

}