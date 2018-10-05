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
        // �܂��� 
        // Eclipse�̂悤��IDE�Ŏ��s����Ȃ�java.nio.file.Paths���C���|�[�g���ĉ��̍s
        // Image img = new Image(Paths.get( "duke.png" ).toUri().toString());

        ImageView iv = new ImageView(img);
        iv.setLayoutX(120);
        iv.setLayoutY(50);
        Group root = new Group();
        stage.setScene(new Scene(root));
        stage.show();

        // ��]����A�j���[�V�����Ƃ��ĕ\������
        RotateTransition rt = new RotateTransition(Duration.millis(3000), iv);
        rt.setByAngle(180);       // ��]�p�x��180�x
        rt.setCycleCount(Animation.INDEFINITE);     // �J��Ԃ��񐔂͖���
        rt.setAutoReverse(true);  // �����I�ɔ��]������

        // �g��k������A�j���[�V�����Ƃ��ĕ\������
        ScaleTransition st = new ScaleTransition(Duration.millis(3000), iv);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(1.6);
        st.setToY(1.6);;
        st.setCycleCount(Animation.INDEFINITE);     // �J��Ԃ��񐔂͖���
        st.setAutoReverse(true);  // �����I�ɔ��]������

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
        timeline.play();      // �A�j���[�V���������s����
    }
}
