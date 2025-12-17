package com.aix.agent.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Agao
 * @date 2025/12/17 16:56
 */
@Getter
@AllArgsConstructor
public enum LLMPlatform {
    OPENAI("openai"),
    DASH_SCOPE("dashscope"),
    DEEP_SEEK("deepseek"),

    ;


    private final String platform;
}
