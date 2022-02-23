package com.light.lightboot.common.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>通用返回结果</p>
 *
 * @author wb
 * @Date: 2022/02/23/
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse {
    /**
     * 数据列表
     */
    protected T data;

    public CommonResponse() {
    }

    public CommonResponse(T data) {
        super();
        this.data = data;
    }
}
