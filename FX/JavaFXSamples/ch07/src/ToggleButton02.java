import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleButtonBuilder;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleGroupBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ToggleButton02 extends Application {

  public void start(Stage stage) throws Exception {

    ToggleButton tb1 = ToggleButtonBuilder.create().text("開く").id("open").build();
    ToggleButton tb2 = ToggleButtonBuilder.create().text("保存").id("save").build();
    ToggleButton tb3 = ToggleButtonBuilder.create().text("終了").id("exit").build();
    //
    ToggleGroup group = ToggleGroupBuilder.create()
      .toggles( tb1, tb2, tb3 ).build();
    group.selectedToggleProperty().addListener( new CheckToggleButton() );

    VBox root = VBoxBuilder.create().spacing(10)
      .children( tb1, tb2, tb3 ).build();

    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("ToggleButton02").build();
    stage.show();
  }
  //
  class CheckToggleButton implements ChangeListener<Toggle> {
    public void changed( ObservableValue<? extends Toggle> value, Toggle oldVal, Toggle newVal ) {
      ToggleButton bt1 = (ToggleButton)newVal;
      ToggleButton bt2 = (ToggleButton)oldVal;
      if( bt1 !=null ){
        System.out.print( " クリックされたのは="+ bt1.getId() );
      }
      if( bt2 !=null ){
        System.out.print( " 解除されたのは="+ bt2.getId() );
      }
      System.out.println();
    }
  };
//
  public static void main(String[] args) {
    launch(args);
  }
}
