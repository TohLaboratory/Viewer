import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage01 extends Application {
  public void start( Stage stage ) throws Exception {
    stage.setScene( new Scene( new Group() ) );
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
