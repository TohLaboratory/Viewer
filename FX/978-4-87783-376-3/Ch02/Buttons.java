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

        // [ビープ]ボタンを作成する
        Button btnBeep = new Button("Beep");
        btnBeep.setOnAction(event ->
                java.awt.Toolkit.getDefaultToolkit().beep());

        // [終了]ボタンを作榮する
        Button btnExit = new Button("終了");
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Platform.exit();;
            }
            });

        // イメージ付き[閉じる]ボタン
        Image img = new Image( Paths.get("image/close.gif").toUri().toString() );
        Canvas canvas = new Canvas();
        canvas.setWidth(img.getWidth());
        canvas.setHeight(img.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(img, 0, 0);
        Button btnClose = new Button("閉じる", canvas);
        // btnClose.setGraphic(canvas); // これを使ってもOK
        btnClose.setPrefWidth(100);
        btnClose.setOnAction(event -> stage.close());

        // 水平に配置する
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.getChildren().addAll(btnBeep, btnExit, btnClose);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
