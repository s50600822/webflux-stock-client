package com.mechanitist.stockui;

import javafx.application.Application;
import javafx.scene.chart.Chart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockUiApplication {

    public static void main(String[] args) {
        Application.launch(ChartApplication.class);
    }

}
