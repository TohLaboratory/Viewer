// Shapes1.java

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Shapes1 extends Application {
    public void start(Stage stage) {
        stage.setTitle("Shapes1");
        stage.setWidth(240);
        stage.setHeight(150);

        Circle circ = new Circle(40, 40, 35);
        circ.setFill(Color.BLUE);
        Ellipse elips = new Ellipse(150, 45, 60, 30);
        elips.setFill(Color.GRAY);

        Line line1 = new Line(10, 80, 200, 80);
        line1.setStrokeWidth(1.0);
        Line line2 = new Line(10, 90, 200, 90);
        line2.setStrokeWidth(5.0);

        Group root = new Group();
        root.getChildren().addAll(circ, elips, line1, line2);
        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}
