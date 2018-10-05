import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class HBox01A extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{ 
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5") };
    //
    HBox hb = HBoxBuilder.create().children( b )
      .padding( new Insets(20) ) // ���C�A�E�g�̊O���ɋ󔒂�ݒ�
      .build();
    //
    Scene scene = new Scene( hb );
    stage = StageBuilder.create()
      .scene( scene ).title("HBox01A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
