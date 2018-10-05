// StackedAreaChartSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StackedAreaChartSmpl extends Application {

    @Override
    public void start(Stage stage) throws Exception {
     
        stage.setTitle("StackedAreaChartSmpl");
        stage.setWidth(460);
        stage.setHeight(420);

        NumberAxis xAxis = new NumberAxis(2011, 2020, 1);
        NumberAxis yAxis = new NumberAxis(0, 4000, 100);
        StackedAreaChart<Number, Number> chart = new StackedAreaChart<>(xAxis, yAxis);

        // 男性人口のデータ
        XYChart.Series<Number, Number> dataM = new XYChart.Series<>();
        dataM.setName("男性");
        dataM.getData().add(new Data<>(2011, 1232));
        dataM.getData().add(new Data<>(2012, 1236));
        dataM.getData().add(new Data<>(2013, 1271));
        dataM.getData().add(new Data<>(2014, 1288));
        dataM.getData().add(new Data<>(2015, 1310));
        dataM.getData().add(new Data<>(2016, 1323));
        dataM.getData().add(new Data<>(2017, 1452));
        dataM.getData().add(new Data<>(2018, 1460));
        dataM.getData().add(new Data<>(2019, 1507));
        dataM.getData().add(new Data<>(2020, 1607));
        // 女性人口のデータ
        XYChart.Series<Number, Number> dataF = new XYChart.Series<>();
        dataF.setName("女性");
        dataF.getData().add(new Data<>(2011, 1122));
        dataF.getData().add(new Data<>(2012, 1182));
        dataF.getData().add(new Data<>(2013, 1236));
        dataF.getData().add(new Data<>(2014, 1296));
        dataF.getData().add(new Data<>(2015, 1322));
        dataF.getData().add(new Data<>(2016, 1356));
        dataF.getData().add(new Data<>(2017, 1466));
        dataF.getData().add(new Data<>(2018, 1589));
        dataF.getData().add(new Data<>(2019, 1699));
        dataF.getData().add(new Data<>(2020, 1725));
        chart.getData().add(dataM);
        chart.getData().add(dataF);
     
        chart.setTitle("人口推移");

        BorderPane root = new BorderPane();
        root.setCenter(chart);
     
        stage.setScene(new Scene(root));
        stage.show();
    }
}
