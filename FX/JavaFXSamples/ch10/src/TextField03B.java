import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField03B extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField_1 = new TextField();
    //
    textField_1.textProperty().addListener( new LimitListener(10) );
    //
    VBox root = VBoxBuilder.create().children( textField_1 ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField03B").build();
    stage.show();
  }
  //
  class LimitListener implements ChangeListener<String> {
    int limitCount; // 入力制限文字数を記録
    LimitListener( int limitCount ){
      this.limitCount = limitCount;
    }
    public void changed( ObservableValue<? extends String> value, String oldVal, String newVal ) {
      if( limitCount < newVal.length() ){
        // 入力を受け付けない場合は、textプロパティを変更前に戻す
        TextField textField = (TextField)((ReadOnlyProperty)value).getBean();
        textField.setText( oldVal );
      }
    }
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
