package com.light.lightboot.common.exception;

import com.light.lightboot.common.enums.IResponseEnum;

/**
 * <p>参数异常,业务过程校验参数可抛出该异常，封装工具类时校验参数可抛出该异常</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
public class ArgumentException extends BaseException{

    private static final long serialVersionUID = -8884727350246712313L;

    public ArgumentException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public ArgumentException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
