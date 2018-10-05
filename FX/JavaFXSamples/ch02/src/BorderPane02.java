import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class BorderPane02 extends Application {
  public void start(Stage stage) throws Exception {
    Button center = new Button( "íÜâõ" );
    Button top    = new Button( "è„" );
    Button left   = new Button( "ç∂" );
    Button right  = new Button( "âE" );
    Button bottom = new Button( "â∫" );
    //
    BorderPane bp = BorderPaneBuilder.create()
      .center(center).top(top).left(left).right(right).bottom(bottom)
      .prefWidth(240).prefHeight(160)
      .build();
    //
    center.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
    top.setMaxWidth( Double.MAX_VALUE );
    bottom.setMaxWidth( Double.MAX_VALUE );
    left.setMaxHeight( Double.MAX_VALUE );
    right.setMaxHeight( Double.MAX_VALUE );
    //
    Scene scene = new Scene( bp );
    stage = StageBuilder.create()
      .scene( scene ).title("BorderPane02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
