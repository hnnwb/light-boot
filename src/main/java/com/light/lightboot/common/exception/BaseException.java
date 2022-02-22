package com.light.lightboot.common.exception;

import com.light.lightboot.common.enums.IResponseEnum;
import lombok.Data;

/**
 * <p>基础异常类，所有自定义异常类都需要继承本类</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
@Data
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 2823558945595698352L;

    protected IResponseEnum responseEnum;

    protected Object[] args;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(int code,String msg){
        super(msg);
        this.responseEnum =new IResponseEnum(){

            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }
        };
    }

    public BaseException(IResponseEnum responseEnum, Object[] args) {
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args,String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum responseEnum,Object[] args,String message, Throwable cause) {
        super(message, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }

}
