import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class BackgroundImge01 extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{ 
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4") };
    //
    FlowPane fp = FlowPaneBuilder.create()
      .prefWidth(320).prefHeight(100) 
      .style( "-fx-background-image:url(\"4-87783-185-1.png\")" ) // îwåiâÊëúÇÃê›íË
      .children( b ).build();
    //
    Scene scene = new Scene( fp );
    stage = StageBuilder.create()
      .scene( scene ).title("BackgroundImge01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
