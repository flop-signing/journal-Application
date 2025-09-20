package com.mehedy.journal_app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void redisTest(){
        redisTemplate.opsForValue().set("email","vipba@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a=1;
    }
}
