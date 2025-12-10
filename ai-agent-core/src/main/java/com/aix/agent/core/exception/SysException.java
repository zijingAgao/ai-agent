package com.aix.agent.core.exception;

import com.aix.agent.core.errorcode.IErrorCode;

/**
 * @author Agao
 * @date 2025/12/10 16:54
 */
public class SysException extends AbstractException {

    public SysException(IErrorCode iErrorCode) {
        this(null, iErrorCode);
    }

    public SysException(String message, IErrorCode iErrorCode) {
        this(message, null, iErrorCode);
    }

    public SysException(String message, Throwable throwable, IErrorCode iErrorCode) {
        super(message, throwable, iErrorCode);
    }
}
