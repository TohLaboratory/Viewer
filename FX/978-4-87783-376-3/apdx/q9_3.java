// q9_3.java

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class q9_3 extends Application {

    Random rnd = new Random();
	
    public void start(Stage stage) throws Exception {

        stage.setTitle("q9_3");
    	BorderPane root = new BorderPane();
    	final Label label = new Label("Hello, JavaFX!");

        Timeline timer = new Timeline(
            new KeyFrame(Duration.millis(1000),
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // ˆÊ’u‚ðƒ‰ƒ“ƒ_ƒ€‚É•Ï‚¦‚é
                	stage.setX(stage.getX() - 5 + rnd.nextInt(10));
                	stage.setY(stage.getY() - 5 + rnd.nextInt(10));
                }
            }));
        label.prefWidth(200);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font(30));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        
        root.setCenter(label);
        stage.setScene(new Scene(root, 300, 120));
        stage.show();
    }
}
