import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class FillProperty01 extends Application {
  public void start(Stage stage) throws Exception {
    ColorPicker colorPicker = new ColorPicker();
    Scene scene = new Scene( new Group( colorPicker ) );
    stage = StageBuilder.create().width(300).height(200)
      .scene( scene ).title( "FillProperty01" ).build();
    //
    scene.fillProperty().bind( colorPicker.valueProperty() );
    //
    stage.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
