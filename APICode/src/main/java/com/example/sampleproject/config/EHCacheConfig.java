package com.example.sampleproject.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "myapplication.setting", name = "enableCache",havingValue = "true")
@Configuration
@EnableCaching
public class EHCacheConfig {
}
