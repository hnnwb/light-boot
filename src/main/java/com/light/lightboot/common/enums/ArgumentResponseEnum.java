package com.light.lightboot.common.enums;

import com.light.lightboot.common.assertion.CommonAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p></p>
 *
 * @author wb
 * @Date: 2022/02/23/
 */
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements CommonAssert {
    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常"),

    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

}
