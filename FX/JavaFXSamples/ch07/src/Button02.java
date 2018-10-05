import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Button02 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Button button = new Button( "ボタンが押されている時間を計る \n armedプロパティを利用" );
    button.armedProperty().addListener( new CheckArmedProperty() );
    //
    VBox root = VBoxBuilder.create().children( button ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Button02").build();
    stage.show();
  }
  //
  class CheckArmedProperty implements ChangeListener<Boolean> {
    long time1;
    public void changed( ObservableValue<? extends Boolean> obj, Boolean oldVal, Boolean newVal ) {
      if( newVal==true ){ // armedプロパティが trueになる -> ボタンが押され始めた
        time1 = System.currentTimeMillis();
      }
      else{ //armedプロパティが falseになる ->  ボタンが放された
        long time2 = System.currentTimeMillis();
        long holdTime = time2 - time1;
        System.out.println( "ボタンが押されていた時間は : "+ holdTime +" ミリ秒" );
      }
    }
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
