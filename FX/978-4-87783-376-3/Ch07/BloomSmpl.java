// BloomSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BloomSmpl extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("BloomSmpl");
		
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.1);

        Rectangle rect = new Rectangle();
        rect.setX(10);
        rect.setY(10);
        rect.setWidth(180);
        rect.setHeight(150);
        rect.setFill(Color.DARKSLATEBLUE);

        Text text1 = new Text();
        text1.setText("Bloom!");
        text1.setFill(Color.ALICEBLUE);
        text1.setFont(Font.font(null, FontWeight.BOLD, 40));
        text1.setX(25);
        text1.setY(65);
        text1.setEffect(bloom);

        Text text2 = new Text();
        text2.setText("Bloom!");
        text2.setFill(Color.LIGHTGREEN);
        text2.setFont(Font.font(null, FontWeight.BOLD, 40));
        text2.setX(25);
        text2.setY(130);
        text2.setEffect(bloom);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(rect, text1, text2);
        Scene scene = new Scene(root, 210, 180);
        stage.setScene(scene);
        stage.show();
   }
}