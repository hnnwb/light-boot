package com.light.lightboot.common.redis.enums;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 系统缓存，及基础配置
 *
 * @author wb
 */
public enum CacheConfigEnums {

    /**
     * 【 延时队列 】
     **/
    R_BLOCKING_QUEUE(commonCacheKey() + "rBlockingQueue", Duration.ZERO),
    /**
     * 永久缓存,永不过期 ttl永久 = Duration.ZERO
     */
    PERPETUAL_CACHE(commonCacheKey() + "perpetualCH", Duration.ZERO),

    /**
     * 默认缓存 ，1天
     */
    DEFAULT_CACHE(commonCacheKey() + "defaultCH", Duration.ofDays(1)),
    SOCO_LONGITUDE_TO_ADDRESS_CACHE(commonCacheKey() + "socoLongitudeToAddressCH", Duration.ofDays(1)),

    /**
     * 【 测试缓存 】
     **/
    TEST_DTO_CACHE(commonCacheKey() + "testDtoCH", Duration.ofDays(1)),
    ;

    private final String   cacheName;       //缓存名
    private final Duration ttl;             //ttl

    static {
        //验证【value】必须是唯一的
        int size = Arrays.stream(CacheConfigEnums.values())
                .map(CacheConfigEnums::getKey)
                .collect(Collectors.toSet())
                .size();
    }

    //公共缓存key
    private static final String commonCacheKey = "yjxService:";

    public static String commonCacheKey() {
        return commonCacheKey;
    }

    CacheConfigEnums(String cacheName, Duration ttl) {
        this.cacheName = cacheName;
        this.ttl = ttl;
    }

    /**
     * 获取缓存名
     *
     * @return
     */
    public String getKey() {
        return cacheName;
    }

    /**
     * 获取TTL
     *
     * @return
     */
    public Duration getTTL() {
        return this.ttl;
    }

}


