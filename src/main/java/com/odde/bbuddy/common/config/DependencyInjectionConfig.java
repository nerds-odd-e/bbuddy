package com.odde.bbuddy.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DependencyInjectionConfig {

    @Bean
    public Clock systemDefaultZone() {
        return Clock.systemDefaultZone();
    }

}
