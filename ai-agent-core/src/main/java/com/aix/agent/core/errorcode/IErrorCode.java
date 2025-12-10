package com.aix.agent.core.errorcode;

/**
 * @author Agao
 * @date 2025/12/10 14:44
 */
public interface IErrorCode {

    /**
     * 异常编码 字母+4位数字定义
     *
     * @return
     */
    String getCode();

    String getMsg();
}
