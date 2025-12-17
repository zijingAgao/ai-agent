package com.aix.agent.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Agao
 * @date 2025/12/17 11:15
 */
@Data
@ConfigurationProperties("aix.llm")
public class LLMModelProperties {
    /**
     * chat model
     */
    private List<LLMProperties> chat;


    /**
     * image model
     */
    private List<LLMProperties> image;
}
