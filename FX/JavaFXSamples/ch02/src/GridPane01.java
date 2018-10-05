import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class GridPane01 extends Application {
  public void start(Stage stage) throws Exception {
    GridPane gp = GridPaneBuilder.create()
    .hgap(5).vgap(5)
    .build();
    //
    int colSize = 5; // 列の数
    int rowSize = 2; // 行の数
    for( int r=0 ; r< rowSize ; r++ ){
      for( int c=0 ; c< colSize ; c++ ){
        String btTxt = (r+1) +""+ (char)('A'+c);
        Button bt = new Button( btTxt );
        gp.add( bt, c, r ); // 各セルにGUI要素を配置
      }
    }
    //
    Scene scene = new Scene( gp );
    stage = StageBuilder.create()
      .scene( scene ).title("GridPane01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
