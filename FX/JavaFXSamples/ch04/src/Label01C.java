import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label01C extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    //
    Label label_1 = LabelBuilder.create()
      .text( "長い文字列を含むラベルも、setWrapText(true) を指定することにより"+
             "折り返し処理が行われ、複数行に渡って表示されます。" +
             "どこで改行されるかは、そのラベルの初期値の大きさで決まります。" )
      .wrapText( true )
      .prefWidth(120).prefHeight(180)
      .style( backgroundColor )
      .build();
    //
    Label label_2 = LabelBuilder.create()
      .text( "長い文字列を含むラベルも、setWrapText(true) を指定することにより"+
             "折り返し処理が行われ、複数行に渡って表示されます。" +
             "どこで改行されるかは、そのラベルの初期値の大きさで決まります。" )
      .wrapText( true )
      .prefWidth(240).prefHeight(100)
      .style( backgroundColor )
      .build();
    //
    Label label_3 = LabelBuilder.create()
      .text( "ラベルの大きさを明示的に指定しないと"+
             "WARPスタイルを設定しても、1行で表示されます。" )
      .style( backgroundColor )
      .build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label01C").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
