import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image04 extends Application {
	
  public void start(Stage stage) throws Exception {
    Image img = new Image( "500kei01.jpg" );
    //
    ImageView imgv1 = ImageViewBuilder.create().image(img).build();
    //
    ImageView imgv2 = ImageViewBuilder.create().image(img)
      .fitWidth(600)
      .build();
    //
    ImageView imgv3 = ImageViewBuilder.create().image(img)
      .fitWidth(100)
      .build();
    //
    HBox root = HBoxBuilder.create().spacing(10)
      .children( imgv1, imgv2, imgv3 ).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image04").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
