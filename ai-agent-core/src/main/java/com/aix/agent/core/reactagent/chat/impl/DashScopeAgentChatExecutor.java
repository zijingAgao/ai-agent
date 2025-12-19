package com.aix.agent.core.reactagent.chat.impl;

import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.enums.AgentPlatform;
import com.aix.agent.core.factory.AgentRequest;
import com.aix.agent.core.factory.AgentResponse;
import com.aix.agent.core.reactagent.chat.AgentChatExecutor;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.checkpoint.savers.MemorySaver;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

/**
 * 通义模型Api
 *
 * @Author Agao
 * @Date 2025/12/16 20:49
 */
@Slf4j
public class DashScopeAgentChatExecutor implements AgentChatExecutor {

    @Override
    public String agentName() {
        return "dash-scope-chat-agent";
    }
    /**
     * 模型提供者 id
     */
    @Override
    public String getProviderId() {
        return AgentPlatform.DASH_SCOPE.getPlatform() + "-" + this.getAgentType();
    }

    @Override
    public AgentResponse execute(AgentRequest req, LLMProperties properties) {
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

        ReactAgent reactAgent = ReactAgent.builder()
                .name("reactAgent")
                .model(chatModel)
                .tools().hooks()
                .outputType(String.class)
                .saver(new MemorySaver())
                .build();

//        AssistantMessage called = reactAgent.call("请告诉我中国有多少个名族");
//        ReactAgent reactAgent1 = new ReactAgent();


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
