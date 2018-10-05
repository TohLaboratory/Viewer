import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label01E extends Application {

  public void start(Stage stage) throws Exception {
    String backgroundColor = "-fx-background-color: rgb(180,180,180)";
    //
    Label label_1 = LabelBuilder.create()
      .text( "TextAlignment\nCENTER\n �����s�̏ꍇ�A�����s������ƁA���̌��ʂ��킩��܂��B" )
      .textAlignment( TextAlignment.CENTER )
      .prefWidth(280).prefHeight(80)
      .style( backgroundColor )
      .build();
    //
    Label label_2 = LabelBuilder.create()
      .text( "TextAlignment\nLEFT\n �����s�̏ꍇ�A�����s������ƁA���̌��ʂ��킩��܂��B" )
      .textAlignment( TextAlignment.LEFT )
      .prefWidth(280).prefHeight(80)
      .style( backgroundColor )
      .build();
    //
    Label label_3 = LabelBuilder.create()
      .text( "TextAlignment\nRIGHT\n �����s�̏ꍇ�A�����s������ƁA���̌��ʂ��킩��܂��B" )
      .textAlignment( TextAlignment.RIGHT )
      .prefWidth(280).prefHeight(80)
      .style( backgroundColor )
      .build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label01E").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
