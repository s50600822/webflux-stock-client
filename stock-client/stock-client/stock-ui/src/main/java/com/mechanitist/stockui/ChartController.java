package com.mechanitist.stockui;

import com.mechanitist.demo.stockclient.StockPrice;
import com.mechanitist.demo.stockclient.WebClientStockClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Consumer;

import static javafx.application.Platform.runLater;
import static javafx.scene.chart.XYChart.Series;

@Component
public class ChartController implements Consumer<StockPrice> {
    @FXML
    public LineChart<String, Double> chart;
    private final WebClientStockClient webClientStockClient;
    private ObservableList<XYChart.Data<String, Double>> seriesData = FXCollections.observableArrayList();

//    public ChartController(WebClientStockClient webClientStockClient) {
//        this.webClientStockClient = webClientStockClient;
//    }

    public ChartController() {
        this.webClientStockClient = new WebClientStockClient(WebClient.builder().build());
    }

    @FXML
    public void initialize() {
        String sb = "SB";

        ObservableList<XYChart.Series<String,Double>> data = FXCollections.observableArrayList();
        data.add(new Series<>(sb, seriesData));
        chart.setData(data);

        webClientStockClient.priceFor(sb).subscribe(this);
    }

    @Override
    public void accept(StockPrice stockPrice) {
        runLater(() ->
                seriesData.add(
                        new Data<>(
                                String.valueOf(stockPrice.getTime().getSecond()),
                                stockPrice.getPrice()
                        )
                )
        );
    }
}
