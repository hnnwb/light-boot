package com.light.lightboot.common.exception;

import com.light.lightboot.common.assertion.Assert;

/**
 * <p>只包装了 错误信息 的 {@link RuntimeException}.</p>
 *  用于 {@link Assert} 中用于包装自定义异常信息
 * @author wb
 * @Date: 2022/02/22/
 */
public class WrapMessageException extends RuntimeException{

    public WrapMessageException(String message) {
        super(message);
    }

    public WrapMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
