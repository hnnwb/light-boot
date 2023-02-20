package com.light.lightboot.common.redis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wb
 */

@Getter
@AllArgsConstructor
public enum RedisDelayQueueEnum {

	ORDER_PAYMENT_TIMEOUT("ORDER_PAYMENT_TIMEOUT","超时订单自动关闭队列", "orderPaymentTimeout"),
	;

	/**
	 * 延迟队列 Redis Key
	 */
	private String code;
 
	/**
	 * 中文描述
	 */
	private String name;
 
	/**
	 * 延迟队列具体业务实现的 Bean
	 * 可通过 Spring 的上下文获取
	 */
	private String beanId;
	
}
