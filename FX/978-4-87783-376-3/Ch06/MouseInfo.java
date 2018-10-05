// MouseInfo.java

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MouseInfo extends Application {

    TextArea textArea = new TextArea();
    String txt = "";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MouseInfo");
        stage.setWidth(440);
        stage.setHeight(320);

        HBox root = new HBox();

        Label label = new Label();
        label.setPrefSize(200,  300);
        label.setOnMousePressed(event -> printInfo(event));
        // label.setOnMouseMoved(event -> printInfo(event));
        label.setOnMouseEntered(event -> printInfo(event));
        label.setOnMouseExited(event -> printInfo(event));
        label.setOnMouseReleased(event -> printInfo(event));
        textArea.setPrefSize(200,  300);
        textArea.setEditable(false);

        root.getChildren().addAll(textArea, label);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void printInfo(Event event) {
        txt = txt + String.format("%s\n",  event.getEventType().toString());
        textArea.setText( txt );
    }
}
