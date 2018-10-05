import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Stage03A extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    //
    Image open = new Image( getClass().getResourceAsStream("open.gif") );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Scene03A")
      .x(100).y(100).width(300).height(200)
      .icons( open )
      .build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
