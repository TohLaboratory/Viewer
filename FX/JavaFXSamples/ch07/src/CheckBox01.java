import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class CheckBox01 extends Application {
 
  public void start(Stage stage) throws Exception {

    CheckBox ch1 = new CheckBox("開く");
      ch1.setId("open");
    CheckBox ch2 = CheckBoxBuilder.create().text("保存").id("save")
      .selected(true)
      .build();
    CheckBox ch3 = CheckBoxBuilder.create().text("終了").id("exit").build();
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( ch1, ch2, ch3 ).build();
    Scene scene = new Scene(root);
    scene.addEventHandler( ActionEvent.ACTION, actionHandler );
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("CheckBox01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    // 選択しても 解除しても アクションベントが発生する。
    public void handle( ActionEvent e ){
      CheckBox src = (CheckBox)e.getTarget();
      String id = src.getId();
      boolean selected = src.isSelected();
      if( selected ) System.out.println( id +" 選択されました" );
      else           System.out.println( id +" 解除されました" );
    }
  };
//
  public static void main(String[] args) {
    launch(args);
  }
}
