// Flow.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Flow extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Flow");

        // ボタンを5個作る
        Button button[] = new Button[5];
        for (int i=0; i<5;i++) {
          button[i] = new Button(Integer.toString(i));
          button[i].setPrefWidth(80);
        }

        // FlowPaneに配置する
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(button);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
