// q9_1.java

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class q9_1 extends Application {

    double x = 0;

    @Override
    public void start(Stage stage) throws Exception 
    {
        stage.setTitle("q9_1");
        stage.setWidth(400);
        stage.setHeight(120);

        final Label label = new  Label("Hello, Happy Java FX!!");
        label.setFont(new Font(30));
        label.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(label));
        stage.show();

        x = stage.getWidth();
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.INDEFINITE);
            }

            @Override
            protected void interpolate(double frac) {
                label.setLayoutX(x);
                x = x - 5;
                if (x < label.getWidth() * (-1))
                	x = stage.getWidth();
                
            }
        };
        animation.play();
    }
}

