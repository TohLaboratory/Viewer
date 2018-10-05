import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Stage09A extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).title( "Scene09A" )
      .x(350).y(350).width(300).height(200)
      .build();
    stage.show();
    //
    Stage msgBox = makeMsgBox( stage, "メッセージボックス", "このウインドウを閉じるまで待ちます" );
    msgBox.showAndWait(); // JavaFX 2.2 で新設されたメソッド
    System.out.println( "メッセージボックスが終了しました" );
  }
  //-----------------------------------------
  Stage makeMsgBox( Stage owner, String title, String msg ){
    final Stage msgBox = StageBuilder.create()
      .title( title )
      .x(450).y(450)
      .style( StageStyle.UTILITY )
      .resizable(false) // ウインドウサイズ固定
      .build();
    msgBox.initOwner( owner );
    msgBox.initModality( Modality.WINDOW_MODAL );
    //
    Label label = new Label( msg );
    Button ok = new Button( "了解" );
    ok.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        msgBox.hide();
      }
    });
    VBox root = new VBox();
    root.getChildren().addAll( label, ok );
    Scene scene = new Scene( root );
    msgBox.setScene( scene );
    //
    return( msgBox );
  }
  //------------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
