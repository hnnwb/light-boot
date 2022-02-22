package com.light.lightboot.common.exception;

import com.light.lightboot.common.enums.IResponseEnum;

/**
 * <p>接口参数格式校验异常</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
public class ValidationException extends BaseException{

    private static final long serialVersionUID = 6170891655648537663L;

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
