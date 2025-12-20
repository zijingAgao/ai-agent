package com.aix.agent.core.reactagent.chat.impl;

import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.enums.AgentPlatform;
import com.aix.agent.core.errorcode.AgentErrorCode;
import com.aix.agent.core.exception.AgentException;
import com.aix.agent.core.factory.AgentChatRequest;
import com.aix.agent.core.factory.AgentRequest;
import com.aix.agent.core.factory.AgentResponse;
import com.aix.agent.core.reactagent.chat.AgentChatExecutor;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.dashscope.spec.DashScopeApiSpec;
import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.RunnableConfig;
import com.alibaba.cloud.ai.graph.agent.Builder;
import com.alibaba.cloud.ai.graph.agent.ReactAgent;
import com.alibaba.cloud.ai.graph.checkpoint.savers.MemorySaver;
import com.alibaba.cloud.ai.graph.exception.GraphRunnerException;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * 通义模型Api
 *
 * @Author Agao
 * @Date 2025/12/16 20:49
 */
@Slf4j
public class DashScopeAgentChatExecutor implements AgentChatExecutor {

    private static final String AGENT_NAME = "dash-scope-chat-agent";

    @Override
    public String agentName() {
        return AGENT_NAME;
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



        AgentChatRequest agentChatRequest = (AgentChatRequest) req;
        String threadId = req.getThreadId();
        List<ToolCallback> toolCallbacks = req.getToolCallbacks();
        UserMessage userMessage = agentChatRequest.getUserMessage();
        Class<?> outputType = agentChatRequest.getOutputType();
        String outputSchema = agentChatRequest.getOutputSchema();


        DashScopeChatModel chatModel = buildDashScopeChatModel(properties, toolCallbacks);

        // threadId 是给定对话的唯一标识符
        RunnableConfig runnableConfig = null;
        if (StringUtils.hasText(threadId)) {
            runnableConfig = RunnableConfig.builder()
                    .threadId(threadId)
                    // 可以传递给调用tool 获取上下文参数使用
                    .addMetadata("user_id", "1")
                    .build();
        }

        Builder defaultAgentBuilder = ReactAgent.builder()
                .name(AGENT_NAME)
                .model(chatModel)
                .hooks()
                .outputType(outputType)
                .outputSchema(outputSchema)
                .saver(new MemorySaver());

        if (StringUtils.hasText(outputSchema)) {
            defaultAgentBuilder.outputType(null);
        }

        if (!CollectionUtils.isEmpty(toolCallbacks)) {
            defaultAgentBuilder.tools(toolCallbacks);
        }

        ReactAgent reactAgent = defaultAgentBuilder.build();



        AssistantMessage assistantMessage;
        try {


//
//            ChatResponse chatResponse = chatModel.call(Prompt.builder().messages(userMessage).build());
//            System.out.println(JSONObject.toJSONString(chatResponse));
//
            Optional<OverAllState> invoke = reactAgent.invoke(userMessage, runnableConfig);
//            assistantMessage = reactAgent.call(userMessage, runnableConfig);


//            System.out.println(JSONObject.toJSONString());
        } catch (GraphRunnerException e) {
            log.error("dash scope call error", e);
            throw new AgentException(AgentErrorCode.LLM_CALL_ERROR);
        }



        return null;
    }


    private DashScopeApi buildDashScopeApi(LLMProperties properties) {
        return DashScopeApi.builder()
                .apiKey(properties.getApiKey())
                .build();
    }


    /**
     * todo: 模型配置 Options
     *
     * @param properties
     * @return
     */
    private DashScopeChatModel buildDashScopeChatModel(LLMProperties properties, List<ToolCallback> toolCallbacks) {
        DashScopeApi dashScopeApi = buildDashScopeApi(properties);

        return DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .defaultOptions(DashScopeChatOptions.builder()
                        .model(properties.getModel())
                        .temperature(0.4)
                        .maxToken(200)
                        .toolCallbacks(toolCallbacks)
                        .build())
                .build();
    }

    private Prompt buildPrompt(String userMsg) {
        return Prompt.builder().messages(
                SystemMessage.builder().text("You are a helpful assistant.").build(),
                UserMessage.builder().text(userMsg).build()
        ).build();
    }

}
