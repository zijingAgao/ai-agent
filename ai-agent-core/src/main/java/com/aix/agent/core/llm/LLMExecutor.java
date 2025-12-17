package com.aix.agent.core.llm;

import com.aix.agent.core.config.LLMProperties;
import com.aix.agent.core.factory.LLMRequest;
import com.aix.agent.core.factory.LLMResponse;

/**
 * @author Agao
 * @date 2025/12/13 17:54
 */
public interface LLMExecutor {

    LLMResponse execute(LLMRequest req, LLMProperties properties);

}
