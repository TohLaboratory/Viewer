// SimpleWindow.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SimpleWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("SimpleWindow");
        stage.setWidth(260);
        stage.setHeight(120);

        Label lblMsg = new Label("Hello, JavaFX");
        lblMsg.setFont(new Font(12));

        Button btnOk = new Button("OK");
        btnOk.setPrefWidth(80);
        btnOk.setOnAction(event -> stage.close());

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(lblMsg, btnOk);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
