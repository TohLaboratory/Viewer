import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class BorderPane01 extends Application {
  public void start(Stage stage) throws Exception {
    Button center = new Button( "����" );
    Button top    = new Button( "��" );
    Button left   = new Button( "��" );
    Button right  = new Button( "�E" );
    Button bottom = new Button( "��" );
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
