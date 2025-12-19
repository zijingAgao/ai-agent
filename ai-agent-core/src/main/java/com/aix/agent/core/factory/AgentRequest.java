package com.aix.agent.core.factory;

import lombok.Data;

/**
 * @author Agao
 * @date 2025/12/19 10:20
 */
@Data
public class AgentRequest {
    /**
     * 所属平台
     */
    private String platform;
    /**
     * 模型名称
     */
    private String model;
    /**
     * 模型类型 @see com.aix.agent.core.enums.ModelType
     */
    private String modelType;
}
