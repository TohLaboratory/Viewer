// SepiaSmpl.java

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SepiaSmpl extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
        stage.setTitle("SepiaSmpl");
		
        SepiaTone sepiaTone = new SepiaTone();
        sepiaTone.setLevel(0.7);

        Image img = new Image( Paths.get( "dog.jpg" ).toUri().toString());
        ImageView imageView = new ImageView(img);
        imageView.prefWidth(img.getWidth());
        imageView.prefHeight(img.getHeight());
        imageView.setPreserveRatio(true);
        
        imageView.setOnMouseClicked( e -> imageView.setEffect(sepiaTone));
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(imageView);
        Scene scene = new Scene(root); //, 210, 180);
        stage.setScene(scene);
        stage.show();
   }
}