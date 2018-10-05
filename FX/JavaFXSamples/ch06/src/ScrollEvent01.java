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
        System.out.print( "スクロール " );
      }
      if( type == ScrollEvent.SCROLL_STARTED ){
        System.out.print( "スクロール開始 " );
      }
      if( type == ScrollEvent.SCROLL_FINISHED ){
        System.out.print( "スクロール終了 " );
      }
      //
      double x = e.getX(); double y = e.getY();
      double deltaX = e.getDeltaX(); 
      double deltaY = e.getDeltaY();
      double textDeltaX = e.getTextDeltaX();
      double textDeltaY = e.getTextDeltaY();
      // 手前に回転:Y-  奥へ回転:Y+  左に押す:X+  右に押す:X-
      String msg = String.format(" 相対座標(%f %f) Delta(%f %f) textDelta(%f %f)", 
    		                      x,y, deltaX,deltaY, textDeltaX,textDeltaY );
      System.out.println( msg );
    }
  };

  public static void main(String[] args) {
    launch(args);
  }
}
