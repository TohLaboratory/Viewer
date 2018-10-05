import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioButtonBuilder;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleGroupBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class RadioButton01A extends Application {

  public void start(Stage stage) throws Exception {

    RadioButton rb1 = RadioButtonBuilder.create().text("開く").id("open")
      .selected(true).build();
    RadioButton rb2 = RadioButtonBuilder.create().text("保存").id("save").build();
    RadioButton rb3 = RadioButtonBuilder.create().text("終了").id("exit").build();
    //
    ToggleGroup group = ToggleGroupBuilder.create()
      .toggles( rb1, rb2, rb3 ).build();
    group.selectedToggleProperty().addListener( new CheckRadioButton() );

    VBox root = VBoxBuilder.create().spacing(10)
      .children( rb1, rb2, rb3 ).build();

    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(300)
      .scene( scene ).title("RadioButton01A").build();
    stage.show();
  }
  //
  class CheckRadioButton implements ChangeListener<Toggle> {
    public void changed( ObservableValue<? extends Toggle> value, Toggle oldVal, Toggle newVal ) {
      RadioButton bt1 = (RadioButton)newVal;
      RadioButton bt2 = (RadioButton)oldVal;
      System.out.print( "今クリックされたのは="+ bt1.getId() );
      if( bt2 !=null ){
        System.out.print( " / さっき選ばれていたのは="+ bt2.getId() );
      }
      System.out.println();
    }
  };
//
  public static void main(String[] args) {
    launch(args);
  }
}
