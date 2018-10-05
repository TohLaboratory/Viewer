// StackedBarChartSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StackedBarChartSmpl extends Application {
 
    @Override
    public void start(Stage stage) {
 
        stage.setTitle("StackedBarChartSmpl");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("�N�x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("�l��");
 
        StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
        barChart.setTitle("�Q���Ґ��̐���");

        XYChart.Series<String, Number> xyChartSeries1 = new XYChart.Series<>();
        xyChartSeries1.setName("�j��");
        xyChartSeries1.getData().add(new XYChart.Data<>("1960", 32));
        xyChartSeries1.getData().add(new XYChart.Data<>("1970", 45));
        xyChartSeries1.getData().add(new XYChart.Data<>("1980", 63));
        xyChartSeries1.getData().add(new XYChart.Data<>("1990", 89));
        xyChartSeries1.getData().add(new XYChart.Data<>("2000", 63));
        xyChartSeries1.getData().add(new XYChart.Data<>("2010", 85));
 
        XYChart.Series<String, Number> xyChartSeries2 = new XYChart.Series<>();
        xyChartSeries2.setName("����");
        xyChartSeries2.getData().add(new XYChart.Data<>("1960", 22));
        xyChartSeries2.getData().add(new XYChart.Data<>("1970", 23));
        xyChartSeries2.getData().add(new XYChart.Data<>("1980", 23));
        xyChartSeries2.getData().add(new XYChart.Data<>("1990", 29));
        xyChartSeries2.getData().add(new XYChart.Data<>("2000", 25));
        xyChartSeries2.getData().add(new XYChart.Data<>("2010", 26));

        barChart.getData().add(xyChartSeries1);
        barChart.getData().add(xyChartSeries2);
 
        HBox root = new HBox();
        root.getChildren().add(barChart);
        Scene scene = new Scene(root, 540, 400);
        stage.setScene(scene);
        stage.show();
    }
}
