import javafx.application.Application;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

public class Stage06A extends Application {
  public void start(Stage stage) throws Exception {
    Label label = new Label("透明ウインドウ");
    label.setFont( new Font(50) );
    FlowPane root = FlowPaneBuilder.create()
      .children( label )
      .build();
    //
    Scene scene = SceneBuilder.create()
      .fill( Color.TRANSPARENT ) // ウインドウの背景も透明にする
      .root( root ).build();
    //
    stage = StageBuilder.create()
      .style( StageStyle.TRANSPARENT ) // ウインドウを透明にする
      .scene( scene )
      .x(300).y(200)
      .width(300).height(200)
      .build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
