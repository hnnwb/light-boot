package com.light.lightboot.common.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author wb
 * @Date: 2023/02/20/
 */
@Configuration
public class MyredissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient RedissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
