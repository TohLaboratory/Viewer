import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class KeyEvent01 extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField = new TextField();
    textField.setId("textfield");

    textField.addEventHandler( KeyEvent.ANY, keyEventHandler );
    
    VBox root = VBoxBuilder.create().children( textField ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField01").build();
    stage.show();
  }
  //-----------------------------------------------
  EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
    public void handle(KeyEvent e){
      TextField field = (TextField)e.getTarget();
      System.out.print( "\n"+ field.getId() +" " );
      //
      EventType<? extends Event> type = e.getEventType();
      if( type == KeyEvent.KEY_PRESSED ){
        System.out.print("キーが押された "+ msgKey1(e) + msgModifier(e) );
      }
      if( type == KeyEvent.KEY_RELEASED ){
        System.out.print("キーが放された "+ msgKey1(e) + msgModifier(e) );
      }
      if( type == KeyEvent.KEY_TYPED ){
        System.out.print("キーがタイプされた "+ msgKey2(e) + msgModifier(e) );
      }
    }
  };
  //-----------------------------------------
  String msgKey1( KeyEvent e ){
    KeyCode code = e.getCode();
    String msgKeyCode = "KeyCode."+code.name();
    String text = e.getText();
    String msg = msgKeyCode +" text["+ text +"]";
    return( msg );
  }
  //-----------------------------------------
  String msgKey2( KeyEvent e ){
    String character = e.getCharacter();
    String msg = " char["+ character +"]";
    return( msg );
  }
  //----------------------------------------
  String msgModifier( KeyEvent e ){
    String msg = "";
    if( e.isShiftDown() ){
      msg += " +Shift";
    }
    if( e.isControlDown() ){
      msg += " +Ctrl";
    }
    if( e.isAltDown() ){
      msg += " +Alt";
    }
    if( e.isMetaDown() ){
      msg += " +Meta";
    }
    if( e.isShortcutDown() ){
      msg += " +Shortcut";
    }
    return( msg );
  }
  //----------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
