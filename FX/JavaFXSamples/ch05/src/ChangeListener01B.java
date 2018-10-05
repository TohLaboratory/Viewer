import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ChangeListener01B extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).width(300).height(200).title("ChangeListener01B").build();
    //
    ReadOnlyBooleanProperty focusedProp = stage.focusedProperty();
    System.out.println( "focusProp "+ focusedProp );
    focusedProp.addListener( new CheckBooleanProperty() );
    //
    stage.show();
  }
  //
  class CheckBooleanProperty implements ChangeListener<Boolean> {
    public void changed( ObservableValue<? extends Boolean> obj, Boolean oldVal, Boolean newVal ) {
      ReadOnlyBooleanProperty prop = (ReadOnlyBooleanProperty)obj;
      String propName = prop.getName();
      System.out.println( propName +" : ïœçXëO="+ oldVal +" ïœçXå„="+ newVal );
    }
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
