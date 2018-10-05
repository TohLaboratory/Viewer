// Tile.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tile extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Tile");
        stage.setWidth(280);
        stage.setHeight(160);

        Label status = new Label();

        // ボタンを12個作る
        Button button[] = new Button[12];
        for (int i=0; i<12;i++) {
            button[i] = new Button("button " + Integer.toString(i));
            button[i].setPrefWidth(80);
            button[i].setPrefHeight(20);
            String txt = String.format("button %d がクリックされました。", i);
            button[i].setOnAction(event -> status.setText(txt));
        }

        // TileFloePaneに配置する
        TilePane pane = new TilePane();
        pane.getChildren().addAll(button);

        VBox root = new VBox();
        root.getChildren().addAll(pane, status);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
