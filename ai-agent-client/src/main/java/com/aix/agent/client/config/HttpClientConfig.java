package com.aix.agent.client.config;

import com.aix.agent.client.apiclient.TestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder()
//                .baseUrl("http://localhost:8888")
//                .build();
//    }

    @Bean
    TestClient userClient(WebClient.Builder builder) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8888").build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        TestClient client = factory.createClient(TestClient.class);
        return client;
//        return HttpServiceProxyFactory
//                .builder(WebClientAdapter.forClient(builder.build()))
//                .build()
//                .createClient(TestClient.class);
    }
}
