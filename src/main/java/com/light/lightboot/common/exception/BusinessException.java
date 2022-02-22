package com.light.lightboot.common.exception;

import com.light.lightboot.common.enums.IResponseEnum;

/**
 * <p>业务异常类</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
public class BusinessException extends BaseException{

    private static final long serialVersionUID = -2271979413627217433L;

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
