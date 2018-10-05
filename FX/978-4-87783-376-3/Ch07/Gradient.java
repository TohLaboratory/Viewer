// Gradient.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gradient extends Application {
    public void start(Stage stage) {
        stage.setTitle("Gradient");
        stage.setWidth(280);
        stage.setHeight(380);

        Rectangle rect1 = new Rectangle();
        rect1.setWidth(220);
        rect1.setHeight(50);
        Stop[] stops1 = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops1);
        rect1.setFill(lg1);

        Rectangle rect2 = new Rectangle();
        rect2.setWidth(220);
        rect2.setHeight(50);
        Stop[] stops2 = new Stop[]{new Stop(0, Color.LIGHTSTEELBLUE), new Stop(1, Color.PALEGREEN)};
        LinearGradient lg2 = new LinearGradient(0, 0, 0.25, 0.25, true, CycleMethod.REFLECT, stops2);
        rect2.setFill(lg2);

        Rectangle rect3 = new Rectangle();
        rect3.setWidth(200);
        rect3.setHeight(200);
        RadialGradient rg = RadialGradient.valueOf(
            "radial-gradient(center 100px 100px, radius 200px, red  0%, blue 30%, black 100%)");

        rect3.setFill(rg);

        VBox root = new VBox();
        root.setSpacing(10.0);
        root.getChildren().addAll(rect1, rect2, rect3);
        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}
