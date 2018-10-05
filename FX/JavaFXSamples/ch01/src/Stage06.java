import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Stage06 extends Application {
	
  public void start(Stage stage) throws Exception {
    Label label = new Label("ウインドウ全体の透明度を指定出来る");
    FlowPane root = FlowPaneBuilder.create()
      .children( label )
      .build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Scene06")
      .x(100).y(100).width(300).height(200)
      .opacity( 0.5 ) // ウインドウの透明度を指定
      .build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
