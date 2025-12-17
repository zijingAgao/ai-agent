package com.aix.agent.core.factory;

import com.aix.agent.core.config.LLMAutoConfiguration;
import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.errorcode.LLMErrorCode;
import com.aix.agent.core.exception.LLMException;
import com.aix.agent.core.llm.LLMExecutor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Agao
 * @date 2025/12/13 17:53
 */
@Slf4j
@Component
public class LLMApiFactory implements ApplicationContextAware {
    private static final Map<String, LLMExecutor> CHAT_MODEL_MAP = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        CHAT_MODEL_MAP.putAll(applicationContext.getBeansOfType(LLMExecutor.class));
        log.debug("load chat modes : {}", CHAT_MODEL_MAP.keySet());
    }


    /**
     * 交由大模型执行
     *
     * @param request 执行入参
     * @return
     */
    public LLMResponse execute(LLMRequest request) {
        LLMProperties properties = LLMAutoConfiguration.getLLMProperties(request.getModelType(), request.getPlatform(), request.getModel());
        String executor = properties.getExecutor();

        LLMExecutor llmExecutor = CHAT_MODEL_MAP.get(executor);
        if (llmExecutor == null) {
            throw new LLMException(LLMErrorCode.LLM_EXECUTOR_NOT_EXIST);
        }

        return llmExecutor.execute(request,properties);
    }
}
