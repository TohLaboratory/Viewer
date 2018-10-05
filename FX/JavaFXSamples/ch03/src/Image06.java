import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image06 extends Application {
	
  public void start(Stage stage) throws Exception {
    Image img = new Image( "500kei01.jpg" );
    //----
    ImageView imgv1_1 = ImageViewBuilder.create().image(img).build();
    //
    ImageView imgv1_2 = ImageViewBuilder.create().image(img)
      .rotate( 90 )
      .build();
    //
    ImageView imgv1_3 = ImageViewBuilder.create().image(img)
      .rotate( 180 )
      .build();
    //
    ImageView imgv1_4 = ImageViewBuilder.create().image(img)
      .rotate( 270 )
      .build();
    //----
    ImageView imgv2_1 = ImageViewBuilder.create().image(img)
      .rotate( 360 )
      .build();
    //
    ImageView imgv2_2 = ImageViewBuilder.create().image(img)
      .rotate( -270 )
      .build();
    //
    ImageView imgv2_3 = ImageViewBuilder.create().image(img)
      .rotate( -180 )
      .build();
    //
    ImageView imgv2_4 = ImageViewBuilder.create().image(img)
      .rotate( -90 )
      .build();
    //------
    HBox hb1 = HBoxBuilder.create().spacing(10)
      .children( imgv1_1, imgv1_2, imgv1_3, imgv1_4 ).build();
    HBox hb2 = HBoxBuilder.create().spacing(10)
      .children( imgv2_1, imgv2_2, imgv2_3, imgv2_4 ).build();

    VBox root = VBoxBuilder.create().spacing(20)
      .children( hb1, hb2 ).build();
    //------
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image06").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
