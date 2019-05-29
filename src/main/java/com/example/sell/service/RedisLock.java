package com.example.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * @author Lixh
 * @Date: 2018/5/19 09:48
 * @Description:
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *
     * @param key
     * @param value 同时进入的两个线程的值  当前时间+超时时间
     * @return
     * 三个线程A、B、C   A已获得锁   BC同时竞争锁
     * Key = 产品id， value = 当前时间+超时时间，即BC的value相等
     *
     * 1. setIfAbsent单线程：所以此时AB谁先设置谁先返回就代表谁先获得锁
     * 2. 模拟此时锁已经被A占有，但由于A获得锁后在处理业务逻辑时出异常了导致死锁
     *      2.1 BC线程获取线程A的值
     *      2.2 判断线程A的值是否过期
     *          2.2.1 BC线程竞争获得并设置新的值，BC的value相等
     *              假设B先竞争到了，并设定了新值，旧值是A线程的值，在第五步时判断获取的值是否等于A线程的值，
     *              当C设置并获取时，由于B先竞争到了，所以此时C获取到的旧值为B线程的值，由于currentValue是A的，所以在第五步判断时不相等。
     */
    public boolean lock(String key, String value) {


        // 第一步：如果设置成功，代表加锁成功
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        // 第二步：获取上一个线程A的锁的值   BC的值为value
        String currentValue = redisTemplate.opsForValue().get(key);
        // 第三步：如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.valueOf(currentValue) < System.currentTimeMillis()) {

            // 第四步：获取上一个锁的值并设置新的值, BC有先后关系。
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            // 第五步：判断
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     * 问题：客户端A加锁，一段时间之后客户端A解锁，在执行jedis.del()之前，锁突然过期了，
     *      此时客户端B尝试加锁成功，然后客户端A再执行del()方法，则将客户端B的锁给解除了。
     */
    public void unlock(String key, String value) {
        // 执行lua脚本，确保原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        redisTemplate.execute(redisScript, Collections.singletonList(key), value);
//        try {
//            String currentValue = redisTemplate.opsForValue().get(key);
//            // 当前加锁值 == 当前线程的值 才可进行解锁
//            if (!StringUtils.isEmpty(currentValue) && currentValue.equalsIgnoreCase(value)) {
//                redisTemplate.opsForValue().getOperations().delete(key);
//            }
//        } catch (Exception e) {
//            log.error("【redis分布式锁】解锁异常，{}", e);
//        }
    }
}
