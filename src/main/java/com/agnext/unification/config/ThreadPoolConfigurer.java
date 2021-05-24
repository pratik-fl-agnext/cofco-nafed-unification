package com.agnext.unification.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfigurer implements AsyncConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolConfigurer.class);

    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(175);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(175);
        executor.setThreadNamePrefix("Reporting-");
        executor.initialize();
        return executor;
    }

}