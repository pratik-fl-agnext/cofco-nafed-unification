package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.CommodityAnalytics;

public interface CommodityAnalyticsRepository extends JpaRepository<CommodityAnalytics, Long> {

    List<CommodityAnalytics> findAll();
}
