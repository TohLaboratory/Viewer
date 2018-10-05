// q8_1.java

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class q8_1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("q8_1");
        stage.setWidth(300);
        stage.setHeight(220);

        ObservableList<PieChart.Data> pieData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Java",  23560),
                        new PieChart.Data("C/C++",  18563),
                        new PieChart.Data("JavaScript",  15320),
                        new PieChart.Data("VB", 12005),
                        new PieChart.Data("その他", 8563));
        final PieChart chart = new PieChart(pieData);
        chart.setTitle("プログラミング言語のユーザー数");
        chart.setStartAngle(90.0);

        BorderPane root = new BorderPane();
        root.setCenter(chart);
        
        stage.setScene(new Scene(root));
        stage.show();
    }
}