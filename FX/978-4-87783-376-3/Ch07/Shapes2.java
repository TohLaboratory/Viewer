// Shapes2.java

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Shapes2 extends Application {
    public void start(Stage stage) {
        stage.setTitle("Shapes2");
        stage.setWidth(280);
        stage.setHeight(120);

        // Line
        Line hline = new Line(10, 10, 260, 10);
  
        // PathÇégÇ§ÅiMoveToÇ∆HLineToÅj
        Path path = new Path();
        path.getElements().add(new MoveTo(10.0f, 15.0f));
        path.getElements().add(new HLineTo(120.0f));

        // CubicCurve
        CubicCurve cubic = new CubicCurve();
        cubic.setStartX(20.0f);
        cubic.setStartY(50.0f);
        cubic.setControlX1(25.0f);
        cubic.setControlY1(0.0f);
        cubic.setControlX2(75.0f);
        cubic.setControlY2(100.0f);
        cubic.setEndX(100.0f);
        cubic.setEndY(50.0f);

        // Polyline
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            160.0, 18.0,
            250.0, 30.0,
            160.0, 50.0,
            230.0, 60.0,
            165.0, 70.0 });

        Group root = new Group();
        root.getChildren().addAll(hline, path, cubic, polyline);
        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}
