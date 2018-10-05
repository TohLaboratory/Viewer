import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class CheckBox02 extends Application {
 
  public void start(Stage stage) throws Exception {

    Button button = new Button("ボタン");
    CheckBox check = new CheckBox("選択可能");
    button.disableProperty().bind( check.selectedProperty().not() );
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( check, button ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("CheckBox02").build();
    stage.show();
  }
//
  public static void main(String[] args) {
    launch(args);
  }
}
