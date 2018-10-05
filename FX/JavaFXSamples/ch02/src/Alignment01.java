import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Alignment01 extends Application {
  public void start(Stage stage) throws Exception {
    HBox[][] hb = new HBox[4][3];
    //
    hb[0][0] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Base"), new Button("Left") )
      .alignment( Pos.BASELINE_LEFT )   // 基準 左
      .build();
    hb[0][1] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Base"), new Button("Center") )
      .alignment( Pos.BASELINE_CENTER ) // 基準 中
      .build();
    hb[0][2] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Base"), new Button("Right") )
      .alignment( Pos.BASELINE_RIGHT )  // 基準 右
      .build();
    //
    hb[1][0] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Top"), new Button("Left") )
      .alignment( Pos.TOP_LEFT )        // 上 左
      .build();
    hb[1][1] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Top"), new Button("Center") )
      .alignment( Pos.TOP_CENTER )      // 上 中
      .build();
    hb[1][2] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Top"), new Button("Right") )
      .alignment( Pos.TOP_RIGHT )       // 上 右
      .build();
    //
    hb[2][0] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Center"), new Button("Left") )
      .alignment( Pos.CENTER_LEFT )     // 中 左
      .build();
    hb[2][1] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Center"), new Button("Center") )
      .alignment( Pos.CENTER )          // 中央
      .build();
    hb[2][2] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Center"), new Button("Right") )
      .alignment( Pos.CENTER_RIGHT )    // 中 右
      .build();
    //
    hb[3][0] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Bottom"), new Button("Left") )
      .alignment( Pos.BOTTOM_LEFT )     // 下 左
      .build();
    hb[3][1] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Bottom"), new Button("Center") )
      .alignment( Pos.BOTTOM_CENTER )   // 下 中
      .build();
    hb[3][2] = HBoxBuilder.create().prefWidth(160).prefHeight(64)
      .children( new Button("Bottom"), new Button("Right") )
      .alignment( Pos.BOTTOM_RIGHT )    // 下 右
      .build();
    //
    GridPane root = new GridPane();
    root.setGridLinesVisible(true);
    for( int r=0 ; r<4 ; r++ ){
      for( int c=0 ; c<3 ; c++ ){
        root.add( hb[r][c], c, r );
      }
    }
    //
    Scene scene = new Scene( root );
    stage = StageBuilder.create()
      .scene( scene ).title("Alignment01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
