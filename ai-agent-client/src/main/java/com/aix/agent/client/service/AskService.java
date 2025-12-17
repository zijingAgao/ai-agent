package com.aix.agent.client.service;

import com.aix.agent.core.factory.LLMApiFactory;
import com.aix.agent.core.factory.LLMRequest;
import com.aix.agent.core.factory.LLMResponse;
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

        LLMRequest llmRequest = new LLMRequest();
        llmRequest.setPlatform("dashscope");
        llmRequest.setModel("qwen-plus");
        llmRequest.setModelType("chat");
        llmRequest.setContent(question);

        LLMResponse response = LLMApiFactory.execute(llmRequest);
        return "hello world";
    }

}
