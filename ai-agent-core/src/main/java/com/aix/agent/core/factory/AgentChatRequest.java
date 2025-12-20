package com.aix.agent.core.factory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.ai.chat.messages.UserMessage;

import java.util.List;

/**
 * @Author Agao
 * @Date 2025/12/16 20:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AgentChatRequest extends AgentRequest {
    /**
     * 用户级别的提示词
     */
    private UserMessage userMessage;
    /**
     * 定义输出格式
     */
    private Class<?> outputType;
    /**
     * 定义输出格式
     */
    private String outputSchema;
}
