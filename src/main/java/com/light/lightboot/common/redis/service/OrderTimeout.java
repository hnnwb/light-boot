package com.light.lightboot.common.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 订单支付超时处理类
 * @author wb
 */
@Component
@Slf4j
public class OrderTimeout implements RedisDelayQueueHandle<Map> {
	@Override
	public void execute(Map map) {
		log.info("(收到超时订单延迟消息) {}", map);
		// TODO 订单相关，处理业务逻辑...
		//1.调用第三方（微信，支付宝）的支付接口，查询订单是否已经支付，如果确认没支付则，调用关闭订单支付的api,并修改订单的状态为关闭，同时回滚库存数量。
		//2.如果支付状态为已支付则需要做补偿操作，修改订单的状态为已支付，订单历史记录
 
	}
}
