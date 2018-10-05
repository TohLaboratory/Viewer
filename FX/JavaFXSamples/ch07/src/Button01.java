import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Button01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Button bt1 = new Button( "�{�^��\n �����s���\" );
    //
    Button bt2 = ButtonBuilder.create().text( "�{�^��\n �t�H���g�ύX���\" )
      .font( new Font("MS Gothic", 32) )
      .build();
    //
    Button bt3 = ButtonBuilder.create().text( "�{�^��\n �����F�̕ύX�͉\" )
      .textFill( Color.RED )
      .build();
    //
    Button bt4 = ButtonBuilder.create().text( "�{�^��\n �e�L�X�g�Ɖ摜�̍��݂��\" )
      .graphic( new ImageView( "open.gif" ) )
      .build();
    //
    Button bt5 = ButtonBuilder.create().graphic( new ImageView( "save.gif" ) )
      .tooltip( new Tooltip( "�ۑ�" ) )
      .build();
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( bt1, bt2, bt3, bt4, bt5 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Button01").build();
    stage.show();
  }
//
  public static void main(String[] args) {
    launch(args);
  }
}
