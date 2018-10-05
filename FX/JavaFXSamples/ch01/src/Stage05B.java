import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Stage05B extends Application {
  public void start(Stage stage) throws Exception {
    //
    FlowPane root = FlowPaneBuilder.create()
      .children( new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5") )
      .build();
    //
    Scene scene = new Scene( root );
    stage = StageBuilder.create()
      .minWidth( 180 ).minHeight( 80 )
      .scene( scene ).title("Stage05B").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
