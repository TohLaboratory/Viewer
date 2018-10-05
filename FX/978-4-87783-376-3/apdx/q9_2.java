// q9_2.java

import java.nio.file.Paths;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class q9_2 extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("q9_2");
        stage.setWidth(360);
        stage.setHeight(360);

        // 矩形を作る
        Image img = new Image(Paths.get( "image/duke.png" ).toUri().toString());
        ImageView iv = new ImageView(img);
        iv.setLayoutX(120);
        iv.setLayoutY(50);

         Group root = new Group();
         root.getChildren().add(iv);
         stage.setScene(new Scene(root));
         stage.show();

        // 回転するアニメーションとして表示する
        RotateTransition rt = new RotateTransition(Duration.millis(5000), iv);
        rt.setByAngle(360);       // 回転角度は180度
        rt.setCycleCount(10);     // 繰り返し回数は10回
        //rt.setAutoReverse(true);  // 自動的に反転させる


        rt.play();   // アニメーションを実行する
    }
}
