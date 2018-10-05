// q3_2.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class q3_2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q3_2");
        stage.setWidth(280);
        stage.setHeight(160);

        // ���x�������
        Label label = new Label();
        AnchorPane.setTopAnchor(label, 95.0);
        AnchorPane.setLeftAnchor(label,  5.0);

        // �{�^����3���
        Button button1 = new Button("button1");
        button1.setPrefWidth(80);
        button1.setOnAction(event ->
            label.setText("button1���N���b�N����܂����B"));
        AnchorPane.setTopAnchor(button1,  5.0);
        AnchorPane.setLeftAnchor(button1,  5.0);

        Button button2 = new Button("button1");
        button2.setPrefWidth(80);
        button2.setOnAction(event ->
            label.setText("button2���N���b�N����܂����B"));
        AnchorPane.setTopAnchor(button2,  35.0);
        AnchorPane.setLeftAnchor(button2,  35.0);

        Button button3 = new Button("button1");
        button3.setPrefWidth(80);
        button3.setOnAction(event ->
            label.setText("button3���N���b�N����܂����B"));
        AnchorPane.setTopAnchor(button3,  65.0);
        AnchorPane.setLeftAnchor(button3,  65.0);

        // AnchorPane��Button��Label��z�u����
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(button1, button2, button3, label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
