package com.aix.agent.core.factory;

import lombok.Data;

/**
 * @Author Agao
 * @Date 2025/12/16 20:43
 */
@Data
public class LLMRequest {
    private String platform;
    private String model;
    private String modelType;
    private String content;
}
