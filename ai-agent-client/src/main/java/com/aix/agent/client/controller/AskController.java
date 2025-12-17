package com.aix.agent.client.controller;

import com.aix.agent.client.service.AskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Agao
 * @Date 2025/12/16 21:18
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AskController {

    private final AskService askService;

    @GetMapping("/ask")
    public String ask(@RequestParam("question") String question) {
        askService.ask(question);
        return "hello world";
    }
}
