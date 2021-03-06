import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class HBox02 extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5") };
    //
    HBox.setHgrow( b[0], Priority.ALWAYS );                // 横方向への伸張を許可する
    b[0].setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE ); // 縦横共に どこまでも伸張する
    //
    HBox.setHgrow( b[1], Priority.ALWAYS );                // 横方向への伸張を許可する
    b[1].setMaxSize( 100, 100 );                           // 縦横共に 指定した幅と高さまで伸張する
    //
    b[2].setMaxHeight( Double.MAX_VALUE );          // 縦に どこまでも伸張する
    b[3].setMaxHeight( 100 );                       // 縦に 指定した高さまで伸張する
    //
    HBox.setHgrow( b[4], Priority.ALWAYS );         // 横方向への伸張を許可するだけではダメ
    //
    HBox hb = HBoxBuilder.create()
      .children( b ).build();
    //
    Scene scene = new Scene( hb );
    stage = StageBuilder.create().
      scene( scene ).title("HBox02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
