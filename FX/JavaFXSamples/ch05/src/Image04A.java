import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image04A extends Application {
  public void start(Stage stage) throws Exception {
    ImageView imgv = new ImageView( "500kei01.jpg" );
    
    HBox root = HBoxBuilder.create().spacing(10)
    	      .children( imgv ).build();
    Scene scene = new Scene( root );
    stage = StageBuilder.create()
      .scene( scene ).title( "Image04A" ).build();
    //
    imgv.fitWidthProperty().bind( root.widthProperty() );
    imgv.fitHeightProperty().bind( root.heightProperty() );
    //
    stage.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
