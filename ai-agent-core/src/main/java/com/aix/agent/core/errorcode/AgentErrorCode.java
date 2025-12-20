package com.aix.agent.core.errorcode;

import lombok.AllArgsConstructor;

/**
 * @author Agao
 * @date 2025/12/10 14:33
 */
@AllArgsConstructor
public enum AgentErrorCode implements IErrorCode {

    SYSTEM_ERROR("L0001", "LLM 系统异常"),
    LLM_NOT_EXIST("L0002", "LLM 模型不存在"),
    LLM_EXECUTOR_NOT_EXIST("L0003", "Agent 不存在"),
    LLM_CALL_ERROR("L0004", "LLM 调用异常"),
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
