package ru.akutepov.spring.cache.example.configuration;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.akutepov.spring.cache.example.constant.CacheConstants;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public CaffeineCache databaseCache() {
        return new CaffeineCache(CacheConstants.DATABASE_CACHE, Caffeine.newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build());
    }
}
