import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class HBox02 extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5") };
    //
    HBox.setHgrow( b[0], Priority.ALWAYS );                // �������ւ̐L����������
    b[0].setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE ); // �c������ �ǂ��܂ł��L������
    //
    HBox.setHgrow( b[1], Priority.ALWAYS );                // �������ւ̐L����������
    b[1].setMaxSize( 100, 100 );                           // �c������ �w�肵�����ƍ����܂ŐL������
    //
    b[2].setMaxHeight( Double.MAX_VALUE );          // �c�� �ǂ��܂ł��L������
    b[3].setMaxHeight( 100 );                       // �c�� �w�肵�������܂ŐL������
    //
    HBox.setHgrow( b[4], Priority.ALWAYS );         // �������ւ̐L���������邾���ł̓_��
    //
    HBox hb = HBoxBuilder.create()
      .children( b ).build();
    //
    Scene scene = new Scene( hb );
    stage = StageBuilder.create().
      scene( scene ).title("HBox02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
