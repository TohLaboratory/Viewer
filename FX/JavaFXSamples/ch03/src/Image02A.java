import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image02A extends Application {
	
  public void start(Stage stage) throws Exception {
    ImageView imgv1 = ImageViewBuilder.create()
      .image( new Image( "/Book/JavaFX/JavaFX-1/ch03/bin/4-87783-185-1.png" ) )
      .build();
    ImageView imgv2 = ImageViewBuilder.create()
      .image( new Image( "file:///C:/Book/JavaFX/JavaFX-1/ch03/bin/4-87783-185-1.png" ) )
      .build();
    ImageView imgv3 = ImageViewBuilder.create()
      .image( new Image( "http://www.cutt.co.jp/book/images/4-87783-185-1.png" ) )
      .build();
    ImageView imgv4 = ImageViewBuilder.create()
      .image( new Image( "4-87783-185-1.png" ) )
      .build();
    //
    HBox root = HBoxBuilder.create().spacing(10)
      .children( imgv1, imgv2, imgv3, imgv4 ).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image02A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
