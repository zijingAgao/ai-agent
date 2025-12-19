package com.aix.agent.core.factory;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Agao
 * @Date 2025/12/16 20:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AgentChatRequest extends AgentRequest {
    private String content;
}
