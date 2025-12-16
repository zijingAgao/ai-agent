package com.aix.agent.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Agao
 * @Date 2025/12/16 21:18
 */
@RestController
public class AskController {

    @GetMapping("/ask")
    public String ask(String question) {

        return "hello world";
    }
}
