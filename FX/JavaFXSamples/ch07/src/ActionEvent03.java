import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ActionEvent03 extends Application {

  public void start(Stage stage) throws Exception {
    //
    ImageView imgv_open = new ImageView( "open.gif" );
    ImageView imgv_exit = new ImageView( "exit.gif" );

    Button bt1 = ButtonBuilder.create().text( "開く Alt+[" ).id("open").build();
    Button bt2 = ButtonBuilder.create().graphic( imgv_open ).id("save").build();
    Button bt3 = ButtonBuilder.create().graphic( imgv_exit ).build();
    Label label3 = LabelBuilder.create().text( "終了 Ctrl+Alt+X" ).id("exit")
      .mnemonicParsing(true).labelFor(bt3).build();

    // ニーモニックには Altキーを含む必要がある
    Mnemonic mn1 = new Mnemonic( bt1, KeyCombination.keyCombination("Alt+[") );
    Mnemonic mn2 = new Mnemonic( bt2, KeyCombination.keyCombination("Shift+Alt+S") );
    Mnemonic mn3 = new Mnemonic( label3, KeyCombination.keyCombination("Ctrl+Alt+X") );
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( bt1, bt2, label3, bt3 ).build();
    //
    root.addEventHandler( ActionEvent.ACTION, actionHandler );
    //
    Scene scene = new Scene(root);
    scene.addMnemonic( mn1 );
    scene.addMnemonic( mn2 );
    scene.addMnemonic( mn3 );
    //
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("ActionEvent03").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      Control src = (Control)e.getTarget();
      String id = src.getId();
      System.out.println( id );
    }
  };
//
  public static void main(String[] args) {
    launch(args);
  }
}
