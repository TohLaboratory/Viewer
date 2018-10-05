import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label02C extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    //
    ImageView imgv1 = new ImageView( "exit.gif" );
    ImageView imgv2 = new ImageView( "exit.gif" );
    ImageView imgv3 = new ImageView( "exit.gif" );
    ImageView imgv4 = new ImageView( "exit.gif" );
    ImageView imgv5 = new ImageView( "exit.gif" );
    ImageView imgv6 = new ImageView( "exit.gif" );
    //
    Label label_1 = LabelBuilder.create().text( "ContentDisplay.BOTTOM" ).graphic(imgv1)
      .contentDisplay( ContentDisplay.BOTTOM )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    Label label_2 = LabelBuilder.create().text( "ContentDisplay.CENTER" ).graphic(imgv2)
      .contentDisplay( ContentDisplay.CENTER )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    Label label_3 = LabelBuilder.create().text( "ContentDisplay.LEFT" ).graphic(imgv3)
      .contentDisplay( ContentDisplay.LEFT )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    Label label_4 = LabelBuilder.create().text( "ContentDisplay.RIGHT" ).graphic(imgv4)
      .contentDisplay( ContentDisplay.RIGHT )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    Label label_5 = LabelBuilder.create().text( "ContentDisplay.TOP" ).graphic(imgv5)
      .contentDisplay( ContentDisplay.TOP )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    Label label_6 = LabelBuilder.create().text( "ContentDisplay.GRAPHIC_ONLY" ).graphic(imgv6)
      .contentDisplay( ContentDisplay.GRAPHIC_ONLY )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    Label label_7 = LabelBuilder.create().text( "ContentDisplay.TEXT_ONLY" )
      .contentDisplay( ContentDisplay.TEXT_ONLY )
      .prefWidth(320).prefHeight(100).style(backgroundColor)
      .build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3, label_4, label_5, label_6, label_7 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label02C").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
