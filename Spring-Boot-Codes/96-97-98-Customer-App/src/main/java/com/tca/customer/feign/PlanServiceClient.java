package com.tca.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tca.customer.entity.PlanEntity;


@FeignClient(name="PLANAPP", fallback=PlanFallback.class)
public interface PlanServiceClient 
{
	@GetMapping(value="/plans/{id}")
	PlanEntity fetchPlanById(@PathVariable String id);
}

@Component
class PlanFallback implements PlanServiceClient 
{

	@Override
	public PlanEntity fetchPlanById(String id) {
		return new PlanEntity();
	}
	
}