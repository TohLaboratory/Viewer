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
        System.out.println("�E�C���h�E�\���J�n�� " );
      }
    });
    stage.setOnShown(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("�E�C���h�E��\�����܂��� " );
      }
    });
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("�E�C���h�E�̃N���[�Y���v������܂��� " );
      }
    });
    stage.setOnHiding(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("�E�C���h�E����Ă��܂� " );
      }
    });
    stage.setOnHidden(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        System.out.println("�E�C���h�E�����܂��� " );
      }
    });
    //
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
