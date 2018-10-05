import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Color01 extends Application {

  public void start(Stage stage) throws Exception {
    String backgroundColor = "-fx-background-color: rgb(180,180,180)";
    //
    String textColor1 = "-fx-text-fill: red";
    Label label_1 = LabelBuilder.create().text( " 文字色の変更も可能 \n Color.RED " )
      .style( backgroundColor +";"+ textColor1 ).build();
    //
    String textColor2 = "-fx-text-fill: rgb(255, 0, 0)";
    Label label_2 = LabelBuilder.create().text( " 文字色の変更も可能  RGB指定 \n Color.rgb(255, 0, 0) " )
      .style( backgroundColor +";"+ textColor2 ).build();
    //
    String textColor3 = "-fx-text-fill: rgba(255, 0, 0, 0.5 )";
    Label label_3 = LabelBuilder.create().text( " 文字色の変更も可能  RGBA指定 \n Color.rgba(255, 0, 0, 0.5 ) " )
      .style( backgroundColor +";"+ textColor3 ).build();
    //
    String textColor4 = "-fx-text-fill: hsb(270, 100%, 100%)";
    Label label_4 = LabelBuilder.create().text( " 文字色の変更も可能   HSB指定 \n Color.hsb(270, 100%, 100%) " )
      .style( backgroundColor +";"+ textColor4 ).build();
    //
    String textColor5 = "-fx-text-fill: hsba(270, 100%, 100%, 0.5 )";
    Label label_5 = LabelBuilder.create().text( " 文字色の変更も可能   HSBA指定 \n Color.hsba(270, 100%, 100%, 0.5 ) " )
      .style( backgroundColor +";"+ textColor5 ).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3, label_4, label_5 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Color01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
