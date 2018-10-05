// q8_2.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class q8_2 extends Application {
 
    @Override
    public void start(Stage stage) {
 
        stage.setTitle("q8_2");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("プログラミング言語");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("人数");
 
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("プログラミング言語のユーザー数");
        XYChart.Series<String, Number> xyChartSeries = new XYChart.Series<>();
        xyChartSeries.setName("ユーザー数");
 
        xyChartSeries.getData().add(new XYChart.Data<>("Java", 23560));
        xyChartSeries.getData().add(new XYChart.Data<>("C/C++", 18563));
        xyChartSeries.getData().add(new XYChart.Data<>("JavaScript", 15320));
        xyChartSeries.getData().add(new XYChart.Data<>("VB", 12005));
        xyChartSeries.getData().add(new XYChart.Data<>("その他", 8563));
 
        barChart.getData().add(xyChartSeries);
 
        HBox root = new HBox();
        root.getChildren().add(barChart);
        Scene scene = new Scene(root, 560, 400);
        stage.setScene(scene);
        stage.show();
    }
}
