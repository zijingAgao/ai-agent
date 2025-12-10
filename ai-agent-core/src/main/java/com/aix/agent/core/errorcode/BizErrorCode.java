package com.aix.agent.core.errorcode;

import lombok.AllArgsConstructor;

/**
 * @author Agao
 * @date 2025/12/10 14:51
 */
@AllArgsConstructor
public enum BizErrorCode implements IErrorCode{
    USER_NOT_EXIST("B0001", "用户不存在"),
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
