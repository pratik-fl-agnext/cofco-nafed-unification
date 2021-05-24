package com.agnext.unification.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "analyticsconfig")
public class AnalyticsVariations {

    private Map<String, List<String>> analytics;

    public Map<String, List<String>> getAnalytics() {
        return analytics;
    }

    public void setAnalytics(Map<String, List<String>> analytics) {
        this.analytics = analytics;
    }
}
