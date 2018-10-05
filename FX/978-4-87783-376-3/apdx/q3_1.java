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

        // ���x�������
        Label label = new Label();

        // �{�^����3���
        Button button1 = new Button("button1");
        button1.setPrefWidth(80);
        button1.setOnAction(event ->
            label.setText("button1���N���b�N����܂����B"));
        Button button2 = new Button("button1");
        button2.setPrefWidth(80);
        button2.setOnAction(event ->
            label.setText("button2���N���b�N����܂����B"));
        Button button3 = new Button("button1");
        button3.setPrefWidth(80);
        button3.setOnAction(event ->
            label.setText("button3���N���b�N����܂����B"));

        // VBox��Button��z�u����
        VBox left = new VBox(5);
        left.getChildren().addAll(button1, button2, button3);

        // HBox��VBox�ileft�j��Label��z�u����
        HBox root = new HBox(5);
        root.getChildren().addAll(left, label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
