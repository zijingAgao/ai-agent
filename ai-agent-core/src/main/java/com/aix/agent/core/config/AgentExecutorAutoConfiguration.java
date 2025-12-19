package com.aix.agent.core.config;

import com.aix.agent.core.reactagent.chat.impl.DashScopeAgentChatExecutor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Agao
 * @date 2025/12/19 14:21
 */
@EnableConfigurationProperties(LLMProperties.class)
public class AgentExecutorAutoConfiguration {

    @Bean
    public DashScopeAgentChatExecutor agentExecutor() {
        return new DashScopeAgentChatExecutor();
    }
}
