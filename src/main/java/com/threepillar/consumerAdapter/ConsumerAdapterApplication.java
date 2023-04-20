package com.threepillar.consumerAdapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerAdapterApplication {

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerAdapterApplication.class, args);
    }


}
