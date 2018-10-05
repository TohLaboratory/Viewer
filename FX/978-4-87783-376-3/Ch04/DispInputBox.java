// DispInputBox.java

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DispInputBox extends Application {

	Label label = new Label("label");
	
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("DispInputBox");
        stage.setWidth(240);
        stage.setHeight(120);

        Button btnOpenDialog = new Button("����");
        btnOpenDialog.setPrefWidth(80);
        btnOpenDialog.setOnAction(event -> onClick(stage));

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(btnOpenDialog, label);

        stage.setScene(new Scene(root));
        stage.show();
    }

    void onClick(Stage stage)
    {
        // �e�L�X�g�{�b�N�X��\������
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("���O����͂��Ă��������B");
        dialog.setContentText("���O��:");

        // �K�؂Ȍ��ʂ����邩�ǂ����𒲂ׂ�
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            label.setText(dialog.getEditor().getText());
        }
    }
}
