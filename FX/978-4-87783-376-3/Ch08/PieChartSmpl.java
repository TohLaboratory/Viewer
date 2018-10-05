// PieChartSmpl.java

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PieChartSmpl extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("PieChartSmpl");
        stage.setWidth(260);
        stage.setHeight(180);

        ObservableList<PieChart.Data> pieData =
                FXCollections.observableArrayList(
                        new PieChart.Data("パン",  13),
                        new PieChart.Data("そば",  10),
                        new PieChart.Data("ラーメン",  22),
                        new PieChart.Data("カレー",  20));
        final PieChart chart = new PieChart(pieData);
        chart.setTitle("お昼はなに？");

        BorderPane root = new BorderPane();
        root.setCenter(chart);
        
        stage.setScene(new Scene(root));
        stage.show();
    }
}
