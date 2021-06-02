package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.cofco.Analytics;

public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

	Analytics findByAnalyticName(String analyticName);;
	
}
