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

public class ActionEvent01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Button bt1 = ButtonBuilder.create().text( "‚Í‚¢" )
      .id("yes")
      .defaultButton(true)
      .build();
    Button bt2 = ButtonBuilder.create().text( "‚¢‚¢‚¦" )
      .id("no")
      .cancelButton(true)
      .build();
    //
    VBox root = VBoxBuilder.create().spacing(10).children( bt1, bt2 ).build();
    Scene scene = new Scene(root);
    scene.addEventHandler( ActionEvent.ACTION, actionHandler );
    //
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("ActionEvent01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      Button src = (Button)e.getTarget();
      String text = src.getId();
      System.out.println( text );
    }
  };
//
  public static void main(String[] args) {
    launch(args);
  }
}
