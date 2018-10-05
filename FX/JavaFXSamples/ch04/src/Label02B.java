import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label02B extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
  ImageView imgv1 = new ImageView( "exit.gif" );
  ImageView imgv2 = new ImageView( "exit.gif" );
  ImageView imgv3 = new ImageView( "exit.gif" );
  ImageView imgv4 = new ImageView( "exit.gif" );
  ImageView imgv5 = new ImageView( "exit.gif" );
  ImageView imgv6 = new ImageView( "exit.gif" );
  ImageView imgv7 = new ImageView( "exit.gif" );
  ImageView imgv8 = new ImageView( "exit.gif" );
  ImageView imgv9 = new ImageView( "exit.gif" );
    //
    Label label_TopLeft = LabelBuilder.create().text("Top Left").graphic(imgv1)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.TOP_LEFT )
      .build();
    //
    Label label_TopCenter = LabelBuilder.create().text("Top Center").graphic(imgv2)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.TOP_CENTER )
      .build();
    //
    Label label_TopRight = LabelBuilder.create().text("Top Right").graphic(imgv3)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.TOP_RIGHT )
      .build();
    //
    Label label_CenterLeft = LabelBuilder.create().text("Center Left").graphic(imgv4)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.CENTER_LEFT )
      .build();
    //
    Label label_Center = LabelBuilder.create().text("Center").graphic(imgv5)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.CENTER )
      .build();
    //
    Label label_CenterRight = LabelBuilder.create().text("Center Right").graphic(imgv6)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.CENTER_RIGHT )
      .build();
    //
    Label label_BottomLeft = LabelBuilder.create().text("Bottom Left").graphic(imgv7)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.BOTTOM_LEFT )
      .build();
    //
    Label label_BottomCenter = LabelBuilder.create().text("Bottom Center").graphic(imgv8)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.BOTTOM_CENTER )
      .build();
    //
    Label label_BottomRight = LabelBuilder.create().text("Bottom Right").graphic(imgv9)
      .prefWidth(200).prefHeight(120).style(backgroundColor)
      .alignment( Pos.BOTTOM_RIGHT )
      .build();
    //
    GridPane root = new GridPane();
    root.setHgap( 10 );
    root.setVgap( 10 );
    root.add( label_TopLeft,    0, 0 );
    root.add( label_TopCenter,  1, 0 );
    root.add( label_TopRight,   2, 0 );
    root.add( label_CenterLeft, 0, 1 );
    root.add( label_Center,     1, 1 );
    root.add( label_CenterRight,2, 1 );
    root.add( label_BottomLeft,  0, 2 );
    root.add( label_BottomCenter,1, 2 );
    root.add( label_BottomRight, 2, 2 );
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label02B").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
