import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ComboBox01 extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    ComboBox<String> comboBox_1 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .promptText("ファイル")
      .visibleRowCount(4)
      .prefWidth(120).build();
    //
    ComboBox<String> comboBox_2 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .editable(true)
      .promptText("ファイル") 
      .prefWidth(120).build();
    //
    TextField textField2 = comboBox_2.getEditor();
    //
    HBox root = HBoxBuilder.create().children(comboBox_1, comboBox_2).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ComboBox01").build();
    stage.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
