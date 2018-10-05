// Stack.java

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Stack extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Stack");
        stage.setWidth(240);
        stage.setHeight(140);
        
        Label status = new Label();

        // ボタンを3個作る
        Button button[] = new Button[5];
        for (int i=0; i<3;i++) {
            button[i] = new Button("button " + Integer.toString(i));
            button[i].setAlignment(Pos.TOP_LEFT);
            button[i].setPrefWidth(80 + i* 80);
            button[i].setPrefHeight(30 + i* 20);
            String txt = String.format("button %d がクリックされました。", i);
            button[i].setOnAction(event -> status.setText(txt));
        }

        // StackFloePaneに配置する
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.BOTTOM_RIGHT);
        pane.getChildren().addAll(button[2], button[1], button[0]);
        
        VBox root = new VBox();
        root.getChildren().addAll(pane, status);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
