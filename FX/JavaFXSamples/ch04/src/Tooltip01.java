import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Tooltip01 extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";
  String toolipBackgroundColor = "-fx-background-color: cyan";

  public void start(Stage stage) throws Exception {
    //
    Tooltip tooltip_1 = new Tooltip( "ツールチップ\n 複数行も可能" );
    //
    Tooltip tooltip_2 = new Tooltip( "ツールチップ\n フォント変更も可能" );
    Font font = Font.font( "MS Mincho", 24 );
    tooltip_2.setFont( font );
    //
    Tooltip tooltip_3 = new Tooltip( "ツールチップ\n テキストとイメージの混在も可能" );
    ImageView imgv = new ImageView( "open.gif" );
    tooltip_3.setGraphic( imgv );
    //
    Tooltip tooltip_4 = new Tooltip( "ツールチップ\n 背景色の変更が可能" );
    tooltip_4.setStyle( toolipBackgroundColor );
    //
    Label label_for_Tooltip = new Label( "ツールチップ\n 背景色と文字色の変更も可能" );
    String tooltipBackgroundColor = "-fx-background-color: cyan";
    String textColor = "-fx-text-fill: red";
    label_for_Tooltip.setStyle( tooltipBackgroundColor +";"+ textColor );
    Tooltip tooltip_5 = new Tooltip();
    tooltip_5.setGraphic( label_for_Tooltip );
    //
    Label label_1 = LabelBuilder.create().text("ラベル 1")
      .tooltip( tooltip_1 )
      .prefWidth(240).prefHeight(60).style(backgroundColor).build();

    Label label_2 = LabelBuilder.create().text("ラベル 2")
      .tooltip( tooltip_2 )
      .prefWidth(240).prefHeight(60).style(backgroundColor).build();

    Label label_3 = LabelBuilder.create().text("ラベル 3")
      .tooltip( tooltip_3 )
      .prefWidth(240).prefHeight(60).style(backgroundColor).build();

    Label label_4 = LabelBuilder.create().text("ラベル 4")
      .tooltip( tooltip_4 )
      .prefWidth(240).prefHeight(60).style(backgroundColor).build();

    Label label_5 = LabelBuilder.create().text("ラベル 5")
      .tooltip( tooltip_5 )
      .prefWidth(240).prefHeight(60).style(backgroundColor).build();
    //
    VBox root = new VBox();
    root.setSpacing( 10 );
    root.getChildren().addAll( label_1, label_2, label_3, label_4, label_5 );
    //
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Tooltip01");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
