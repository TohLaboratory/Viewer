// TransText.java

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransText extends Application {

    @Override
    public void start(Stage stage) throws Exception 
    {
        stage.setTitle("TransText");
        stage.setWidth(400);
        stage.setHeight(120);

        final String content = "Hello, Happy Java FX!!";
        final Label label = new  Label("");
        label.setFont(new Font(30));
        label.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(label));
        stage.show();

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(3000));
            }

            @Override
            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                label.setText(content.substring(0, n));
            }
        };
        animation.play();
    }
}
