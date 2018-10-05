// DropShadowSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DropShadowSmpl extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
        stage.setTitle("DropShadowSmpl");
		
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(4.0);
        dropShadow.setOffsetY(4.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));  

        Text text = new Text();
        text.setEffect(dropShadow);
        text.setCache(true);
        text.setX(10.0);
        text.setY(70.0);
        text.setFill(Color.web("0x3b596d"));
        text.setText("Hello, JavaFX!!");
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setOffsetX(6.0);
        dropShadow2.setOffsetY(4.0);

        Circle circle = new Circle();
        circle.setEffect(dropShadow2);
        circle.setCenterX(50.0);
        circle.setCenterY(125.0);
        circle.setRadius(30.0);
        circle.setFill(Color.STEELBLUE);
        circle.setCache(true);

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(text, circle);
        Scene scene = new Scene(root, 340, 180);
        stage.setScene(scene);
        stage.show();
   }
}