package com.aix.agent.core.reactagent.chat;

import com.aix.agent.core.enums.AgentType;
import com.aix.agent.core.reactagent.AgentExecutor;

/**
 * @Author Agao
 * @Date 2025/12/17 19:46
 */
public interface AgentChatExecutor extends AgentExecutor {

    /**
     * 获取agent 名称
     *
     * @return agent 名称
     */
    String agentName();

    /**
     * 获取agent 类型
     *
     * @return @see AgentType
     */
    default String getAgentType() {
        return AgentType.CHAT.getType();
    }


}
