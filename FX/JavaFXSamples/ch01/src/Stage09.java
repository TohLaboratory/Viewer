import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

public class Stage09 extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).title( "Scene09" )
      .x(350).y(350).width(300).height(200)
      .build();
    stage.show();
    //
    Scene scene1A = new Scene( new Group() );
    Stage sub1A = StageBuilder.create()
      .scene( scene1A ).title( "Sub 1A" )
      .x(200).y(200).width(300).height(100)
      .build();
    sub1A.initOwner( stage );
    sub1A.initModality( Modality.WINDOW_MODAL );
    sub1A.show();
    //
    System.out.println( "モーダルウインドウのクローズを待てないで、処理が続行してしまいます" );
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
