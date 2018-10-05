// BarChartSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BarChartSmpl extends Application {
 
    @Override
    public void start(Stage stage) {
 
        stage.setTitle("BarChartSmpl");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("”N“x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("“X•Ü”");
 
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("“X•Ü”‚Ì„ˆÚ");
        XYChart.Series<String, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("“X•Ü”");
 
        xyChartSeries.getData().add(new XYChart.Data<>("1960", 2102));
        xyChartSeries.getData().add(new XYChart.Data<>("1970", 2453));
        xyChartSeries.getData().add(new XYChart.Data<>("1980", 2363));
        xyChartSeries.getData().add(new XYChart.Data<>("1990", 2389));
        xyChartSeries.getData().add(new XYChart.Data<>("2000", 2563));
        xyChartSeries.getData().add(new XYChart.Data<>("2010", 2856));
 
        barChart.getData().add(xyChartSeries);
 
        HBox root = new HBox();
        root.getChildren().add(barChart);
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
