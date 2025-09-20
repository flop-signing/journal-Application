package com.mehedy.journal_app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public <T> T getValue(String key, Class<T> clazz) throws JsonProcessingException {
        Object value = redisTemplate.opsForValue().get(key);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(value.toString(), clazz);
    }

    public void setValue(String key, Object o, Long ttl) throws JsonProcessingException {
        redisTemplate.opsForValue().set(key, o.toString(), ttl, TimeUnit.SECONDS);
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(value.toString(), clazz);
    }
}
