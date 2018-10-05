// Cursors.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Cursors extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cursors");
        stage.setWidth(240);
        stage.setHeight(120);

        Label label = new Label("Hello, JavaFX");
        label.setFont(new Font(12));
        label.setCursor(Cursor.WAIT);  // 待ち状態のカーソル

        Button btnClose = new Button("Close");
        btnClose.setPrefWidth(80);
        btnClose.setOnAction(event -> stage.close());
        btnClose.setCursor(Cursor.HAND); // 手のカーソル

        VBox root = new VBox();
        root.setCursor(Cursor.CLOSED_HAND);  // 閉じた手のカーソル
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(label, btnClose);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
