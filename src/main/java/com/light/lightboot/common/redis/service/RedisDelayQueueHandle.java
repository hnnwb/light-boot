package com.light.lightboot.common.redis.service;

/**
 * 延迟队列执行器
 * @author wb
 */
public interface RedisDelayQueueHandle<T> {
	/**
	 * 通用执行器
	 * @param t
	 */
	void execute(T t);
 
}
