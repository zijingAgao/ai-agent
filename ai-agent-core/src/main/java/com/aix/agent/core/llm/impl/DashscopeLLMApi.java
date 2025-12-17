package com.aix.agent.core.llm.impl;

import com.aix.agent.core.dto.LLMRequest;
import com.aix.agent.core.dto.LLMResponse;
import com.aix.agent.core.llm.LLMApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 通义模型Api
 *
 * @Author Agao
 * @Date 2025/12/16 20:49
 */
@Component
@RequiredArgsConstructor
public class DashscopeLLMApi implements LLMApi {

    private final DashScopeChatModel dashScopeModel;
    @Override
    public LLMResponse ask(LLMRequest req) {
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


        return null;
    }
}
