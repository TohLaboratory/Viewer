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
    HBox.setHgrow( b[0], Priority.ALWAYS );                // ‰¡•ûŒü‚Ö‚ÌL’£‚ğ‹–‰Â‚·‚é
    b[0].setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE ); // c‰¡‹¤‚É ‚Ç‚±‚Ü‚Å‚àL’£‚·‚é
    //
    HBox.setHgrow( b[1], Priority.ALWAYS );                // ‰¡•ûŒü‚Ö‚ÌL’£‚ğ‹–‰Â‚·‚é
    b[1].setMaxSize( 100, 100 );                           // c‰¡‹¤‚É w’è‚µ‚½•‚Æ‚‚³‚Ü‚ÅL’£‚·‚é
    //
    b[2].setMaxHeight( Double.MAX_VALUE );          // c‚É ‚Ç‚±‚Ü‚Å‚àL’£‚·‚é
    b[3].setMaxHeight( 100 );                       // c‚É w’è‚µ‚½‚‚³‚Ü‚ÅL’£‚·‚é
    //
    HBox.setHgrow( b[4], Priority.ALWAYS );         // ‰¡•ûŒü‚Ö‚ÌL’£‚ğ‹–‰Â‚·‚é‚¾‚¯‚Å‚Íƒ_ƒ
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
