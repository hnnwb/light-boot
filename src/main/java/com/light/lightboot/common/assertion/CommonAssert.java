package com.light.lightboot.common.assertion;

import cn.hutool.core.util.ArrayUtil;
import com.light.lightboot.common.enums.IResponseEnum;
import com.light.lightboot.common.exception.ArgumentException;
import com.light.lightboot.common.exception.BaseException;

import java.text.MessageFormat;

/**
 * <p></p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
public interface CommonAssert extends IResponseEnum,Assert {

    @Override
    default BaseException newException(Object... args) {
        String message = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            message = MessageFormat.format(this.getMessage(),args);
        }
        return new ArgumentException(this,args,message);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String message = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            message = MessageFormat.format(this.getMessage(),args);
        }
        return new ArgumentException(this,args,message,t);
    }
}
