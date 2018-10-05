// q1_2.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class q1_2 extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("q1_2");
        stage.setWidth(240);
        stage.setHeight(120);

        Label lblMsg = new Label("Hello, Java!");
        lblMsg.setFont(new Font(12));

        Button btnOk = new Button("ƒNƒŠƒbƒN‚µ‚Ä‚Ë");
        btnOk.setPrefWidth(120);
        btnOk.setOnAction(event -> lblMsg.setText("Hello, JavaFX!"));

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(lblMsg, btnOk);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
