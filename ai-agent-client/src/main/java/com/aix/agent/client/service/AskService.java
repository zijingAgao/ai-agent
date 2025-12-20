package com.aix.agent.client.service;

import com.aix.agent.client.tool.WeatherForLocationTool;
import com.aix.agent.core.factory.AgentFactory;
import com.aix.agent.core.factory.AgentChatRequest;
import com.aix.agent.core.factory.AgentResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Agao
 * @Date 2025/12/13 19:51
 */
@Service
@RequiredArgsConstructor
public class AskService {


    public String ask(String question) {
        String customSchema = """
                请按照以下JSON格式输出：
                {
                    "title": "标题",
                    "content": "内容",
                    "style": "风格"
                }
                """;
        // 下面是输出类的字段注释 title:标题，content:内容,style:风格
        AgentChatRequest agentChatRequest = new AgentChatRequest();
        agentChatRequest.setPlatform("dashscope");
        agentChatRequest.setModel("qwen-plus");
        agentChatRequest.setModelType("chat");
        agentChatRequest.setUserMessage(UserMessage.builder().text("what is the weather in chendu").build());
        agentChatRequest.setOutputType(Poetry.class);


        ToolCallback getWeatherTool = FunctionToolCallback
                .builder("getWeatherForLocation", new WeatherForLocationTool())
                .description("Get weather for a given city")
                .inputType(String.class)
                .build();
        agentChatRequest.setToolCallbacks(List.of(getWeatherTool));

        AgentResponse response = AgentFactory.execute(agentChatRequest);
        return "hello world";
    }

    @Data
    public static class Poetry{
        private String punnyResponse;
        private String poetryConditions;
        private String title;
        private String content;
        private String style;
    }
}
