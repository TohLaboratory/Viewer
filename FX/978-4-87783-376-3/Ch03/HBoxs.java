// HBoxs.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxs extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("HBoxs");

        Button button[] = new Button[5];
    
        for (int i=0; i<5;i++) {
            button[i] = new Button(Integer.toString(i));
            button[i].setPrefWidth(80);
        }

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(2.0);
        root.getChildren().addAll(button);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
