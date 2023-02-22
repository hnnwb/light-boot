package com.light.lightboot.common.redis.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author wb
 */
@Component
public class RedissonUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedissonUtil.class);
    /**
     * redis锁前缀
     */
    public static final String SYS_LOCK_FLAG = "MY_LOCK";
 
    /**
     * 用于隔开缓存前缀与缓存键值
     */
    public static final String KEY_SPLIT = ":";
    
    // 静态属性注入
    private static Redisson redisson;
    @Autowired
    public void setRedisson(Redisson redisson) {
        RedissonUtil.redisson = redisson;
    }
 
    /**
     * 加锁
     *
     * @param lockName    锁名  相同的key表示相同的锁，建议针对不同的业务使用不同的key
     * @param expiresTime 过期时间，单位：秒
     * @return
     */
    public static boolean getLock(String lockName, long expiresTime) {
        String key = getLockKey(lockName);
        //获取锁对象
        RLock lock = redisson.getLock(key);
        //设置锁过期时间，防止死锁的产生
        lock.lock(expiresTime, TimeUnit.SECONDS);
        logger.info("获取锁成功，Redis Lock key :{}", key);
        return true;
    }
 
    /**
     * 释放锁，建议放在 finally里面
     *
     * @param lockName 锁名称
     */
    public static void unlock(String lockName) {
        String key = getLockKey(lockName);
        //获取所对象
        RLock lock = redisson.getLock(key);
        // 释放锁,判断要解锁的key是否已被锁定并且是否被当前线程保持
        if (lock.isLocked() && lock.isHeldByCurrentThread()) {
            lock.unlock();
            logger.info("释放Redis锁成功，key:{}", key);
        }
    }
 
    /**
     * 对锁的key添加系统标识前缀
     *
     * @return
     */
    private static String getLockKey(String key) {
        return RedissonUtil.SYS_LOCK_FLAG + RedissonUtil.KEY_SPLIT + key;
    }
 
}
