// q8_3.java 

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class q8_3 extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("q8_3");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("èÊé‘íËàı");
        NumberAxis yAxis = new NumberAxis(500, 2200, 100);
        yAxis.setLabel("îrãCó ");

        BubbleChart<Number, Number> bubbleChart = new BubbleChart<Number, Number>(xAxis, yAxis);
        bubbleChart.setTitle("é‘óºÇÃï™ïz");
        XYChart.Series<Number, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("ë‰êî");

        xyChartSeries.getData().add(new XYChart.Data<>(2, 660, 3/3));
        xyChartSeries.getData().add(new XYChart.Data<>(5, 1000, 8/3));
        xyChartSeries.getData().add(new XYChart.Data<>(5, 1600, 6/3));
        xyChartSeries.getData().add(new XYChart.Data<>(4, 1600, 3/3));
        xyChartSeries.getData().add(new XYChart.Data<>(5, 2000, 3/3));
        xyChartSeries.getData().add(new XYChart.Data<>(7, 2000, 4/3));

        bubbleChart.getData().add(xyChartSeries);

        HBox root = new HBox();
        root.getChildren().add(bubbleChart);
        Scene scene = new Scene(root, 560, 400);
        stage.setScene(scene);
        stage.show();
    }
}
