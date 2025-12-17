package com.aix.agent.client.apiclient;

import com.aix.agent.api.domain.User;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/test")
public interface TestClient {

    @GetExchange("/getUser")
    User getUser();
}
