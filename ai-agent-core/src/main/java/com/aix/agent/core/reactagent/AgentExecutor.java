package com.aix.agent.core.reactagent;

import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.factory.AgentRequest;
import com.aix.agent.core.factory.AgentResponse;

/**
 * @author Agao
 * @date 2025/12/13 17:54
 */
public interface AgentExecutor {

    /**
     * agent 提供者id
     *
     * @return
     */
    String getProviderId();

    /**
     * agent 执行
     *
     * @param req
     * @param properties
     * @return
     */
    AgentResponse execute(AgentRequest req, LLMProperties properties);

}
