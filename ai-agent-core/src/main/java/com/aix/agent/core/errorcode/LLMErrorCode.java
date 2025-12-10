package com.aix.agent.core.errorcode;

import lombok.AllArgsConstructor;

/**
 * @author Agao
 * @date 2025/12/10 14:33
 */
@AllArgsConstructor
public enum LLMErrorCode implements IErrorCode {

    SYSTEM_ERROR("L0001", "LLM系统异常"),
    ;


    private final String code;
    private final String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
