import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class GridPane04 extends Application {
  public void start(Stage stage) throws Exception {
    //
    int colSize = 5; int rowSize = 2;
    //
    ColumnConstraints[] col = new ColumnConstraints[colSize];
    col[0] = ColumnConstraintsBuilder.create()
        .hgrow(Priority.ALWAYS) .percentWidth(40) .build();
    col[1] = ColumnConstraintsBuilder.create()
        .hgrow(Priority.ALWAYS) .prefWidth(100) .build();
    col[2] = ColumnConstraintsBuilder.create()
        .hgrow(Priority.ALWAYS) .maxWidth(60)  .build();
    col[3] = ColumnConstraintsBuilder.create()
        .hgrow(Priority.ALWAYS) .minWidth(60)  .build();
    col[4] = ColumnConstraintsBuilder.create().build();
    //
    GridPane gp = GridPaneBuilder.create()
      .columnConstraints( col )
      .gridLinesVisible(true).build();
    //
    for( int r=0 ; r< rowSize ; r++ ){
      for( int c=0 ; c< colSize ; c++ ){
        String btTxt = (r+1) +""+ (char)('A'+c);
        Button bt = new Button( btTxt );
        gp.add( bt, c, r ); 
        GridPane.setVgrow( bt, Priority.ALWAYS );
        bt.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
        GridPane.setMargin( bt, new Insets(10) ); 
      }
    }
    //
    Scene scene = new Scene( gp );
    stage = StageBuilder.create()
      .scene( scene ).title("GridPane04").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
