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

public class TextField03A extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField_1 = new TextField();
    //
    textField_1.textProperty().addListener( textChenge );
    //
    VBox root = VBoxBuilder.create().children( textField_1 ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField03A").build();
    stage.show();
  }
  //
  ChangeListener<String> textChenge = new ChangeListener<String>() {
    Pattern intPattern = Pattern.compile("[0-9]+"); 
    public void changed( ObservableValue<? extends String> value, String oldVal, String newVal ) {
      Matcher match = intPattern.matcher( newVal );
      if( match.matches()==false ){
        // 入力を受け付けない場合は、textプロパティを変更前に戻す
        TextField textField = (TextField)((ReadOnlyProperty)value).getBean();
        textField.setText( oldVal );
      }
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
