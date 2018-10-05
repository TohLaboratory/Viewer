// q6_1.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class q6_1 extends Application {

    String txt = "";
    int count = 0;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("q6_1");
        stage.setWidth(300);
        stage.setHeight(220);

        HBox root = new HBox();

        Label label = new Label();
        label.setPrefSize(300,  200);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setOnMousePressed(event -> label.setText(String.format("%d回クリックしました。", ++count)));

        root.getChildren().addAll(label);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
