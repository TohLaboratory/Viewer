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
                        new PieChart.Data("�p��",  13),
                        new PieChart.Data("����",  10),
                        new PieChart.Data("���[����",  22),
                        new PieChart.Data("�J���[",  20));
        final PieChart chart = new PieChart(pieData);
        chart.setTitle("�����͂ȂɁH");

        BorderPane root = new BorderPane();
        root.setCenter(chart);
        
        stage.setScene(new Scene(root));
        stage.show();
    }
}
