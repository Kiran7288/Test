package com.tca.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tca.customer.entity.PlanEntity;

// @FeignClient(name="PLANAPP", url="http://localhost:8081") //[48:45] you need to provide  URL of Remove Service i.e plann app if you are not using eureka. Professional way -> (i.e when your plan service is not registered with eureka)
@FeignClient(name="PLANAPP")
public interface PlanServiceClient 
{
	@GetMapping(value="/plans/{id}")
	PlanEntity fetchPlanById(@PathVariable String id);
}
