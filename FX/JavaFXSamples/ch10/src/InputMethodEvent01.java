import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodTextRun;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class InputMethodEvent01 extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField = new TextField();
    textField.setId("textfield");

    VBox root = VBoxBuilder.create().children( textField ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(280).height(120)
      .scene( scene ).title("InputMethodEvent01").build();
    //
    scene.setOnInputMethodTextChanged( imeEventHandler );

    stage.show();
  }
  //----------------------------------------------------------------------
  EventHandler<InputMethodEvent> imeEventHandler = new EventHandler<InputMethodEvent>() {
    public void handle(InputMethodEvent e){
      TextField field = (TextField)e.getTarget();
      System.out.print( "\n*InputMethodEvent "+ field.getId() +" " );
      //
      int position = e.getCaretPosition();
      String text = e.getCommitted();
      System.out.println("CaretPosition:"+ position + " Committed:"+ text  );
      ObservableList<InputMethodTextRun> composed = e.getComposed();
      for( int i=0 ; i<composed.size() ; i++ ){
        InputMethodTextRun textRun = composed.get(i);
        String runtext = textRun.getText();
        System.out.println( "textRun: "+ runtext );
      }
    }
  };
  //-----------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
