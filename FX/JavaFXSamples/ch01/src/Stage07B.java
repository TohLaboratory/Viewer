import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

public class Stage07B extends Application {
  public void start(Stage stage) throws Exception {
    Ellipse elipse = new Ellipse( 350, 250, 320, 190 );
    Circle circle1 = new Circle( 220, 250, 120 );
    Circle circle2 = new Circle( 480, 250, 120 );
    Shape shape1 = Shape.subtract( elipse, circle1 );
    Shape shape2 = Shape.subtract( shape1, circle2 );
    shape2.setFill( Color.WHITE );
    //
    Button button = new Button( "非矩形ウインドウの中のボタン" );
    button.setLayoutX( 260 ); button.setLayoutY( 100 );
    //
    Group root = new Group();
    root.getChildren().addAll( shape2, button );
    //
    Scene scene = SceneBuilder.create()
      .fill( Color.TRANSPARENT ) // ウインドウの背景も透明にする
      .root( root ).build();
    //
    stage = StageBuilder.create()
      .style( StageStyle.TRANSPARENT ) // ウインドウを透明にする
      .scene( scene )
      .x(420).y(300)
      .build();
    stage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
