import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.WindowEvent;

public class WindowEvent01A extends Application {

  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).title("WindowEvent01A").build();
    //
    stage.setOnShowing(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("ウインドウ表示開始中 " );
      }
    });
    stage.setOnShown(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("ウインドウを表示しました " );
      }
    });
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("ウインドウのクローズが要求されました " );
      }
    });
    stage.setOnHiding(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("ウインドウを閉じています " );
      }
    });
    stage.setOnHidden(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("ウインドウが閉じました " );
      }
    });
    //
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
