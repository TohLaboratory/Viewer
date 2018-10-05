// WebColor.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WebColor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
        stage.setTitle("WebColor");
		
        Text text = new Text();
        text.setFill(Color.web("0xff0000"));
        text.setText("0xff0000");
        text.setFont(Font.font(null, FontWeight.BOLD, 20));

        Text text2 = new Text();
        text2.setFill(Color.web("0x00ff00"));
        text2.setText("0x00ff00");
        text2.setFont(Font.font(null, FontWeight.BOLD, 20));

        Text text3 = new Text();
        text3.setFill(Color.web("0x0000ff"));
        text3.setText("0x0000ff");
        text3.setFont(Font.font(null, FontWeight.BOLD, 20));

        Text text4 = new Text();
        text4.setFill(Color.web("0x2288aa"));
        text4.setText("0x2288aa");
        text4.setFont(Font.font(null, FontWeight.BOLD, 20));

        VBox root = new VBox();
        root.setSpacing(8);
        root.getChildren().addAll(text, text2, text3, text4);
        Scene scene = new Scene(root, 210, 140);
        stage.setScene(scene);
        stage.show();
   }
}