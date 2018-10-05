import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Properties;

public class Stage02 extends Application {
  //
  public static void main(String[] args) {
    Thread th = Thread.currentThread();
    System.out.println("main()メソッドが呼び出されました  "+ th.getName() );
    launch(args);
    //
    System.out.println( "launch()を呼び出し終わりました "+ th.getName() );
  }
  //
  public void init() throws Exception {
    Thread th = Thread.currentThread();
    System.out.println( "init()が呼び出されました "+ th.getName() );
    //
    Properties prop = System.getProperties();
    Object fxVer = prop.get("javafx.runtime.version");
    System.out.println( "JavaFX version = "+ fxVer ); // JavaFXのバージョン
  }
  //
  public void start(Stage stage) throws Exception {
    Thread th = Thread.currentThread();
    System.out.println( "start()が呼び出されました "+ th.getName() );
    //
    stage.setScene( new Scene( new Group() ) );
    stage.show();
    System.out.println( "stage.show()を呼び出しました "+ th.getName() );
  }
  //
  // ウインドウを閉じた時点で呼び出される
  public void stop() throws Exception {
    Thread th = Thread.currentThread();
    System.out.println( "stop()が呼び出されました "+ th.getName() );
  }
}
