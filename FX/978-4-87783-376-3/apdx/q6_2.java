// q6_2.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class q6_2 extends Application {

    String txt = "";
    int count = 0;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("q6_2");
        stage.setWidth(300);
        stage.setHeight(220);

        HBox root = new HBox();

        Label label = new Label("ここをクリックして閉じてください。");
        label.setPrefSize(300,  200);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setOnMousePressed(event -> Platform.exit());

        root.getChildren().addAll(label);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
