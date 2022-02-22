package com.light.lightboot.common.assertion;

import cn.hutool.core.util.ArrayUtil;
import com.light.lightboot.common.enums.IResponseEnum;
import com.light.lightboot.common.exception.BaseException;
import com.light.lightboot.common.exception.BusinessException;

import java.text.MessageFormat;

/**
 * <p>业务异常断言</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
public interface BusinessAssert extends IResponseEnum,Assert {

    @Override
    default BaseException newException(Object... args) {
        String message = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            message = MessageFormat.format(this.getMessage(),args);
        }
        return new BusinessException(this,args,message);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String message = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            message = MessageFormat.format(this.getMessage(),args);
        }
        return new BusinessException(this,args,message,t);
    }
}
