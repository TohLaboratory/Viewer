import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Flow01A extends Application {
  public void start(Stage stage) throws Exception {
    //
    FlowPane fp = FlowPaneBuilder.create()
      .children( new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4") )
      .build();
    //
    Scene scene = new Scene( fp );
    stage = StageBuilder.create()
      .scene( scene ).title("Flow01A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
