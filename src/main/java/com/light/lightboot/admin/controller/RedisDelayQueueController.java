package com.light.lightboot.admin.controller;

import com.light.lightboot.common.redis.enums.RedisDelayQueueEnum;
import com.light.lightboot.common.redis.util.RedisDelayQueueUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列测试
 * @author wb
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class RedisDelayQueueController {
 
	@Resource
	private RedisDelayQueueUtil redisDelayQueueUtil;
 
	@GetMapping("/addQueue")
	public void addQueue() {
		Map<String, String> map1 = new HashMap<>(2);
		map1.put("orderId", "10001");
		map1.put("remark", "订单支付超时，自动取消订单");
		log.info("请求成功=================================");
		// 为了测试效果，延迟10秒钟
		redisDelayQueueUtil.addDelayQueue(map1, 10, TimeUnit.SECONDS, RedisDelayQueueEnum.ORDER_PAYMENT_TIMEOUT.getCode());
	}	 
}
