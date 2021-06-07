package com.mechanitist.demo.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientTestIntegrationTest {
    private WebClient client = WebClient.create();

    @Test
    void shoulRetrieveStockPriceFromTheService() {
        final String SB = "symbol";
        WebClientStockClient clientStockClient = new WebClientStockClient(client);
        Flux<StockPrice> prices = clientStockClient.priceFor(SB);

        Assertions.assertNotNull(prices);
        Flux<StockPrice> take5 = prices.take(5);
        Assertions.assertEquals(5, take5.count().block());
        Assertions.assertEquals(5, take5.count().block());
        Assertions.assertEquals(5, take5.count().block());

        Assertions.assertEquals(SB, take5.blockFirst().getSymbol());
    }
}
