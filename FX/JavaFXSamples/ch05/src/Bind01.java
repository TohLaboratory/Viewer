import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Bind01 extends Application {
	
  public void start(Stage stage) throws Exception {
    //
    Slider slider1 = new Slider( 0.0, 1.0, 0.5 );
    Slider slider2 = new Slider( 0.0, 1.0, 0.5 );
    //
    DoubleProperty slider1Value = slider1.valueProperty();
    DoubleProperty slider2Value = slider2.valueProperty();
    slider2Value.bind( slider1Value ); 
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( slider1, slider2 ).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("Bind01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
