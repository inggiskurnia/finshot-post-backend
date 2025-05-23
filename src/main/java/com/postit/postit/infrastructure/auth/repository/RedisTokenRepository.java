package com.postit.postit.infrastructure.auth.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisTokenRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String token, Duration duration){
        redisTemplate.opsForValue().set(token, "blacklisted", duration);
    }

    public boolean isTokenBlacklisted(String token){
        return redisTemplate.hasKey(token);
    }
}
