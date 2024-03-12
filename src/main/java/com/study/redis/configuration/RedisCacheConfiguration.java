package com.study.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfiguration {

    @Value("${spring.data.redis.ttl.general}")
    private int ttlForGeneralInSeconds;

    @Value("${spring.data.redis.ttl.person}")
    private int ttlForPersonInSeconds;

    @Value("${spring.data.redis.ttl.business}")
    private int ttlForBusinessInSeconds;

    private static final String BUSINESS_PREFIX = "business";
    private static final String PERSON_PREFIX = "person";

    @Bean
    RedisCacheManager redisCacheConf(RedisConnectionFactory connectionFactory) {
        org.springframework.data.redis.cache.RedisCacheConfiguration generalConfiguration = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(prefix -> "redis:cache:" + prefix + "::")
                .entryTtl(Duration.ofSeconds(ttlForGeneralInSeconds))
                .disableCachingNullValues();

        org.springframework.data.redis.cache.RedisCacheConfiguration businessConfiguration = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(ttlForBusinessInSeconds))
                .computePrefixWith(prefix -> "redis:cache:" + prefix + "::")
                .serializeValuesWith( RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        org.springframework.data.redis.cache.RedisCacheConfiguration personConfiguration = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(prefix -> "redis:cache:" + prefix + "::")
                .entryTtl(Duration.ofSeconds(ttlForPersonInSeconds));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(generalConfiguration)
                .withCacheConfiguration(PERSON_PREFIX, personConfiguration)
                .withCacheConfiguration(BUSINESS_PREFIX, businessConfiguration)
                .build();
    }

}