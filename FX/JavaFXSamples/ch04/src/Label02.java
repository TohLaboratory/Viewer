import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label02 extends Application {

  public void start(Stage stage) throws Exception {
    //
    ImageView imgv1 = new ImageView( "exit.gif" );
    ImageView imgv2 = new ImageView( "exit.gif" );
    //
    Label label_1 = LabelBuilder.create().graphic(imgv1)
      .build(); 
    //
    Label label_1a = LabelBuilder.create()
      .style(  "-fx-graphic: url(\"exit.gif\")" )
      .build(); 
    //
    Label label_2 = LabelBuilder.create().text("テキスト").graphic(imgv2)
      .build(); 
    //
    Label label_2a = LabelBuilder.create().text("テキスト")
      .style(  "-fx-graphic: url(\"exit.gif\")" )
      .build(); 
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_1a, label_2, label_2a ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(190)
      .scene( scene ).title("Label02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
