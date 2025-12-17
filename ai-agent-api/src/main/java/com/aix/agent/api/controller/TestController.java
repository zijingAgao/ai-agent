package com.aix.agent.api.controller;

import com.aix.agent.api.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getUser")
    public User getUser(){
        User user = User.builder()
                .id(System.currentTimeMillis())
                .name("中国移动")
                .build();

        return user;
    }
}
