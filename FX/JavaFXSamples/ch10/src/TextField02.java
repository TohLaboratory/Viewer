import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField02 extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField_1 = new TextField();
    textField_1.setId("tf1");
    TextField textField_2 = new TextField();
    textField_2.setId("tf2");
    //
    textField_1.setOnAction( actionTextField );
    textField_2.setOnAction( actionTextField );
    //
    textField_1.addEventHandler( KeyEvent.ANY, keyEventHandler );
    textField_2.addEventHandler( KeyEvent.ANY, keyEventHandler );
    //
    VBox root = VBoxBuilder.create().children(textField_1, textField_2).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField02").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionTextField = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      TextField textField = (TextField)e.getTarget();
      String id    = textField.getId();
      String value = textField.getText();
      System.out.println( "\n *ActionEvent "+ id +" "+ value );
    }
  };
  //
  EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
    public void handle(KeyEvent e){
      EventType type = e.getEventType();
      String code = " KeyCode."+ e.getCode();
      System.out.println( " *KeyEvent "+ type +" "+ code );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
