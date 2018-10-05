// q3_1.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q3_1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q3_1");
        stage.setWidth(280);
        stage.setHeight(160);

        // ラベルを作る
        Label label = new Label();

        // ボタンを3個作る
        Button button1 = new Button("button1");
        button1.setPrefWidth(80);
        button1.setOnAction(event ->
            label.setText("button1がクリックされました。"));
        Button button2 = new Button("button1");
        button2.setPrefWidth(80);
        button2.setOnAction(event ->
            label.setText("button2がクリックされました。"));
        Button button3 = new Button("button1");
        button3.setPrefWidth(80);
        button3.setOnAction(event ->
            label.setText("button3がクリックされました。"));

        // VBoxにButtonを配置する
        VBox left = new VBox(5);
        left.getChildren().addAll(button1, button2, button3);

        // HBoxにVBox（left）とLabelを配置する
        HBox root = new HBox(5);
        root.getChildren().addAll(left, label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
