import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Shape01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Group shapeGroup = new Group();
    Circle circle = new Circle( 100, 100, 100, Color.rgb(255, 0, 0, 0.5) );
    Line line1 = new Line( 0, 0, 200, 200 );
    Line line2 = new Line( 0, 200, 200, 0 );
    shapeGroup.getChildren().addAll( circle, line1, line2 );

    Label label_1 = LabelBuilder.create().graphic( shapeGroup ).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Shape01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
