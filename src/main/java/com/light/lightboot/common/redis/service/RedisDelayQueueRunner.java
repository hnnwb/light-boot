package com.light.lightboot.common.redis.service;

import cn.hutool.extra.spring.SpringUtil;
import com.light.lightboot.common.redis.enums.RedisDelayQueueEnum;
import com.light.lightboot.common.redis.util.RedisDelayQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * 启动延迟队列
 * @author wb
 */
@Slf4j
@Component
public class RedisDelayQueueRunner implements CommandLineRunner {
 
	@Resource
	private RedisDelayQueueUtil redisDelayQueueUtil;
    @Resource
    private ThreadPoolTaskExecutor threadPool;
   ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 50, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000), Executors.defaultThreadFactory());
	@Override
	public void run(String... args) {
		CompletableFuture.runAsync(this::runRedisDelayQueue);
		log.info("(Redis延迟队列启动成功)");
	}

	private void runRedisDelayQueue() {
		while (true){
			try {
				RedisDelayQueueEnum[] queueEnums = RedisDelayQueueEnum.values();
				for (RedisDelayQueueEnum queueEnum : queueEnums) {
					Object value = redisDelayQueueUtil.getDelayQueue(queueEnum.getCode());
					if (value != null) {
						RedisDelayQueueHandle redisDelayQueueHandle = SpringUtil.getBean(queueEnum.getBeanId());
						redisDelayQueueHandle.execute(value);
					}
				}
			} catch (InterruptedException e) {
				log.error("(Redis延迟队列异常中断) {}", e.getMessage());
			}
		}
	}
}
