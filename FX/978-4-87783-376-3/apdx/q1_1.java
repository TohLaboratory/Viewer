// q1_1.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class q1_1 extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("q1_1");
        stage.setWidth(540);
        stage.setHeight(220);
        Label lblMsg = new Label("‰Ä–Ú ŸùÎ");
        lblMsg.setFont(new Font(60));
        stage.setScene(new Scene(lblMsg));
        stage.show();
    }
}
