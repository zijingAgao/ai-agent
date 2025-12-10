package com.aix.agent.core.exception;

import com.aix.agent.core.errorcode.IErrorCode;

/**
 * 大模型异常
 *
 * @author Agao
 * @date 2025/12/10 14:26
 */
public class LLMException extends AbstractException {

    public LLMException(IErrorCode iErrorCode) {
        this(null, iErrorCode);
    }

    public LLMException(String message, IErrorCode iErrorCode) {
        this(message, null, iErrorCode);
    }

    public LLMException(String message, Throwable cause, IErrorCode iErrorCode) {
        super(message, cause, iErrorCode);
    }
}
