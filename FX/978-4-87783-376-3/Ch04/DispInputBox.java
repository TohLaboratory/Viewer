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

        Button btnOpenDialog = new Button("入力");
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
        // テキストボックスを表示する
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("名前を入力してください。");
        dialog.setContentText("名前は:");

        // 適切な結果があるかどうかを調べる
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            label.setText(dialog.getEditor().getText());
        }
    }
}
