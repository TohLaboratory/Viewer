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

        Button btnOpenDialog = new Button("表示");
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
        // アラートボックスを表示する
    	Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("AlertType.ERROR");
        alert.setHeaderText("エラーです！");
        alert.setContentText("エラーだってば！");

        // アラートボックスを表示する。
        alert.showAndWait();
    }
}
