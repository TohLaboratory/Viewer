// Border.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Border extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Border");
        stage.setWidth(220);
        stage.setHeight(140);

        Label lblStatus = new Label();

        Button btnTop = new Button("Top Button");
        btnTop.setPrefWidth(210);
        btnTop.setPrefHeight(30);
        btnTop.setOnAction(event -> lblStatus.setText("Topがクリックされました"));
        Button btnLeft = new Button("Left");
        btnLeft.setPrefHeight(30);
        btnLeft.setOnAction(event -> lblStatus.setText("Leftがクリックされました"));
        Button btnCenter = new Button("Center Button");
        btnCenter.setPrefHeight(30);
        btnCenter.setPrefWidth(160);
        btnCenter.setOnAction(event -> lblStatus.setText("Centerがクリックされました"));
        Button btnRight = new Button("Right");
        btnRight.setPrefHeight(30);
        btnRight.setOnAction(event -> lblStatus.setText("Rightがクリックされました"));
        Button btnBottom = new Button("Bottom Button");
        btnBottom.setPrefHeight(30);
        btnBottom.setPrefWidth(210);
        btnBottom.setOnAction(event -> lblStatus.setText("Bottomがクリックされました"));

        BorderPane border = new BorderPane();
        border.setTop(btnTop);
        border.setLeft(btnLeft);
        border.setCenter(btnCenter);
        border.setRight(btnRight);
        border.setBottom(btnBottom);

        VBox root = new VBox();
        root.getChildren().addAll(border, lblStatus);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
