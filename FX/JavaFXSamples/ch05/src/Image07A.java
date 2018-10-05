import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image07A extends Application {
	
  public void start(Stage stage) throws Exception {
    //
    ImageView imgv1 = new ImageView( "stream.jpg" ); 
    ImageView imgv2 = new ImageView( "500kei02.jpg" );
    //
    Slider slider1 = new Slider( 0, 1, 0.5 );
    Slider slider2 = new Slider( 0, 1, 0.5 );
    imgv1.opacityProperty().bind( slider1.valueProperty() ); 
    imgv2.opacityProperty().bind( slider2.valueProperty() ); 
    //
    Group group = new Group( imgv1, imgv2 );
    VBox root = VBoxBuilder.create()
      .children( group, slider1, slider2 ).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image07A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
