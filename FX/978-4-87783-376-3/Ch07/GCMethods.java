// GCMethods.java

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GCMethods extends Application {
    public void start(Stage stage) {
        stage.setTitle("GCMethods");
        stage.setWidth(340);
        stage.setHeight(350);

        final Canvas canvas = new Canvas(300,300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
  
        // ü‚ğ•`‚­
        gc.setLineWidth(2.0);
        gc.setStroke(Color.DARKGRAY);
        gc.strokeLine(10, 10, 300, 10);
  
        // ‘È‰~‚ğ•`‚­
        gc.fillOval(10, 20, 100, 70);
  
        // ‹éŒ`‚ğ•`‚­
        gc.rect(120, 20, 160, 30);
        gc.stroke();
  
        // ƒeƒLƒXƒg‚ğ•`‚­
        gc.setFont(new Font(20));
        gc.fillText("JaxaFX‚ÍÅ‚‚¾‚ËI", 120, 80);
  
        // ƒCƒ[ƒW‚ğ•`‚­
        Image img = new Image(getClass().getResource("sample.jpg").toExternalForm());
        gc.drawImage(img, 20, 100);
  
        Group root = new Group();
        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}
