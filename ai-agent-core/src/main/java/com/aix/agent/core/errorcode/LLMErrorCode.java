package com.aix.agent.core.errorcode;

import lombok.AllArgsConstructor;

/**
 * @author Agao
 * @date 2025/12/10 14:33
 */
@AllArgsConstructor
public enum LLMErrorCode implements IErrorCode {

    SYSTEM_ERROR("L0001", "LLM系统异常"),
    LLM_NOT_EXIST("L0002", "LLM模型不存在"),
    LLM_EXECUTOR_NOT_EXIST("L0003", "LLM模型执行器不存在"),
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
