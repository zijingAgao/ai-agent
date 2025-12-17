package com.aix.agent.core.config;

import lombok.Data;

/**
 * @author Agao
 * @date 2025/12/13 17:52
 */
@Data
public class LLMProperties {
    /**
     * 模型名称
     */
    private String model;
    /**
     * api key
     */
    private String apiKey;
    /**
     * api-url
     */
    private String baseUrl;
    /**
     * 所属平台
     */
    private String platform;
    /**
     * 执行器bean
     */
    private String executor;
    /**
     * 描述
     */
    private String desc;
    /**
     * 是否启用
     */
    private boolean enable = false;
}
