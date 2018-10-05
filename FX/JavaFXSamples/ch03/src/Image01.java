import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image01 extends Application {
	
  public void start(Stage stage) throws Exception {
    ImageView imgv1 = new ImageView( "/Book/JavaFX/JavaFX-1/ch03/bin/4-87783-185-1.png" ); 
    ImageView imgv2 = new ImageView( "file:///C:/Book/JavaFX/JavaFX-1/ch03/bin/4-87783-185-1.png" ); 
    //
    ImageView imgv3 = new ImageView( "http://www.cutt.co.jp/book/images/4-87783-185-1.png" );
    //
    HBox root = HBoxBuilder.create().spacing(10)
      .children( imgv1, imgv2, imgv3 ).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
