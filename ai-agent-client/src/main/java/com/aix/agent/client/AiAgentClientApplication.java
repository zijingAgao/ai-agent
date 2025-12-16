package com.aix.agent.client;

import com.alibaba.cloud.ai.autoconfigure.dashscope.DashScopeAudioSpeechAutoConfiguration;
import com.alibaba.cloud.ai.autoconfigure.dashscope.DashScopeAudioTranscriptionAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DashScopeAudioTranscriptionAutoConfiguration.class,DashScopeAudioSpeechAutoConfiguration.class})
@ComponentScan(basePackages = "com.aix.agent")
public class AiAgentClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiAgentClientApplication.class, args);
    }
}
