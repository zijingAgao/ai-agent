package com.aix.agent.core.exception;

import com.aix.agent.core.errorcode.IErrorCode;

/**
 * @author Agao
 * @date 2025/12/10 16:53
 */
public class BizException extends AbstractException {
    public BizException(IErrorCode iErrorCode) {
        this(null, iErrorCode);
    }

    public BizException(String message, IErrorCode iErrorCode) {
        this(message, null, iErrorCode);
    }

    public BizException(String message, Throwable throwable, IErrorCode iErrorCode) {
        super(message, throwable, iErrorCode);
    }
}
