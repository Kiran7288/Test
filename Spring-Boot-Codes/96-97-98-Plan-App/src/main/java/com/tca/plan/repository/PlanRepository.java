package com.tca.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tca.plan.entity.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, String> {

}