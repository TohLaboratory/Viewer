import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class CheckBox01A extends Application {
 
  public void start(Stage stage) throws Exception {

    CheckBox ch1 = CheckBoxBuilder.create().text("äJÇ≠").id("open").build();
    CheckBox ch2 = CheckBoxBuilder.create().text("ï€ë∂").id("save").build();
    CheckBox ch3 = CheckBoxBuilder.create().text("èIóπ").id("exit").build();

    CheckBoxListener listener = new CheckBoxListener();
    ch1.selectedProperty().addListener( listener );
    ch2.selectedProperty().addListener( listener );
    ch3.selectedProperty().addListener( listener );
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( ch1, ch2, ch3 ).build();

    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("CheckBox01A").build();
    stage.show();
  }
  //
  class CheckBoxListener implements ChangeListener<Boolean> {
    public void changed( ObservableValue<? extends Boolean> value, Boolean oldVal, Boolean newVal ) {
      BooleanProperty prop = (BooleanProperty)value;
      CheckBox checkbox = (CheckBox)prop.getBean();
      String id = checkbox.getId();
      System.out.println( id +":"+ newVal );
    }
  }
//
  public static void main(String[] args) {
    launch(args);
  }
}
