package com.light.lightboot.common.exception.handler;

import cn.hutool.core.util.ObjectUtil;
import com.light.lightboot.common.enums.ArgumentResponseEnum;
import com.light.lightboot.common.enums.CommonResponseEnum;
import com.light.lightboot.common.enums.ServletResponseEnum;
import com.light.lightboot.common.exception.BaseException;
import com.light.lightboot.common.exception.BusinessException;
import com.light.lightboot.common.model.i18n.GlobalMessageSource;
import com.light.lightboot.common.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;

/**
 * <p>全局异常处理</p>
 *
 * @author wb
 * @Date: 2022/02/22/
 */
@Slf4j
@Component
@ControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnMissingBean(GlobalExceptionHandler.class)
public class GlobalExceptionHandler {
    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    @Resource
    GlobalMessageSource globalMessageSource;

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return
     */
    public String getMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        String message = globalMessageSource.getMessage(code, e.getArgs());
        if (ObjectUtil.isEmpty(message)) {
            return e.getMessage();
        }
        return message;
    }

    /**
     * 业务异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorResponse handleBusinessException(BaseException e){
        log.error(e.getMessage(),e);
        return new ErrorResponse(e.getResponseEnum().getCode(),getMessage(e));
    }

    /**
     * 自定义异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ErrorResponse handleBaseException(BaseException e){
        log.error(e.getMessage(),e);
        return new ErrorResponse(e.getResponseEnum().getCode(),getMessage(e));
    }

    /**
     * Controller上层异常
     * @param e
     * @return
     */
    @ExceptionHandler({NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            // BindException.class,
            // MethodArgumentNotValidException.class
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class})
    @ResponseBody
    public ErrorResponse handleServletException(Exception e){
        log.error(e.getMessage(),e);
        int code = CommonResponseEnum.SERVER_ERROR.getCode();
        try {
            ServletResponseEnum servletResponseEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletResponseEnum.getCode();
        } catch (IllegalArgumentException ex) {
            log.error("class [{}] not defined in enum {}",e.getClass().getName(),ServletResponseEnum.class.getName());
        }
        if (ENV_PROD.equals(profile)) {
            //正式环境：异常信息不展示给用户
             code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException exception = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getMessage(exception);
            return new ErrorResponse(code,message);
        }
        return new ErrorResponse(code,e.getMessage());
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ErrorResponse handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     * @param bindingResult 绑定参数
     * @return 异常结果
     */
    private ErrorResponse wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        bindingResult.getAllErrors().forEach(error -> {
            msg.append(",");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(":");
            }
            msg.append(error.getDefaultMessage() == null?"":error.getDefaultMessage());
        });

        return new ErrorResponse(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2));
    }

    /**
     * 未定义异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorResponse handException(Exception e){
        log.error(e.getMessage(),e);
        if (ENV_PROD.equals(profile)) {
            //正式环境，异常信息不展示给用户
            int code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getMessage(baseException);
            return new ErrorResponse(code,message);
        }
        return new ErrorResponse(CommonResponseEnum.SERVER_ERROR.getCode(),e.getMessage());
    }
}
