// q2_1.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q2_1 extends Application {

	@Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q2_1");
        stage.setWidth(220);
        stage.setHeight(120);

        TextField textField = new TextField();
        Button button = new Button("Click!");
        Label label = new Label();

        button.setOnAction(event ->
            label.setText("Ç±ÇÒÇ…ÇøÇÕÅA" + textField.getText() + "Ç≥ÇÒÅB"));

        VBox root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(textField, button, label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}