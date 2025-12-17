package com.aix.agent.core.config;

import com.aix.agent.core.enums.LLMType;
import com.aix.agent.core.errorcode.LLMErrorCode;
import com.aix.agent.core.exception.LLMException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Agao
 * @date 2025/12/17 11:09
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(LLMModelProperties.class)
public class LLMAutoConfiguration implements ApplicationRunner {
    private final LLMModelProperties llmModelProperties;

    /**
     * LLM模型配置
     */
    private static final Map<String, Map<String, Map<String, LLMProperties>>> LLM_MODEL_MAP = new HashMap<>();

    /**
     * 获取LLM模型配置
     *
     * @param modelType 模型类型 @see LLMType
     * @param platform  模型所属平台 @see LLMPlatform
     * @param model     模型
     * @return 配置
     */
    public static LLMProperties getLLMProperties(String modelType, String platform, String model) {
        return Optional.ofNullable(LLM_MODEL_MAP.get(modelType))
                .map(map -> map.get(platform))
                .map(map -> map.get(model))
                .orElseThrow(() -> new LLMException(LLMErrorCode.LLM_NOT_EXIST));
    }

    public static Map<String, Map<String, Map<String, LLMProperties>>> getLLMModelMap() {
        return LLM_MODEL_MAP;
    }

    private void joinLLMModelMap(LLMType llmType, List<LLMProperties> LLMPropertiesList) {
        if (CollectionUtils.isEmpty(LLMPropertiesList)) {
            return;
        }
        Map<String, Map<String, LLMProperties>> llmMap = new HashMap<>();
        for (LLMProperties llmProperties : LLMPropertiesList) {
            String platform = llmProperties.getPlatform();
            String model = llmProperties.getModel();

            Map<String, LLMProperties> modelMap = new HashMap<>();
            modelMap.put(model, llmProperties);
            llmMap.put(platform, modelMap);
        }
        LLM_MODEL_MAP.put(llmType.getType(), llmMap);
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

        joinLLMModelMap(LLMType.CHAT, chatLLMList);
        joinLLMModelMap(LLMType.IMAGE, imageLLMList);
    }
}
