package com.light.lightboot.common.enums;

/**
 * @author wb
 * @Date: 2022/02/22/
 * @Description:
 */
public interface IResponseEnum {
    /**
     * 获取返回码
     * @return 返回码
     */
    int getCode();

    /**
     * 获取返回信息
     * @return 返回信息
     */
    String getMessage();
}
