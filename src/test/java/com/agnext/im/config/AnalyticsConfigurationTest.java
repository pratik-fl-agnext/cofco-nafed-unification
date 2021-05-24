package com.agnext.im.config;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.agnext.unification.config.AnalyticsVariations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyticsConfigurationTest extends TestCase {

    @Autowired
    AnalyticsVariations analyticsVariations;

    @Test
    public void testProperties(){
        assertThat(analyticsVariations.getAnalytics()
                .get("moisturecontent")
                .get(0)).isEqualTo("moisture");

        assertThat(analyticsVariations.getAnalytics()
                .get("shrivelledandimmature")
                .get(0)).isEqualTo("shrivelled & immature");
    }
}
