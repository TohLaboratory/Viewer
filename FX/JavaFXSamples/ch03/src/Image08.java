import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.geometry.Rectangle2D;

public class Image08 extends Application {
	
  public void start(Stage stage) throws Exception {
    Image img = new Image( "500kei01.jpg" );
    //
    ImageView imgv1 = ImageViewBuilder.create().image(img).build();
    //
    Rectangle2D viewport2 = new Rectangle2D( 0, 80, 280, 220 );
    ImageView imgv2 = ImageViewBuilder.create().image(img)
      .viewport( viewport2 )
      .build();
    //
    Rectangle2D viewport3 = new Rectangle2D( 230, 80, 280, 220 );
    ImageView imgv3 = ImageViewBuilder.create().image(img)
      .viewport( viewport3 )
      .build();
    //
    HBox root = HBoxBuilder.create()
      .children( imgv1, imgv2, imgv3 ).spacing(15).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image08")
      .build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
