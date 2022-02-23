package com.light.lightboot.common.model.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>分页返回结果</p>
 *
 * @author wb
 * @Date: 2022/02/23/
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class ResPage<T> extends CommonResponse<PageData<T>>{

    public ResPage() {
    }

    public ResPage(PageData<T> data) {
        super(data);
    }
}
