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
        System.out.println("ウインドウ表示開始中 " );
      }
      if( type == WindowEvent.WINDOW_SHOWN ){
        System.out.println("ウインドウを表示しました " );
      }
      if( type == WindowEvent.WINDOW_CLOSE_REQUEST ){
        System.out.println("ウインドウのクローズが要求されました " );
      }
      if( type == WindowEvent.WINDOW_HIDING ){
        System.out.println("ウインドウを閉じています " );
      }
      if( type == WindowEvent.WINDOW_HIDDEN ){
        System.out.println("ウインドウが閉じました " );
      }
    }
  };

  public static void main(String[] args) {
    launch(args);
  }
}
