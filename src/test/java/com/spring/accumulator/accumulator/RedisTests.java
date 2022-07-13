package com.spring.accumulator.accumulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AccumulatorApplication.class)
public class RedisTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisValue() {
        String redisKey = "test:value";
        redisTemplate.opsForValue().set(redisKey, "hello");
        Object result = redisTemplate.opsForValue().get(redisKey);
        System.out.println(result);
    }

    @Test
    public void testRedisTransaction() {
        List<Object> txResults = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("test:01", 1);
                operations.opsForValue().set("test:02", 2);
                operations.opsForValue().set("test:03", 3);
                System.out.println(operations.opsForValue().get("test:01"));
                return operations.exec();
            }
        });
        System.out.println(txResults);
    }
}
