package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.Analytics;

public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

	Analytics findByAnalyticName(String analyticName);;
	
}
