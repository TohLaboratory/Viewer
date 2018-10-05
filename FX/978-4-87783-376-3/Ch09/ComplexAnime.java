// ComplexAnime.java

import java.nio.file.Paths;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ComplexAnime extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("ComplexAnime");
        stage.setWidth(400);
        stage.setHeight(380);

        Image img = new Image(Paths.get( "duke.png" ).toUri().toString());
        // または 
        // EclipseのようなIDEで実行するならjava.nio.file.Pathsをインポートして下の行
        // Image img = new Image(Paths.get( "duke.png" ).toUri().toString());

        ImageView iv = new ImageView(img);
        iv.setLayoutX(120);
        iv.setLayoutY(50);
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.show();

        // 回転するアニメーションとして表示する
        RotateTransition rt = new RotateTransition(Duration.millis(3000), iv);
        rt.setByAngle(180);       // 回転角度は180度
        rt.setCycleCount(Animation.INDEFINITE);     // 繰り返し回数は無限
        rt.setAutoReverse(true);  // 自動的に反転させる

        // 拡大縮小するアニメーションとして表示する
        ScaleTransition st = new ScaleTransition(Duration.millis(3000), iv);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.6);
        st.setToY(1.6);;
        st.setCycleCount(Animation.INDEFINITE);     // 繰り返し回数は無限
        st.setAutoReverse(true);  // 自動的に反転させる

        Timeline timeline = new Timeline(
             new KeyFrame(
                  new Duration(500),
                  new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent event) {
                          root.getChildren().add(iv);
                          rt.play();
                      }
                  }
             ),
             new KeyFrame(
                 new Duration(4000),
                 new EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                          st.play();
                     }
                 }
            )
        );
        timeline.play();      // アニメーションを実行する
    }
}
