import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleButtonBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ToggleButton01 extends Application {

  public void start(Stage stage) throws Exception {

    ToggleButton tb1 = ToggleButtonBuilder.create().text("äJÇ≠").id("open").build();
    ToggleButton tb2 = ToggleButtonBuilder.create().text("ï€ë∂").id("save").build();
    ToggleButton tb3 = ToggleButtonBuilder.create().text("èIóπ").id("exit").build();
    //
    CheckBoxListener listener = new CheckBoxListener();
    tb1.selectedProperty().addListener( listener );
    tb2.selectedProperty().addListener( listener );
    tb3.selectedProperty().addListener( listener );
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( tb1, tb2, tb3 ).build();

    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("ToggleButton01").build();
    stage.show();
  }
  //
  class CheckBoxListener implements ChangeListener<Boolean> {
    public void changed( ObservableValue<? extends Boolean> value, Boolean oldVal, Boolean newVal ) {
      BooleanProperty prop = (BooleanProperty)value;
      ToggleButton checkbox = (ToggleButton)prop.getBean();
      String id = checkbox.getId();
      System.out.println( id +":"+ newVal );
    }
  }
//
  public static void main(String[] args) {
    launch(args);
  }
}
