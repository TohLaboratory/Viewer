// BMI.java

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BMI extends Application {

    Label lblWv = new Label();
    Label lblHv = new Label();
    Slider sliderH = new Slider(100, 200, 170.0);
    Slider sliderW = new Slider(20, 120, 60.0);
    Label lblBMI = new Label("BMI");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("BMI");
        stage.setWidth(400);
        stage.setHeight(230);

        // 身長のラベルとスライダー
        Label lblH = new Label("　身長：");
        sliderH.setPrefWidth(280);
        sliderH.setOrientation(Orientation.HORIZONTAL);
        sliderH.setShowTickMarks(true);
        sliderH.setShowTickLabels(true);
        sliderH.setMajorTickUnit(50.0f);
        sliderH.setBlockIncrement(5.0f);
        lblHv.setText(sliderH.getValue() + "cm");
        sliderH.setOnMouseClicked(event -> updateValue());
        sliderH.setOnKeyPressed(event -> updateValue());

        HBox boxH = new HBox();
        boxH.getChildren().addAll(lblH, sliderH, lblHv);

        // 体重のラベルとスライダー
        Label lblW = new Label("　体重：");
        sliderW.setPrefWidth(280);
        sliderW.setOrientation(Orientation.HORIZONTAL);
        sliderW.setShowTickMarks(true);
        sliderW.setShowTickLabels(true);
        sliderW.setMajorTickUnit(50.0f);
        sliderW.setBlockIncrement(5.0f);
        lblHv.setText(sliderW.getValue() + "kg");
        sliderW.setOnMouseClicked(event -> updateValue());
        sliderW.setOnKeyPressed(event -> updateValue());

        HBox boxW = new HBox();
        boxW.getChildren().addAll(lblW, sliderW, lblWv);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(1, 1, 1, 1));
        root.setSpacing(20.0);
        root.getChildren().addAll(boxH, boxW, lblBMI);

        updateValue();

        stage.setScene(new Scene(root));
        stage.show();
    }

    void updateValue(){
        double h = sliderH.getValue();
        double w = sliderW.getValue();
        lblHv.setText(String.format("%5.1f", h) + "cm");
        lblWv.setText(String.format("%5.1f", w) + "kg");
        double bmi = 10000.0 * w / (h * h);
        lblBMI.setText(String.format("BMI=%5.2f", bmi));
    }
}

