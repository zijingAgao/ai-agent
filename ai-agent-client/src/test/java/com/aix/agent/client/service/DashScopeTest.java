package com.aix.agent.client.service;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.tool.ObservableToolCallingManager;
import io.micrometer.observation.ObservationRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.DefaultToolCallingManager;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.execution.ToolExecutionException;
import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;
import org.springframework.ai.tool.resolution.ToolCallbackResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;

/**
 * @Author Agao
 * @Date 2025/12/17 21:48
 */
@SpringBootTest
public class DashScopeTest {
    @Autowired
//    private ToolCallingManager toolCallingManager;
    private RetryTemplate retryTemplate;
    private ObservationRegistry observationRegistry;

    @Test
    void test() {
        ObservableToolCallingManager observableToolCallingManager = new ObservableToolCallingManager(observationRegistry,
                new ToolCallbackResolver() {
                    @Override
                    public ToolCallback resolve(String toolName) {
                        return null;
                    }
                },
                new ToolExecutionExceptionProcessor() {
                    @Override
                    public String process(ToolExecutionException exception) {
                        return "";
                    }
                }
                );


        var dashScopeApi = DashScopeApi.builder()
                .apiKey("sk-ed3405c0236b4d25a344d0b7e87ffded")
                .build();
        var dashScopeChatOptions = DashScopeChatOptions.builder()
                .model("qwen-plus")
                .temperature(0.4)
                .maxToken(200)
                .build();
        var chatModel = new DashScopeChatModel(dashScopeApi, dashScopeChatOptions,
                observableToolCallingManager, retryTemplate, observationRegistry);

        ChatResponse response = chatModel.call(
                new Prompt("Generate the names of 5 famous pirates."));

        System.out.println(response);

    }
}
