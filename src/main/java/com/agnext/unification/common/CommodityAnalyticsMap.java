package com.agnext.unification.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.agnext.unification.entity.nafed.CommodityAnalytics;
import com.agnext.unification.repository.nafed.CommodityAnalyticsRepository;

@Component
public class CommodityAnalyticsMap {

    private final CommodityAnalyticsRepository commodityAnalyticsRepository;
    private static Map<Long,List<String>> staticMap = new HashMap<>();



    private static final Logger logger =
            LoggerFactory.getLogger(CommodityAnalyticsMap.class);

    public CommodityAnalyticsMap(CommodityAnalyticsRepository commodityAnalyticsRepository) {
        this.commodityAnalyticsRepository = commodityAnalyticsRepository;
    }

    @PostConstruct
    private void getAnalyticsForCommodity(){
        List<CommodityAnalytics> commodityAnalytics = commodityAnalyticsRepository.findAll();
        staticMap =  commodityAnalytics.stream().collect(Collectors.toMap(CommodityAnalytics::getCommodityId, analytics-> {
            String analtyics = analytics.getAnalyticsName();
            String[] analyticsArray = analtyics.split(",");
            return Arrays.stream(analyticsArray).sequential().collect(Collectors.toList());
        }));
    }

    public Map<Long, List<String>> getMap() {
        return staticMap;
    }
}
