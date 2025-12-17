package com.aix.agent.core.llm.chat.impl;

import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.factory.LLMRequest;
import com.aix.agent.core.factory.LLMResponse;
import com.aix.agent.core.llm.chat.LLMChatExecutor;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.common.DashScopeApiConstants;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingManager;
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
        // todo： DashScopeChatOptions 参数 ,不要给 baseUrl
        DashScopeApi dashScopeApi = DashScopeApi.builder()
                .apiKey(properties.getApiKey())
                .build();

        DashScopeChatModel chatModel = DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
//                .toolCallingManager()
                .defaultOptions(DashScopeChatOptions.builder()
                        .model(req.getModel())
                        .temperature(0.4)
                        .maxToken(200)
                        .build())
                .build();

        // 提示词
        Prompt prompt = Prompt.builder().messages(
                SystemMessage.builder().text("You are a helpful assistant.").build(),
                UserMessage.builder().text("请告诉我中国有多少个名族").build()
        ).build();

        ChatResponse chatResponse = chatModel.call(prompt);

//        LLMResponse llmResponse = new LLMResponse();
//
//        llmResponse.setContent(chatResponse.getResult().getOutput().getText());
//        llmResponse.setUsage(chatResponse.getMetadata().getUsage().toString());
//        llmResponse.setCalledToolCalls();
//        llmResponse.setCalledToolResponses();

        System.out.println(JSONObject.toJSONString(chatResponse));


        return null;
    }

}
