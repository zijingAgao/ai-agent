package com.aix.agent.core.factory;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
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


    public static ChatModel getChatModel(String modelName) {
        return CHAT_MODEL_MAP.get(modelName);
    }

    public static Map<String, ChatModel> getChatModelMap() {
        return CHAT_MODEL_MAP;
    }
}
