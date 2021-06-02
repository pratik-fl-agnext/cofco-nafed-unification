package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.Analytics;

@Repository("cofcoAnalyticsRepository")
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

	Analytics findByAnalyticName(String analyticName);;
	
}
