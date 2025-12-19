package com.aix.agent.core.exception;

import com.aix.agent.core.errorcode.SysErrorCode;
import com.aix.agent.core.result.XR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Agao
 * @date 2025/12/10 16:55
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public XR<?> handleException(Exception e) {
        log.error("系统异常", e);
        return XR.error(SysErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BizException.class)
    public XR<?> handleBizException(BizException e) {
        return XR.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = AgentException.class)
    public XR<?> handleLLMException(AgentException e) {
        return XR.error(e.getCode(), e.getMsg());
    }
}
