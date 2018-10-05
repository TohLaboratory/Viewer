import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class GridPane02 extends Application {
  public void start(Stage stage) throws Exception {
    GridPane gp = GridPaneBuilder.create()
      .gridLinesVisible(true).build();
    //
    int colSize = 5;
    int rowSize = 2;
    for( int r=0 ; r< rowSize ; r++ ){
      for( int c=0 ; c< colSize ; c++ ){
        String btTxt = (r+1) +""+ (char)('A'+c);
        Button bt = new Button( btTxt );
        gp.add( bt, c, r ); 
        GridPane.setHgrow( bt, Priority.ALWAYS );
        GridPane.setVgrow( bt, Priority.ALWAYS );
        bt.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
        GridPane.setMargin( bt, new Insets(4) ); 
      }
    }
    //
    Scene scene = new Scene( gp );
    stage = StageBuilder.create()
      .scene( scene ).title("GridPane02").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
