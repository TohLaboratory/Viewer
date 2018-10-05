import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceBoxBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ChoiceBox01 extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    ChoiceBox<String> choiceBox = ChoiceBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .build();
    choiceBox.valueProperty().addListener(new ChangeListener<String>() {
      public void changed( ObservableValue<? extends String> value, String oldVal, String newVal ) {
        System.out.println( " 変更前="+ oldVal +" / 変更後="+ newVal );
      }
    });
    //
    HBox root = HBoxBuilder.create().children( choiceBox ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ChoiceBox01").build();
    stage.show();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
