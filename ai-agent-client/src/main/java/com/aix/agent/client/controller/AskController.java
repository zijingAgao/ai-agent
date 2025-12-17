package com.aix.agent.client.controller;

import com.aix.agent.api.domain.User;
import com.aix.agent.client.apiclient.TestClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Agao
 * @Date 2025/12/16 21:18
 */
@RestController
public class AskController {

    @Resource
    private TestClient testClient;

    @GetMapping("/ask")
    public String ask(@RequestParam("question") String question) {
        return "hello world";
    }

    @GetMapping("/test")
    public User getUser() {
        return testClient.getUser();
    }
}
