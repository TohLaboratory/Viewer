// Combo2.java

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Combo2 extends Application {

    ComboBox<String> comboBox = new ComboBox<>();
    Rectangle rect = new Rectangle(40, 80);

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Combo2");
        stage.setWidth(260);
        stage.setHeight(220);

        comboBox.getItems().addAll(
            "RED", "GREEN", "BLUE", "GRAY", "PINK");
        comboBox.setEditable(true);   // •ÒW‰Â”\‚É‚·‚é
        comboBox.setOnAction(event -> updateColor());

        HBox root = new HBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(comboBox, rect);

        stage.setScene(new Scene(root));
        stage.show();
    }
    
    void updateColor() 
    {
    	try {
    	    Color col = Color.valueOf(comboBox.getValue());
            rect.setFill(col);
    	} catch (Exception e) {
    		Toolkit.getDefaultToolkit().beep();
    	}
    }
}
