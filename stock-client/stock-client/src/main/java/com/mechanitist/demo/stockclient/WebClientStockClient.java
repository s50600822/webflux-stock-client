package com.mechanitist.demo.stockclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;

@Slf4j
public class WebClientStockClient {
    private final WebClient client;

    public WebClientStockClient(WebClient client) {
        this.client = client;
    }

    public Flux<StockPrice> priceFor(String symbol) {
        return client.get().
                uri("http://localhost:8080/stock/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retryWhen(Retry.backoff(5, Duration.ofMillis(200)))
                .doOnError(IOException.class, e -> log.error("ho ho ho", e));
    }
}
