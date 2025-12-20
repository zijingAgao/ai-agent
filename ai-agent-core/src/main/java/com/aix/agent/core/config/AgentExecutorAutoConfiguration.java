package com.aix.agent.core.config;

import com.aix.agent.core.reactagent.chat.impl.DashScopeAgentChatExecutor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Agao
 * @date 2025/12/19 14:21
 */
@Configuration
public class AgentExecutorAutoConfiguration {

    @Bean
    public DashScopeAgentChatExecutor agentExecutor() {
        return new DashScopeAgentChatExecutor();
    }
}
