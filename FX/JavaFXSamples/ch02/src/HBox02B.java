import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class HBox02B extends Application {
  public void start(Stage stage) throws Exception {
    Button[] b = new Button[]{ 
      new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5") };
    b[0].setPrefSize( 120, 60 );  
    b[1].setPrefSize( 60, 120 );  
    b[2].setPrefSize( 120, 120 ); 
    //
    HBox hb = HBoxBuilder.create()
      // レイアウトの幅と高さの推奨値も設定。この場合 レイアウトの推奨サイズの方が小さい
      .prefWidth( 240 ).prefHeight( 60 ) 
      .children( b ).build();
    //
    Scene scene = new Scene( hb );
    stage = StageBuilder.create()
      .scene( scene ).title("HBox02B").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
