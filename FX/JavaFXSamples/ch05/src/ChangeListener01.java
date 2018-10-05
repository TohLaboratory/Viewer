import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ChangeListener01 extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).width(300).height(200).title("ChangeListener01").build();
    //
    stage.widthProperty().addListener(new ChangeListener<Number>() {
      public void changed( ObservableValue<? extends Number> observable, Number oldValue, Number newValue ) {
        System.out.println( "ïœçXëO="+ oldValue +" / ïœçXå„="+ newValue );
      }
    });
    //
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
