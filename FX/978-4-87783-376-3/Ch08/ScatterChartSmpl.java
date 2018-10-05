// ScatterChartSmpl

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScatterChartSmpl extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("ScatterChartSmpl");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("‰w‚©‚ç‚Ì‹——£");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("‰¿Ši");

        ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(xAxis, yAxis);
        scatterChart.setTitle("‰w‚©‚ç‚Ì‹——£‚Æ‰¿Ši");
        XYChart.Series<Number, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("‰¿Šii–œ‰~j");

        xyChartSeries.getData().add(new XYChart.Data<>(5, 4500));
        xyChartSeries.getData().add(new XYChart.Data<>(8, 3830));
        xyChartSeries.getData().add(new XYChart.Data<>(12, 3650));
        xyChartSeries.getData().add(new XYChart.Data<>(13, 3400));
        xyChartSeries.getData().add(new XYChart.Data<>(14, 3010));
        xyChartSeries.getData().add(new XYChart.Data<>(14, 3550));
        xyChartSeries.getData().add(new XYChart.Data<>(14, 3410));
        xyChartSeries.getData().add(new XYChart.Data<>(15, 3200));
        xyChartSeries.getData().add(new XYChart.Data<>(17, 3100));
        xyChartSeries.getData().add(new XYChart.Data<>(19, 2980));
        xyChartSeries.getData().add(new XYChart.Data<>(22, 2900));
        xyChartSeries.getData().add(new XYChart.Data<>(28, 2200));

        scatterChart.getData().add(xyChartSeries);

        HBox root = new HBox();
        root.getChildren().add(scatterChart);
        Scene scene = new Scene(root, 560, 400);
        stage.setScene(scene);
        stage.show();
    }
}
