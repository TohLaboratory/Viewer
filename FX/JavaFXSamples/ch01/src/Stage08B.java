import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Stage08B extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).title( "Stage08B" )
      .x(250).y(100).width(300).height(100)
      .build();
    stage.show();
    //
    Scene scene1A = new Scene( new Group() );
    Stage sub1A = StageBuilder.create()
      .scene( scene1A ).title( "Sub 1A" )
      .x(300).y(200).width(300).height(100)
      .build();
    sub1A.initOwner( stage );
    sub1A.initModality( Modality.APPLICATION_MODAL );
    sub1A.show();
    //
    Scene scene2A = new Scene( new Group() );
    Stage sub2A = StageBuilder.create()
      .scene( scene2A ).title( "Sub 2A" )
      .x(300).y(300).width(300).height(100)
      .build();
    sub2A.initOwner( stage );
    sub2A.initModality( Modality.APPLICATION_MODAL );
    sub2A.show();
    //
    Scene scene2B = new Scene( new Group() );
    Stage sub2B = StageBuilder.create()
      .scene( scene2B ).title( "Sub 2B" )
      .x(350).y(400).width(300).height(100)
      .build();
    sub2B.initOwner( sub2A );
    sub2B.initModality( Modality.APPLICATION_MODAL );
    sub2B.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
