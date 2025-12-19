package com.aix.agent.client.service;

import com.aix.agent.core.factory.AgentFactory;
import com.aix.agent.core.factory.AgentChatRequest;
import com.aix.agent.core.factory.AgentResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * @Author Agao
 * @Date 2025/12/13 19:51
 */
@Service
@RequiredArgsConstructor
public class AskService {


    public String ask(String question) {

        AgentChatRequest agentChatRequest = new AgentChatRequest();
        agentChatRequest.setPlatform("dashscope");
        agentChatRequest.setModel("qwen-plus");
        agentChatRequest.setModelType("chat");
        agentChatRequest.setContent(question);

        AgentResponse response = AgentFactory.execute(agentChatRequest);
        return "hello world";
    }

}
