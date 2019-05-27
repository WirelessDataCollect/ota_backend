package com.ruili.fota.service.impl;

import com.ruili.fota.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yin on 2019/1/2
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        //15分钟过期
        ops.set(key, value, 900, TimeUnit.SECONDS);
    }

    @Override
    public String getValue(String key) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
