package com.aix.agent.core.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Agao
 * @date 2025/12/13 17:51
 */
@EnableConfigurationProperties(LLMApiProperties.class)
public class AgentConfiguration {

    @ConditionalOnMissingBean
    public ChatClient client(ChatModel chatModel) {

        return ChatClient.builder(chatModel).build();
    }
}
