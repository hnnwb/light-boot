package com.light.lightboot.common.assertion;

import cn.hutool.core.util.ArrayUtil;
import com.light.lightboot.common.enums.IResponseEnum;
import com.light.lightboot.common.exception.ArgumentException;
import com.light.lightboot.common.exception.BaseException;

import java.text.MessageFormat;

/**
 * <p>参数异常断言</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
public interface ArgumentAssert extends IResponseEnum,Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, args, msg, t);
    }
}
