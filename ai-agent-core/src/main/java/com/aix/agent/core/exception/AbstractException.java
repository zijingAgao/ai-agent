package com.aix.agent.core.exception;

import com.aix.agent.core.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author Agao
 * @date 2025/12/10 14:43
 */
@Getter
public abstract class AbstractException extends RuntimeException {
    public final String code;
    public final String msg;

    public AbstractException(String message, Throwable throwable, IErrorCode iErrorCode) {
        super(StringUtils.hasText(message) ? message : iErrorCode.getMsg(), throwable);
        this.code = iErrorCode.getCode();
        this.msg = iErrorCode.getMsg();
    }
}
