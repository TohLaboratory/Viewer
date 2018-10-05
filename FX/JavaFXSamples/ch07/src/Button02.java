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
    Button button = new Button( "�{�^����������Ă��鎞�Ԃ��v�� \n armed�v���p�e�B�𗘗p" );
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
      if( newVal==true ){ // armed�v���p�e�B�� true�ɂȂ� -> �{�^����������n�߂�
        time1 = System.currentTimeMillis();
      }
      else{ //armed�v���p�e�B�� false�ɂȂ� ->  �{�^���������ꂽ
        long time2 = System.currentTimeMillis();
        long holdTime = time2 - time1;
        System.out.println( "�{�^����������Ă������Ԃ� : "+ holdTime +" �~���b" );
      }
    }
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
