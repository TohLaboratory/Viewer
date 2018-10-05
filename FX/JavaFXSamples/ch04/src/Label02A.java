import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label02A extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    //
    ImageView imgv1 = new ImageView( "exit.gif" );
    ImageView imgv2 = new ImageView( "exit.gif" );
    ImageView imgv3 = new ImageView( "exit.gif" );
    //
    Label label_Left = LabelBuilder.create().text("Left").graphic(imgv1)
      .alignment( Pos.TOP_LEFT )
      .prefWidth(200).style(backgroundColor).build(); 
    //
    Label label_Center = LabelBuilder.create().text("Center").graphic(imgv2)
      .alignment( Pos.TOP_CENTER )
      .prefWidth(200).style(backgroundColor).build(); 
    //
    Label label_Right = LabelBuilder.create().text("Right").graphic(imgv3)
      .alignment( Pos.TOP_RIGHT )
      .prefWidth(200).style(backgroundColor).build(); 
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_Left, label_Center, label_Right ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label02A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
