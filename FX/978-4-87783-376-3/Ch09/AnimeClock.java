// AnimeClock.java

import java.time.LocalTime;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimeClock extends Application {

    public void start(Stage stage) throws Exception {
        final Label label = new Label("00:00:00");

        Timeline timer = new Timeline(
            new KeyFrame(Duration.millis(1000),
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                // åªç›éûçèÇéÊìæÇµÇƒÉâÉxÉãÇ…ï\é¶Ç∑ÇÈ
                LocalTime now = LocalTime.now();
                String txt = String.format("%02d:%02d:%02d",
                    now.getHour(), now.getMinute(), now.getSecond());
                    label.setText(txt);
                }
            }));
        label.prefWidth(200);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font(30));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        stage.setTitle("AnimeClock");
        stage.setScene(new Scene(label, 220, 60));
        stage.show();
    }
}
