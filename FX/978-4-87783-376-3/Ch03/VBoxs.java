// VBoxs.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxs extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("VBoxs");

        Button button[] = new Button[5];
        Label status = new Label();

        for (int i=0; i<5;i++) {
            button[i] = new Button("button " + Integer.toString(i));
            button[i].setPrefWidth(120);
            button[i].setPrefHeight(20);
            String txt = String.format("button %d Clicked", i);
            button[i].setOnAction(event -> status.setText(txt));
        }
 
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(2.0);
        root.getChildren().addAll(button);
        root.getChildren().add(status);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
