package com.aix.agent.core.errorcode;

import lombok.AllArgsConstructor;

/**
 * @author Agao
 * @date 2025/12/10 14:51
 */
@AllArgsConstructor
public enum SysErrorCode implements IErrorCode {
    SYSTEM_ERROR("S0001", "系统异常"),
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
