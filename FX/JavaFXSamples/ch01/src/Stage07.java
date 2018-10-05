import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

public class Stage07 extends Application {
  public void start(Stage stage) throws Exception {
    Ellipse elipse = new Ellipse( 200, 100, 200, 100 );
    elipse.setFill( Color.WHITE );
    Group root = new Group();
    root.getChildren().add( elipse );
    //
    Scene scene = SceneBuilder.create()
      .root( root )
      .fill( Color.TRANSPARENT ) // ウインドウの背景も透明にする
      .build();
    //
    stage = StageBuilder.create()
      .style( StageStyle.TRANSPARENT ) // ウインドウを透明にする
      .scene( scene )
      .x(300).y(200)
      .build();
    stage.show();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
