// Buttons.java

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Buttons extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Buttons");
        stage.setWidth(260);
        stage.setHeight(120);

        // [�r�[�v]�{�^�����쐬����
        Button btnBeep = new Button("Beep");
        btnBeep.setOnAction(event ->
                java.awt.Toolkit.getDefaultToolkit().beep());

        // [�I��]�{�^������Ă���
        Button btnExit = new Button("�I��");
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Platform.exit();;
            }
            });

        // �C���[�W�t��[����]�{�^��
        Image img = new Image( Paths.get("image/close.gif").toUri().toString() );
        Canvas canvas = new Canvas();
        canvas.setWidth(img.getWidth());
        canvas.setHeight(img.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(img, 0, 0);
        Button btnClose = new Button("����", canvas);
        // btnClose.setGraphic(canvas); // ������g���Ă�OK
        btnClose.setPrefWidth(100);
        btnClose.setOnAction(event -> stage.close());

        // �����ɔz�u����
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.getChildren().addAll(btnBeep, btnExit, btnClose);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
