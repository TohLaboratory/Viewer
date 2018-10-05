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

        // ��`�����
        Image img = new Image(Paths.get( "image/duke.png" ).toUri().toString());
        ImageView iv = new ImageView(img);
        iv.setLayoutX(120);
        iv.setLayoutY(50);

         Group root = new Group();
         root.getChildren().add(iv);
         stage.setScene(new Scene(root));
         stage.show();

        // ��]����A�j���[�V�����Ƃ��ĕ\������
        RotateTransition rt = new RotateTransition(Duration.millis(5000), iv);
        rt.setByAngle(360);       // ��]�p�x��180�x
        rt.setCycleCount(10);     // �J��Ԃ��񐔂�10��
        //rt.setAutoReverse(true);  // �����I�ɔ��]������


        rt.play();   // �A�j���[�V���������s����
    }
}
