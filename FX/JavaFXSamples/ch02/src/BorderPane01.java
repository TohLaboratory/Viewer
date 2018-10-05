import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class BorderPane01 extends Application {
  public void start(Stage stage) throws Exception {
    Button center = new Button( "íÜâõ" );
    Button top    = new Button( "è„" );
    Button left   = new Button( "ç∂" );
    Button right  = new Button( "âE" );
    Button bottom = new Button( "â∫" );
    //
    BorderPane bp = BorderPaneBuilder.create()
      .center(center).top(top).left(left).right(right).bottom(bottom)
      .build();
    //
    Scene scene = new Scene( bp );
    stage = StageBuilder.create()
      .scene( scene ).title("BorderPane01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
