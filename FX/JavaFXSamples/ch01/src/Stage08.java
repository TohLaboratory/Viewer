import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Stage08 extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).title( "Scene08" )
      .x(100).y(100).width(300).height(100)
      .build();
    stage.show();
    //
    Scene scene1A = new Scene( new Group() );
    Stage sub1A = StageBuilder.create()
      .scene( scene1A ).title( "Sub 1A" )
      .x(200).y(200).width(300).height(100)
      .build();
    sub1A.initOwner( stage );
    sub1A.show();
    //
    Scene scene1B = new Scene( new Group() );
    Stage sub1B = StageBuilder.create()
      .scene( scene1B ).title( "Sub 1B" )
      .x(300).y(300).width(300).height(100)
      .build();
    sub1B.initOwner( sub1A );
    sub1B.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
