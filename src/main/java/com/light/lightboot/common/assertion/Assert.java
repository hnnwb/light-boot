package com.light.lightboot.common.assertion;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.light.lightboot.common.exception.BaseException;
import com.light.lightboot.common.exception.WrapMessageException;

import java.text.MessageFormat;

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
     * 创建异常
     * 先使用 errMsg 创建一个 {@link WrapMessageException}异常
     * 再以入参的形式传给{{{@link #newException(Throwable, Object...)}}},作为最后创建的异常的cause属性
     * @param errMsg 自定义的错误信息
     * @param args
     * @return
     */
    default BaseException newExceptionWithMsg(String errMsg,Object... args){
        if (ArrayUtil.isNotEmpty(args)) {
            errMsg = MessageFormat.format(errMsg,args);
        }
        WrapMessageException exception = new WrapMessageException(errMsg);
        throw newException(exception,args);
    }

    /**
     * 创建异常
     * 先使用 errMsg和t创建一个{@link WrapMessageException}异常
     * 再以入参的形式传给{{{@link #newException(Throwable, Object...)}}}
     * @param errMsg
     * @param t
     * @param args
     * @return
     */
    default BaseException newExceptionWithMsg(String errMsg,Throwable t,Object... args){
        if (ArrayUtil.isNotEmpty(args)) {
            errMsg = MessageFormat.format(errMsg,args);
        }
        WrapMessageException exception = new WrapMessageException(errMsg,t);
        throw newException(exception,args);
    }

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
