package com.aix.agent.core.factory;

import lombok.Data;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.ToolResponseMessage;

import java.util.List;

/**
 * @Author Agao
 * @Date 2025/12/16 20:43
 */
@Data
public class LLMResponse {
    /**
     * 思考过程
     */
    private String thinkContent;

    /**
     * 输出内容
     */
    private String content;

    /**
     * 使用情况(json)
     */
    private String usage;

    /**
     * 调用工具
     */
    private List<AssistantMessage.ToolCall> calledToolCalls;

    /**
     * 工具调用结果
     */
    private List<ToolResponseMessage.ToolResponse> calledToolResponses;
}
