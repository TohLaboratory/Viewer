// BubbleChartSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BubbleChartSmpl extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("BubbleChartSmpl");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("人口」（万人）");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("面積（ha）");

        BubbleChart<Number, Number> bubbleChart = new BubbleChart<Number, Number>(xAxis, yAxis);
        bubbleChart.setTitle("面積/人口と施設数");
        XYChart.Series<Number, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("施設数");

        xyChartSeries.getData().add(new XYChart.Data<>(160, 102, 5));
        xyChartSeries.getData().add(new XYChart.Data<>(190, 453, 12));
        xyChartSeries.getData().add(new XYChart.Data<>(180, 363, 15));
        xyChartSeries.getData().add(new XYChart.Data<>(156, 256, 7));
        xyChartSeries.getData().add(new XYChart.Data<>(210, 463, 18));
        xyChartSeries.getData().add(new XYChart.Data<>(155, 326, 14));

        bubbleChart.getData().add(xyChartSeries);

        HBox root = new HBox();
        root.getChildren().add(bubbleChart);
        Scene scene = new Scene(root, 560, 400);
        stage.setScene(scene);
        stage.show();
    }
}
