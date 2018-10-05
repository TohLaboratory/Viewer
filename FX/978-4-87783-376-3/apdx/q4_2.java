// q4_2.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q4_2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q4_2 AlertBox-ERROR");
        stage.setWidth(240);
        stage.setHeight(120);

        Button btnOpenDialog = new Button("�\��");
        btnOpenDialog.setPrefWidth(80);
        btnOpenDialog.setOnAction(event -> onClick(stage));

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(btnOpenDialog);

        stage.setScene(new Scene(root));
        stage.show();
    }

    void onClick(Stage stage)
    {
        // �A���[�g�{�b�N�X��\������
    	Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("AlertType.ERROR");
        alert.setHeaderText("�G���[�ł��I");
        alert.setContentText("�G���[�����Ă΁I");

        // �A���[�g�{�b�N�X��\������B
        alert.showAndWait();
    }
}
