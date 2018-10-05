import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class VBox01 extends Application {
  public void start(Stage stage) throws Exception {
    HBox hb1 = HBoxBuilder.create().spacing( 5 )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .maxWidth( Double.MAX_VALUE )
      .build();
    HBox hb2 = HBoxBuilder.create().spacing( 5 )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .maxWidth( 250 )
      .build();
    HBox hb3 = HBoxBuilder.create().spacing( 5 )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    VBox vb = VBoxBuilder.create().spacing( 10 )
      .children( hb1, hb2, hb3 )
      .build();
    //
    Scene scene = new Scene( vb );
    stage = StageBuilder.create().width(190)
      .scene( scene ).title("VBox01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
