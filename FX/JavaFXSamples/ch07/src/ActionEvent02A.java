import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ActionEvent02A extends Application {

  public void start(Stage stage) throws Exception {
    //
    // �{�^���́A�f�t�H���g�� �j�[���j�b�NON 
    Button bt1 = ButtonBuilder.create().text( "�J�� _O" )
      .id("open").build();
    Button bt2 = ButtonBuilder.create().text( "�ۑ� _s" )
      .id("save").build();
    Button bt3 = ButtonBuilder.create().text( "�I�� _X" )
      .id("exit").build();
    //
    VBox root = VBoxBuilder.create().spacing(10).children( bt1, bt2, bt3 ).build();
    Scene scene = new Scene(root);
    scene.addEventHandler( ActionEvent.ACTION, actionHandler );
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("ActionEvent02A").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      Button src = (Button)e.getTarget();
      String id = src.getId();
      System.out.println( id );
    }
  };
//
  public static void main(String[] args) {
    launch(args);
  }
}
