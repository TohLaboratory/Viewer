// Anchor.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Anchor extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Anchor");
        stage.setWidth(220);
        stage.setHeight(160);

        Button button1 = new Button("button1");
        button1.setPrefWidth(80);
        Button button2 = new Button("button2");
        button2.setMinWidth(120);

        AnchorPane root = new AnchorPane();
        
        // button1���ォ��10�A������10�ɔz�u����
        AnchorPane.setTopAnchor(button1,  10.0);
        AnchorPane.setLeftAnchor(button1,  10.0);
        
        // button2��������10�A�E����10�ɔz�u����
        AnchorPane.setBottomAnchor(button2,  10.0);
        AnchorPane.setRightAnchor(button2,  10.0);

        root.getChildren().addAll(button1, button2);
        
        stage.setScene(new Scene(root));
        stage.show();
    }
}