import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ScrollEvent01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Scene scene = new Scene(new Group());
    stage = StageBuilder.create().width(240).height(240)
      .scene( scene ).title("ScrollEvent01").build();
    //
    scene.addEventHandler( ScrollEvent.ANY, eventHandler );
    //
    stage.show();
  }
  //
  EventHandler<ScrollEvent> eventHandler = new EventHandler<ScrollEvent>() {
    public void handle(ScrollEvent e){
      EventType<? extends Event> type = e.getEventType();
      if( type == ScrollEvent.SCROLL ){
        System.out.print( "�X�N���[�� " );
      }
      if( type == ScrollEvent.SCROLL_STARTED ){
        System.out.print( "�X�N���[���J�n " );
      }
      if( type == ScrollEvent.SCROLL_FINISHED ){
        System.out.print( "�X�N���[���I�� " );
      }
      //
      double x = e.getX(); double y = e.getY();
      double deltaX = e.getDeltaX(); 
      double deltaY = e.getDeltaY();
      double textDeltaX = e.getTextDeltaX();
      double textDeltaY = e.getTextDeltaY();
      // ��O�ɉ�]:Y-  ���։�]:Y+  ���ɉ���:X+  �E�ɉ���:X-
      String msg = String.format(" ���΍��W(%f %f) Delta(%f %f) textDelta(%f %f)", 
    		                      x,y, deltaX,deltaY, textDeltaX,textDeltaY );
      System.out.println( msg );
    }
  };

  public static void main(String[] args) {
    launch(args);
  }
}
