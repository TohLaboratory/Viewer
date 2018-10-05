import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image07 extends Application {
	
  public void start(Stage stage) throws Exception {
    //
    Image img1 = new Image( "stream.jpg" ); 
    Image img2 = new Image( "500kei02.jpg" );
    ImageView imgv1 = ImageViewBuilder.create().image(img1)
      .opacity( 0.5 )
      .build();
    ImageView imgv2 = ImageViewBuilder.create().image(img2)
      .opacity( 0.5 )
      .build();
    //
    Group group = new Group( imgv1, imgv2 );
    Scene scene = new Scene( group );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image07").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
