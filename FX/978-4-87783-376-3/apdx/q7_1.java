// q7_1.java

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class q7_1 extends Application 
{
    public void start(Stage stage)
    {
        stage.setTitle("q7_1");
        stage.setWidth(340);
        stage.setHeight(200);

        Circle circle = new Circle();		// Circleオブジェクトを作成する
        circle.setCenterX(80.0f);
        circle.setCenterY(70.0f);
        circle.setRadius(60.0f);

        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2.0);
        circle.setFill(null);			// 塗りつぶししない

        final Canvas canvas = new Canvas(140,140);
        canvas.setLayoutX(160);
        canvas.setLayoutY(5);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeOval(5, 5, 120, 120);

        
        Group root = new Group();
        root.getChildren().addAll(circle, canvas);
        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}
