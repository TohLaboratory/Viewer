import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

public class Stage07A extends Application {
  public void start(Stage stage) throws Exception {
    Ellipse elipse = new Ellipse( 200, 100, 200, 100 );
    elipse.setFill( Color.WHITE );
    //
    Button button = new Button( "非矩形ウインドウの中のボタン" );
    button.setLayoutX( 100 ); button.setLayoutY( 90 );
    //
    Group root = new Group();
    root.getChildren().addAll( elipse, button );
    //
    Scene scene = SceneBuilder.create()
      .fill( Color.TRANSPARENT ) // ウインドウの背景も透明にする
      .root( root ).build();
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
