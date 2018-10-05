import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Properties;

public class Stage02 extends Application {
  //
  public static void main(String[] args) {
    Thread th = Thread.currentThread();
    System.out.println("main()���\�b�h���Ăяo����܂���  "+ th.getName() );
    launch(args);
    //
    System.out.println( "launch()���Ăяo���I���܂��� "+ th.getName() );
  }
  //
  public void init() throws Exception {
    Thread th = Thread.currentThread();
    System.out.println( "init()���Ăяo����܂��� "+ th.getName() );
    //
    Properties prop = System.getProperties();
    Object fxVer = prop.get("javafx.runtime.version");
    System.out.println( "JavaFX version = "+ fxVer ); // JavaFX�̃o�[�W����
  }
  //
  public void start(Stage stage) throws Exception {
    Thread th = Thread.currentThread();
    System.out.println( "start()���Ăяo����܂��� "+ th.getName() );
    //
    stage.setScene( new Scene( new Group() ) );
    stage.show();
    System.out.println( "stage.show()���Ăяo���܂��� "+ th.getName() );
  }
  //
  // �E�C���h�E��������_�ŌĂяo�����
  public void stop() throws Exception {
    Thread th = Thread.currentThread();
    System.out.println( "stop()���Ăяo����܂��� "+ th.getName() );
  }
}
