package com.aix.agent.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.aix.agent")
public class AiAgentClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiAgentClientApplication.class, args);
    }
}
