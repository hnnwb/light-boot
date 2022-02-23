package com.light.lightboot.common.model.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>通用返回结果，同{@link CommonResponse}</p>
 *
 * @author wb
 * @Date: 2022/02/23/
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Res<T> extends CommonResponse<T>{

    public Res(){
        super();
    }

    public Res(T data){
        super();
        this.data = data;
    }

    public Res(T data, String msg) {
        super();
        this.data = data;
        this.message = msg;
    }

    public Res(Throwable e) {
        super();
        this.message = e.getMessage();
        this.code = -1;
    }
}
