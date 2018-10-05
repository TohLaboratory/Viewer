// PlayVideo.java

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class PlayVideo extends Application {

    public void start(Stage stage) 
    {
        stage.setTitle("PlayVideo");
        stage.setWidth(480);
        stage.setHeight(340);

        Path path = Paths.get("sample.mp4");
        String movieUri = path.toUri().toString();
        Media media = new Media(movieUri);
        MediaPlayer player = new MediaPlayer(media);
        //  player.setAutoPlay(true); // Ž©“®Ä¶‚Ì‚Æ‚«
        MediaView view = new MediaView();
        view.setMediaPlayer(player);
        view.setOnError(e -> System.out.println(e));

        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(view);
        stage.setScene(scene);
        stage.show();

        player.setVolume(0.2); 
        player.play();
    }
}