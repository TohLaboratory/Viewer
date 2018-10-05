// BlendSmpl.java

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BlendSmpl extends Application 
{
    public void start(Stage stage)
    {
        stage.setTitle("BlendSmpl");
        stage.setWidth(300);
        stage.setHeight(320);

        Blend blend = new Blend();
        blend.setMode(BlendMode.COLOR_BURN);

        ColorInput colorInput = new ColorInput();
        colorInput.setPaint(Color.STEELBLUE);
        colorInput.setX(50);
        colorInput.setY(10);
        colorInput.setWidth(100);
        colorInput.setHeight(260);

        blend.setTopInput(colorInput);

        Rectangle rect1 = new Rectangle();
        rect1.setWidth(250);
        rect1.setHeight(100);
        rect1.setFill(Color.LIGHTPINK);

        Rectangle rect2 = new Rectangle();
        rect2.setY(150);
        rect2.setWidth(250);
        rect2.setHeight(100);
        Stop[] stops = new Stop[]{new Stop(0, Color.LIGHTSTEELBLUE), new Stop(1, Color.PALEGREEN)};
        LinearGradient lg = new LinearGradient(0, 0, 0.25, 0.25, true, CycleMethod.REFLECT, stops);
        rect2.setFill(lg);

        Text text1 = new Text();
        text1.setX(15);
        text1.setY(65);
        text1.setFill(Color.PALEVIOLETRED);
        text1.setText("COLOR_BURN");
        text1.setFont(Font.font(null, FontWeight.BOLD, 30));

        Text text2 = new Text();
        text2.setX(15);
        text2.setY(215);
        text2.setFill(Color.BROWN);
        text2.setText("COLOR_BURN");
        text2.setFont(Font.font(null, FontWeight.BOLD, 30));

        Group g = new Group();
        g.setEffect(blend);
        g.getChildren().addAll(rect1, rect2, text1, text2);

        Group root = new Group();
        root.getChildren().addAll(g);
        Scene scene = new Scene(root, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
}
