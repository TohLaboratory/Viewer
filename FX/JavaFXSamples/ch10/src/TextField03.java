import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField03 extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField_1 = new TextField();
    textField_1.setId("tf1");
    TextField textField_2 = new TextField();
    textField_2.setId("tf2");
    //
    textField_1.textProperty().addListener( textChenge );
    textField_2.textProperty().addListener( textChenge );
    //
    textField_1.addEventHandler( KeyEvent.ANY, keyEventHandler );
    textField_2.addEventHandler( KeyEvent.ANY, keyEventHandler );
    //
    VBox root = VBoxBuilder.create().children(textField_1, textField_2).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField03").build();
    stage.show();
  }
  //
  ChangeListener<String> textChenge = new ChangeListener<String>() {
    public void changed( ObservableValue<? extends String> value, String oldVal, String newVal ) {
      ReadOnlyProperty prop = (ReadOnlyProperty)value;
      TextField source = (TextField)prop.getBean();
      String id = source.getId();
      System.out.println( "\n*textChange "+ id +":  ïœçXëO="+ oldVal +" ïœçXå„="+ newVal );
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
