import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image02 extends Application {
	
  public void start(Stage stage) throws Exception {
    ImageView imgv = new ImageView( "4-87783-185-1.png" );
    Image img = imgv.getImage();
    double width = img.getWidth();
    double height = img.getHeight();
    System.out.println( "イメージのサイズ 幅="+ width +" 高さ="+ height );
    //
    Scene scene = new Scene( new Group( imgv ) );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
