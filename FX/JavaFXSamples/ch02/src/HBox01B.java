import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class HBox01B extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{ 
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4") };
    //
    HBox.setMargin( b[0], new Insets( 30 ) ); // äeï”ãœìôÇ… 30
    HBox.setMargin( b[1], new Insets(  0,  0,  0, 30 ) ); // ç∂Ç… 30
    HBox.setMargin( b[2], new Insets( 30,  0,  0,  0 ) ); // è„Ç… 30
    HBox.setMargin( b[3], new Insets(  0,  0, 30,  0 ) ); // â∫Ç… 30
    HBox.setMargin( b[4], new Insets(  0, 30,  0,  0 ) ); // âEÇ… 30
    //
    HBox hb = HBoxBuilder.create()
      .children( b )
      .build();
    //
    Scene scene = new Scene( hb );
    stage = StageBuilder.create()
      .scene( scene ).title("HBox01B").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
