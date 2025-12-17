package com.aix.agent.core.factory;

import com.aix.agent.core.config.LLMAutoConfiguration;
import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.dto.LLMRequest;
import com.aix.agent.core.dto.LLMResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.model.ChatModel;
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
    private static final Map<String, ChatModel> CHAT_MODEL_MAP = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        CHAT_MODEL_MAP.putAll(applicationContext.getBeansOfType(ChatModel.class));
        log.debug("load chat modes : {}", CHAT_MODEL_MAP.keySet());
    }


    public LLMResponse ask(LLMRequest request){
        LLMProperties properties = LLMAutoConfiguration.getLLMProperties(request.getModelType(), request.getPlatform(), request.getModel());

        return null;
    }
}
