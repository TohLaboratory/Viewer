import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class GridPane03 extends Application {
  public void start(Stage stage) throws Exception {
    Button b_1A    = new Button( "1A" );
    Button b_1B    = new Button( "1B" );
    Button b_1C    = new Button( "1C" );
    Button b_1D    = new Button( "1D" );
    Button b_1E    = new Button( "1E" );
    Button b_2A_2B = new Button( "2A-2B" );
    Button b_2C_2E = new Button( "2C-2E" );
    Button b_3A    = new Button( "3A" );
    Button b_4A    = new Button( "4A" );
    Button b_5A    = new Button( "5A" );
    Button b_3B_5B = new Button( "3B-5B" );
    Button b_3C_4D = new Button( "3C-4D" );
    GridPane.setConstraints( b_1A,    0, 0 );
    GridPane.setConstraints( b_1B,    1, 0 );
    GridPane.setConstraints( b_1C,    2, 0 );
    GridPane.setConstraints( b_1D,    3, 0 );
    GridPane.setConstraints( b_1E,    4, 0 );
    GridPane.setConstraints( b_2A_2B, 0, 1, 2, 1 ); // â°2Ç¬ èc1
    GridPane.setConstraints( b_2C_2E, 2, 1, 3, 1 ); // â°3Ç¬ èc1Ç¬
    GridPane.setConstraints( b_3A,    0, 2 );
    GridPane.setConstraints( b_4A,    0, 3 );
    GridPane.setConstraints( b_5A,    0, 4 );
    GridPane.setConstraints( b_3B_5B, 1, 2, 1, 3 ); // â°1Ç¬ èc3Ç¬
    GridPane.setConstraints( b_3C_4D, 2, 2, 2, 2 ); // â°2Ç¬ èc2Ç¬
    //
    ObservableList<Button> bts = FXCollections.observableArrayList();
    bts.addAll( b_1A, b_1B, b_1C, b_1D, b_1E, b_2A_2B, b_2C_2E, b_3A, b_4A, b_5A, b_3B_5B, b_3C_4D );
    //
    GridPane gp = GridPaneBuilder.create().children( bts ).build();
    //
    int cnt = bts.size();
    for( int i=0 ; i<cnt ; i++ ){
      Button bt = bts.get(i);
      GridPane.setHgrow( bt, Priority.ALWAYS );
      GridPane.setVgrow( bt, Priority.ALWAYS );
      bt.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
      GridPane.setMargin( bt, new Insets(2) ); 
    }
    //
    Scene scene = new Scene( gp );
    stage = StageBuilder.create()
      .scene( scene ).title("GridPane03").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
