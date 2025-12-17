package com.aix.agent.core.llm.chat.impl;

import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.factory.LLMRequest;
import com.aix.agent.core.factory.LLMResponse;
import com.aix.agent.core.llm.chat.LLMChatExecutor;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.stereotype.Component;

/**
 * 通义模型Api
 *
 * @Author Agao
 * @Date 2025/12/16 20:49
 */
@Component("dashScopeLLMExecutor")
public class DashScopeLLMExecutor implements LLMChatExecutor {

    @Override
    public LLMResponse execute(LLMRequest req, LLMProperties properties) {
        String model = req.getModel();
        // 获取执行模型
//        ChatModel chatModel = LLMApiFactory.getChatModel(model);
//        // 提示词
//        Prompt prompt = Prompt.builder().messages().build();
//        // 结果
//        ChatResponse chatResponse = chatModel.call(prompt);
//
//
//        LLMResponse llmResponse = new LLMResponse();
//
//        llmResponse.setContent(chatResponse.getResult().getOutput().getText());
//        llmResponse.setUsage(chatResponse.getMetadata().getUsage().toString());
//        llmResponse.setCalledToolCalls();
//        llmResponse.setCalledToolResponses();

        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(properties.getApiKey())
                .build();

        DashScopeChatModel chatModel = DashScopeChatModel.builder().dashScopeApi(dashScopeApi).build();


        return null;
    }

}
