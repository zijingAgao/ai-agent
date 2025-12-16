package com.aix.agent.core.llm;

import com.aix.agent.core.dto.LLMRequest;
import com.aix.agent.core.dto.LLMResponse;

/**
 * @author Agao
 * @date 2025/12/13 17:54
 */
public interface LLMApi {

    LLMResponse ask(LLMRequest req);

}
