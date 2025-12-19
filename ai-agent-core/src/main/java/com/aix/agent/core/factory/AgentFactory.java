package com.aix.agent.core.factory;

import com.aix.agent.core.config.LLMAutoConfiguration;
import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.errorcode.LLMErrorCode;
import com.aix.agent.core.exception.AgentException;
import com.aix.agent.core.reactagent.AgentExecutor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Agao
 * @date 2025/12/13 17:53
 */
@Slf4j
@Component
public class AgentFactory implements ApplicationContextAware {
    private static final Map<String, AgentExecutor> AGENT_EXECUTOR_MAP = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        Map<String, AgentExecutor> agentExecutorMap = applicationContext.getBeansOfType(AgentExecutor.class);
        if (CollectionUtils.isEmpty(agentExecutorMap)) {
            log.warn("no agent executor found");
            return;
        }
        for (AgentExecutor executor : agentExecutorMap.values()) {
            AGENT_EXECUTOR_MAP.put(executor.getProviderId(), executor);
        }
        log.debug("load chat modes : {}", AGENT_EXECUTOR_MAP.keySet());
    }


    /**
     * 交由大模型执行
     *
     * @param request 执行入参
     * @return
     */
    public static AgentResponse execute(AgentChatRequest request) {
        String providerId = buildProviderId(request.getPlatform(), request.getModelType());
        AgentExecutor agentExecutor = AGENT_EXECUTOR_MAP.get(providerId);
        if (agentExecutor == null) {
            throw new AgentException(LLMErrorCode.LLM_EXECUTOR_NOT_EXIST);
        }
        LLMProperties properties = LLMAutoConfiguration.getLLMProperties(request.getModelType(), request.getPlatform(), request.getModel());

        return agentExecutor.execute(request, properties);
    }


    private static String buildProviderId(String platform, String modelType) {
        return platform + "-" + modelType;
    }
}
