package com.mechanitist.demo.stockclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public WebClientStockClient webClientStockClient(WebClient client){
        return new WebClientStockClient(client);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(){
       return WebClient.create();
    }
}
