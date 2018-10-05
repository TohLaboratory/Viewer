import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class KeyEvent02 extends Application {
  ObservableList<Pair> listKeyCombination;

  public void start(Stage stage) throws Exception {
    //
    listKeyCombination = FXCollections.observableArrayList(
      new Pair( KeyCombination.keyCombination("Ctrl+X"), "cut" ),
      new Pair( KeyCombination.keyCombination("Ctrl+C"), "copy" ),
      new Pair( KeyCombination.keyCombination("Ctrl+V"), "paste" ),
      new Pair( KeyCombination.keyCombination("Delete"), "del" ),
      new Pair( KeyCombination.keyCombination("Insert"), "ins" ),
      new Pair( KeyCombination.keyCombination("F3"), "next" ),
      new Pair( KeyCombination.keyCombination("Shift+F3"), "prev" ),
      new Pair( KeyCombination.keyCombination("BackSpace"), "undo" ),
      new Pair( KeyCombination.keyCombination("Shift+BackSpace"), "redo" ),
      new Pair( KeyCombination.keyCombination("Tab"), "tab" ), 
      new Pair( KeyCombination.keyCombination("Enter"), "ok" ),
      new Pair( KeyCombination.keyCombination("Esc"), "cancel" ),
      new Pair( KeyCombination.keyCombination("Home"), "home" ),
      new Pair( KeyCombination.keyCombination("End"), "end" ),
      new Pair( KeyCombination.keyCombination("Up"), "up" ), 
      new Pair( KeyCombination.keyCombination("Down"), "down" ), 
      new Pair( KeyCombination.keyCombination("Left"), "left" ),
      new Pair( KeyCombination.keyCombination("Right"), "right" )
    );
    //
    Scene scene = new Scene(new Group());
    scene.addEventHandler( KeyEvent.KEY_RELEASED, keyEventHandler );
    //
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("KeyEvent02").build();
    stage.show();
  }
  //
  EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
    public void handle( KeyEvent keyEvent ){
      for( int i=0 ; i< listKeyCombination.size() ; i++ ){
        Pair<KeyCombination,String> pair = listKeyCombination.get(i);
        KeyCombination keyCombination = pair.getKey();
        if( keyCombination.match( keyEvent ) ){ //**
          String id = pair.getValue();
          System.out.println("キーコンビネーション "+ id );
        }
      }
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
