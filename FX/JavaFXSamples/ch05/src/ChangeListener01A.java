import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ChangeListener01A extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).width(300).height(200).title("ChangeListener01A").build();
    //
    ReadOnlyDoubleProperty widthProp = stage.widthProperty();
    System.out.println( "widthProp "+ widthProp );
    widthProp.addListener( new CheckWidthProperty() );
    //
    stage.show();
  }
  //
  class CheckWidthProperty implements ChangeListener<Number> {
    public void changed( ObservableValue<? extends Number> observable, Number oldValue, Number newValue ) {
      System.out.println( "observable "+ observable );
      ReadOnlyDoubleProperty prop = (ReadOnlyDoubleProperty)observable;
      String propName = prop.getName();
      Stage source = (Stage)prop.getBean();
      System.out.println( source +" "+ propName +" : ïœçXëO="+ oldValue +" ïœçXå„="+ newValue );
    }
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
