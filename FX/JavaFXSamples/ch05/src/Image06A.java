import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image06A extends Application {
	
  public void start(Stage stage) throws Exception {
    //
    ImageView imgv = new ImageView( "4-87783-185-1.png" );
    Slider slider = new Slider( 0, 360, 0 );
    imgv.rotateProperty().bind( slider.valueProperty() );
    //
    BorderPane root = BorderPaneBuilder.create()
      .center( imgv ).bottom( slider ).build();
    Scene scene = new Scene( root );
    stage = StageBuilder.create().width(200).height(250)
      .scene( scene ).title("Image06A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
