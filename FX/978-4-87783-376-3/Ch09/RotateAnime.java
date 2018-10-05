// RotateAnime.java

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RotateAnime extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("RorateAnime");
        stage.setWidth(300);
        stage.setHeight(220);

        // ��`�����
        Rectangle  rect = new Rectangle (10, 10, 40, 120);
        rect.setArcHeight(30);
        rect.setArcWidth(30);
        rect.setFill(Color.BLUE);
        rect.setLayoutX(80);
        rect.setLayoutY(25);
        Group root = new Group();
        root.getChildren().add(rect);
        stage.setScene(new Scene(root));
        stage.show();

        // ��]����A�j���[�V�����Ƃ��ĕ\������
        RotateTransition rt = new RotateTransition(Duration.millis(3000), rect);
        rt.setByAngle(180);       // ��]�p�x��180�x
        rt.setCycleCount(10);     // �J��Ԃ��񐔂�10��
        rt.setAutoReverse(true);  // �����I�ɔ��]������

        rt.play();   // �A�j���[�V���������s����
    }
}