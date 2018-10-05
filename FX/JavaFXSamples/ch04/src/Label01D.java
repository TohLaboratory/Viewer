import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label01D extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    //
    Label label_Left = LabelBuilder.create().text("Left")
      .prefWidth(200).prefHeight(18).style(backgroundColor)
      .alignment( Pos.TOP_LEFT )
      .build();
    //
    Label label_Center = LabelBuilder.create().text("Center")
      .prefWidth(200).prefHeight(18).style(backgroundColor)
      .alignment( Pos.TOP_CENTER )
      .build();
    //
    Label label_Right = LabelBuilder.create().text("Right")
      .prefWidth(200).prefHeight(18).style(backgroundColor)
      .alignment( Pos.TOP_RIGHT )
      .build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_Left, label_Center, label_Right ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label01D").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
