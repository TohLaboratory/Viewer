import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.WindowEvent;

public class WindowEvent01 extends Application {

  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).title("WindowEvent01").build();
    //
    stage.addEventHandler( WindowEvent.ANY, windowEventHandler );
    //
    stage.show();
  }
  //
  EventHandler<WindowEvent> windowEventHandler = new EventHandler<WindowEvent>() {
    public void handle(WindowEvent e){
      EventType<? extends Event> type = e.getEventType();
      if( type == WindowEvent.WINDOW_SHOWING ){
        System.out.println("�E�C���h�E�\���J�n�� " );
      }
      if( type == WindowEvent.WINDOW_SHOWN ){
        System.out.println("�E�C���h�E��\�����܂��� " );
      }
      if( type == WindowEvent.WINDOW_CLOSE_REQUEST ){
        System.out.println("�E�C���h�E�̃N���[�Y���v������܂��� " );
      }
      if( type == WindowEvent.WINDOW_HIDING ){
        System.out.println("�E�C���h�E����Ă��܂� " );
      }
      if( type == WindowEvent.WINDOW_HIDDEN ){
        System.out.println("�E�C���h�E�����܂��� " );
      }
    }
  };

  public static void main(String[] args) {
    launch(args);
  }
}
