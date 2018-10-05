// TxtArea.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TxtArea extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("TxtArea");
        stage.setWidth(360);
        stage.setHeight(300);

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(5);  // �s���̎w��
        textArea.setPrefColumnCount(20); // �����̎w��
        // ���ƍ����𒼐ڎw�肷��ꍇ
        // textArea.setMaxWidth(200);
        // textArea.setMaxHeight(60);
        textArea.setText("�����s�̕�����\n2�s��\n3�s��\n4�s��\n5�s��\n6�s��");

        // �\���p��TextArea���쐬����
        TextArea displayArea = new TextArea();
        displayArea.setPrefRowCount(5);  // �s���̎w��
        displayArea.setPrefColumnCount(20); // �����̎w��
        // �ҏW�ł��Ȃ��悤�ɂ���
        displayArea.setEditable(false);
        // TextFiled����������
        displayArea.textProperty().bind(textArea.textProperty());

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(textArea, displayArea);

        stage.setScene(new Scene(root));
        stage.show();
    }
}