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
        xAxis.setLabel("�l���v�i���l�j");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("�ʐρiha�j");

        BubbleChart<Number, Number> bubbleChart = new BubbleChart<Number, Number>(xAxis, yAxis);
        bubbleChart.setTitle("�ʐ�/�l���Ǝ{�ݐ�");
        XYChart.Series<Number, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("�{�ݐ�");

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
