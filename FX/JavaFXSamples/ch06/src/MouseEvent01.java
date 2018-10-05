import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class MouseEvent01 extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {

    Label label = new Label( "ラベル" );
    //
    Image img = new Image( getClass().getResourceAsStream("4-87783-185-1.png") );
    ImageView imgv = new ImageView( img );
    //
    Button button_1 = new Button( "ボタン" );
    //
    Button button_2 = new Button( "マウスイベントハンドラ設定済ボタン" );
    button_2.addEventHandler( MouseEvent.ANY, mouseEventHandler );
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( label, imgv, button_1, button_2 ).build();
    Scene scene = new Scene( root );
    // 
    root.addEventHandler( MouseEvent.ANY, mouseEventHandler );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("MouseEvent01")
      .x(300).y(200).width(300).height(320).build();
    stage.show();
  }

  EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e){
      Object source = e.getSource();
      EventTarget target = e.getTarget();
      //
      EventType<? extends Event> type = e.getEventType();
      if( type == MouseEvent.MOUSE_MOVED ){
        System.out.print("\n"+ source +" "+ target +" マウス移動  "+ msgPoint(e) );
      }
      if( type == MouseEvent.DRAG_DETECTED ){
        System.out.print( "\n"+ source +" "+ target +" マウスドラッグ検出 "+ msgPoint(e) );
      }
      // マウスボタンを押しながらの移動
      if( type == MouseEvent.MOUSE_DRAGGED ){
        System.out.print("\n"+ source +" "+ target +" マウスドラッグ移動 "+ msgPoint(e) );
      }
      // イベント監視対象以外のコントロールへマウスが転入した事を検知出来る
      if( type == MouseEvent.MOUSE_ENTERED_TARGET ){
        System.out.print( "\n"+ source +" "+ target +" マウスが他のGUI要素に転入 " );
      }
      // イベント監視対象以外のコントロールからマウスが転出した事を検知出来る
      if( type == MouseEvent.MOUSE_EXITED_TARGET ){
        System.out.print( "\n"+ source +" "+ target +" マウスが他のGUI要素から転出 " );
      }
      if( type == MouseEvent.MOUSE_ENTERED ){
        System.out.print( "\n"+ source +" "+ target +" マウスが転入 " );
      }
      if( type == MouseEvent.MOUSE_EXITED ){
        System.out.print( "\n"+ source +" "+ target +" マウスが転出 " );
      }
      if( type == MouseEvent.MOUSE_PRESSED ){
        System.out.print( "\n"+ source +" "+ target +" "+ msgButton(e) +"が押された "+ msgKey(e) );
      }
      if( type == MouseEvent.MOUSE_RELEASED ){
        System.out.print( "\n"+ source +" "+ target +" "+ msgButton(e) +"が放された "+ msgKey(e) );
      }
      if( type == MouseEvent.MOUSE_CLICKED ){
        System.out.print( "\n"+ source +" "+ target +" "+ msgButton(e) +"がクリックされた "+ msgKey(e) );
      }
    }
  };
  //-----------------------------------------------------------
  String msgPoint( MouseEvent e ){
    double x = e.getX(); double y = e.getY();
    double x1 = e.getSceneX(); double y1 = e.getSceneY();
    double x2 = e.getScreenX(); double y2 = e.getScreenY();
    String msg = String.format("\n   相対座標(%f %f) シーン座標(%f %f) スクリーン座標(%f %f)", x,y,x1,y1,x2,y2 );
    return( msg ); 
  }
  //----------------------------------------
  String msgButton( MouseEvent e ){
    String msg = "";
    MouseButton btn = e.getButton();
    if( btn==MouseButton.PRIMARY ){
      msg += "左ボタン ";
    }
    if( btn==MouseButton.SECONDARY ){
      msg += "右ボタン ";
    }
    if( btn==MouseButton.MIDDLE ){
      msg += "中ボタン ";
    }
    //
    int cnt = e.getClickCount();
    msg = msg +"( クリック回数 "+ cnt +" ) ";
    return( msg );
  }
  //----------------------------------------
  String msgKey( MouseEvent e ){
    String msg = "";
    if( e.isShiftDown() ){
      msg += "Shiftキー ";
    }
    if( e.isControlDown() ){
      msg += "Ctrlキー ";
    }
    if( e.isAltDown() ){
      msg += "Altキー ";
    }
    if( e.isMetaDown() ){
      msg += "Metaキー ";
    }
    if( e.isShortcutDown() ){
      msg += "Shortcutキー ";
    }
    return(msg);
  }
  
  //----------------------------------------
  public static void main(String[] args) {
    launch(args);
  }

}
