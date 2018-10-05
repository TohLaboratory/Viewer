import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Flow02 extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{ 
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4") };
    b[0].setPrefSize( 100, 50 );    // GUI�v�f�̐����T�C�Y���w��
    b[1].setPrefSize( 50, 100 );    // GUI�v�f�̐����T�C�Y���w��
    b[2].setPrefSize( 100, 100 );   // GUI�v�f�̐����T�C�Y���w��
    //
    FlowPane fp = FlowPaneBuilder.create()
      .prefWidth(320).prefHeight(100) //���C�A�E�g���̐����T�C�Y���w��
      .children( b ).build();
    //
    Scene scene = new Scene( fp );
    stage = StageBuilder.create()
      .scene( scene ).title("Flow02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
