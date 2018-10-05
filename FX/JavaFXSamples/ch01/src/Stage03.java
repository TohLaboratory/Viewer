import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Stage03 extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    //
    Image open = new Image( getClass().getResourceAsStream("open.gif") );
    //
    stage.setScene( scene );
    stage.setTitle( "Scene03" );
    stage.setX(100);
    stage.setY(100);
    stage.setWidth(300);
    stage.setHeight(200);
    stage.getIcons().add( open );
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
