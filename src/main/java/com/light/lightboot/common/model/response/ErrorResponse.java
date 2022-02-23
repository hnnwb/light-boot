package com.light.lightboot.common.model.response;

/**
 * <p>异常返回</p>
 *
 * @author wb
 * @Date: 2022/02/23/
 */
public class ErrorResponse extends BaseResponse{

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        super(code, message);
    }
}
