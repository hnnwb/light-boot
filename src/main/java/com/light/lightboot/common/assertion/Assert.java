package com.light.lightboot.common.assertion;

import cn.hutool.core.util.ObjectUtil;
import com.light.lightboot.common.exception.BaseException;

/**
 * <p>枚举类异常断言，满足条件抛出异常</p>
 * <p>错误码和错误信息定义在枚举类中，在本断言方法中，传递错误信息需要的参数</p>
 * @author wb
 * @Date: 2022/02/22/
 */
public interface Assert {


    BaseException newException(Object...args);
    /**
     * 创建异常
     *
     * @param t
     * @param args
     * @return
     */
    BaseException newException(Throwable t, Object... args);

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj 待判断对象
     */
    default void assertNotNull(Object obj) {
        if (ObjectUtil.isEmpty(obj)) {
            throw newException();
        }
    }

}
