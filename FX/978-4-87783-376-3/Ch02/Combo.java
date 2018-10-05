// Combo.java

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Combo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Combo");
        stage.setWidth(260);
        stage.setHeight(220);

        Rectangle rect = new Rectangle(40, 80);
        
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
            "RED", "GREEN", "BLUE", "GRAY", "PINK");
        comboBox.setEditable(true);   // •ÒW‰Â”\‚É‚·‚é
        comboBox.setOnAction(event -> 
            rect.setFill(Color.valueOf(comboBox.getValue())));

        HBox root = new HBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(comboBox, rect);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
