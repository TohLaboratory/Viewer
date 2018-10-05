// LineChartSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LineChartSmpl extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("LineChartSmpl");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("年齢");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("発症率（%）");

        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("年齢と発症率");
        XYChart.Series<Number, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("発症率");

        xyChartSeries.getData().add(new XYChart.Data<>(15, 22));
        xyChartSeries.getData().add(new XYChart.Data<>(20, 31));
        xyChartSeries.getData().add(new XYChart.Data<>(25, 36));
        xyChartSeries.getData().add(new XYChart.Data<>(30, 32));
        xyChartSeries.getData().add(new XYChart.Data<>(35, 35));
        xyChartSeries.getData().add(new XYChart.Data<>(40, 36));
        xyChartSeries.getData().add(new XYChart.Data<>(45, 40));
        xyChartSeries.getData().add(new XYChart.Data<>(50, 42));
        xyChartSeries.getData().add(new XYChart.Data<>(55, 45));
        xyChartSeries.getData().add(new XYChart.Data<>(60, 46));

        lineChart.getData().add(xyChartSeries);

        HBox root = new HBox();
        root.getChildren().add(lineChart);
        Scene scene = new Scene(root, 560, 400);
        stage.setScene(scene);
        stage.show();
    }
}
