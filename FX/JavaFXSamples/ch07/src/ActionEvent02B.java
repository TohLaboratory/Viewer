import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ActionEvent02B extends Application {

  public void start(Stage stage) throws Exception {
    //
    ImageView imgv_open = new ImageView( "open.gif" );
    Button button = ButtonBuilder.create().graphic( imgv_open ).id( "open" ).build();
    //
    Label label_1 = LabelBuilder.create().text( "ŠJ‚­ _O" )
      .mnemonicParsing( true )
      .labelFor( button )
      .build();
    //
    HBox root = HBoxBuilder.create().spacing(10).children( label_1, button ).build();
    Scene scene = new Scene(root);
    scene.addEventHandler( ActionEvent.ACTION, actionHandler );
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("ActionEvent02B").build();
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
