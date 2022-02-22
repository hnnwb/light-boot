package com.light.lightboot.common.model.response;

import com.light.lightboot.common.enums.CommonResponseEnum;
import com.light.lightboot.common.enums.IResponseEnum;
import lombok.Data;

/**
 * <p>基础返回结果</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
@Data
public class BaseResponse {

    /**
     * 返回码
     */
    protected int code;
    /**
     * 返回消息
     */
    protected String message;

    public BaseResponse() {
        // 默认创建成功的回应
        this(CommonResponseEnum.SUCCESS);
    }

    public BaseResponse(IResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
