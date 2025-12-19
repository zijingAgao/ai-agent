package com.aix.agent.core.config;

import com.aix.agent.core.enums.AgentType;
import com.aix.agent.core.errorcode.LLMErrorCode;
import com.aix.agent.core.exception.AgentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Agao
 * @date 2025/12/17 11:09
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(LLMModelProperties.class)
public class LLMAutoConfiguration implements ApplicationRunner {
    private final LLMModelProperties llmModelProperties;

    /**
     * LLM模型配置
     * <chat-dashscope-model, LLMProperties>
     */
//    private static final Map<String, Map<String, Map<String, LLMProperties>>> LLM_MODEL_MAP = new HashMap<>();
    private static final Map<String, LLMProperties> LLM_MODEL_MAP = new HashMap<>();

    /**
     * 获取LLM模型配置
     *
     * @param modelType 模型类型 @see LLMType
     * @param platform  模型所属平台 @see LLMPlatform
     * @param model     模型
     * @return 配置
     */
    public static LLMProperties getLLMProperties(String modelType, String platform, String model) {
        String modelKey = buildModelKey(modelType, platform, model);
        LLMProperties llmProperties = LLM_MODEL_MAP.get(modelKey);
        if (llmProperties == null) {
            throw new AgentException(LLMErrorCode.LLM_NOT_EXIST);
        }

        return llmProperties;
    }

    public static Map<String, LLMProperties> getLLMModelMap() {
        return LLM_MODEL_MAP;
    }

    private void joinLLMModelMap(AgentType agentType, List<LLMProperties> LLMPropertiesList) {
        if (CollectionUtils.isEmpty(LLMPropertiesList)) {
            return;
        }

        for (LLMProperties llmProperties : LLMPropertiesList) {
            String platform = llmProperties.getPlatform();
            String model = llmProperties.getModel();

            String key = buildModelKey(agentType.getType(), platform, model);
            LLM_MODEL_MAP.put(key, llmProperties);
        }

    }

    private static String buildModelKey(String modelType, String platform, String model) {
        return modelType + "-" + platform + "-" + model;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("load llm model properties");
        if (llmModelProperties == null) {
            log.warn("llm model properties is null");
            return;
        }
        List<LLMProperties> chatLLMList = llmModelProperties.getChat();
        List<LLMProperties> imageLLMList = llmModelProperties.getImage();

        joinLLMModelMap(AgentType.CHAT, chatLLMList);
        joinLLMModelMap(AgentType.IMAGE, imageLLMList);
    }
}
