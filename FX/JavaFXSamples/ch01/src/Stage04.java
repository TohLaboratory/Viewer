import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Stage04 extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = SceneBuilder.create()
      .root( new FlowPane() )
      .width(300).height(200)
      .fill( Color.AQUA )
      .build();
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Scene04")
      .x(100).y(100)
      .build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
