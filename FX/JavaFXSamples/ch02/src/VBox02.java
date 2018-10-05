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
    VBox.setVgrow( b[0], Priority.ALWAYS );                // c•ûŒü‚Ö‚ÌL’£‚ğ‹–‰Â‚·‚é
    b[0].setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE ); // c‰¡‹¤‚É ‚Ç‚±‚Ü‚Å‚àL’£‚·‚é
    //
    VBox.setVgrow( b[1], Priority.ALWAYS );                // cŒü‚Ö‚ÌL’£‚ğ‹–‰Â‚·‚é
    b[1].setMaxSize( 250, 100 );                           // c‰¡‹¤‚É w’è‚µ‚½•‚Æ‚‚³‚Ü‚ÅL’£‚·‚é
    //
    b[2].setMaxWidth( Double.MAX_VALUE );          // ‰¡‚É ‚Ç‚±‚Ü‚Å‚àL’£‚·‚é
    b[3].setMaxWidth( 250 );                       // ‰¡‚É w’è‚µ‚½‚‚³‚Ü‚ÅL’£‚·‚é
    //
    VBox.setVgrow( b[4], Priority.ALWAYS );        // c•ûŒü‚Ö‚ÌL’£‚ğ‹–‰Â‚·‚é‚¾‚¯‚Å‚Íƒ_ƒ
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
