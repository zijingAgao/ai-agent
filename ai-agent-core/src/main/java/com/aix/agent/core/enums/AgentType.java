package com.aix.agent.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Agao
 * @date 2025/12/17 16:37
 */
@Getter
@AllArgsConstructor
public enum AgentType {

    CHAT("chat"),
    IMAGE("image"),
    AUDIO("audio");

    private final String type;
}
