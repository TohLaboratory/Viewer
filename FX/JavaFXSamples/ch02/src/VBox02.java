import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class VBox02 extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5") };
    //
    VBox.setVgrow( b[0], Priority.ALWAYS );                // 縦方向への伸張を許可する
    b[0].setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE ); // 縦横共に どこまでも伸張する
    //
    VBox.setVgrow( b[1], Priority.ALWAYS );                // 縦向への伸張を許可する
    b[1].setMaxSize( 250, 100 );                           // 縦横共に 指定した幅と高さまで伸張する
    //
    b[2].setMaxWidth( Double.MAX_VALUE );          // 横に どこまでも伸張する
    b[3].setMaxWidth( 250 );                       // 横に 指定した高さまで伸張する
    //
    VBox.setVgrow( b[4], Priority.ALWAYS );        // 縦方向への伸張を許可するだけではダメ
    //
    VBox vb = VBoxBuilder.create().spacing( 10 ).prefWidth( 170 )
      .children( b ).build();
    //
    Scene scene = new Scene( vb );
    stage = StageBuilder.create().width(190)
      .scene( scene ).title("VBox02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
