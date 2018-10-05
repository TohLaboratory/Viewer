import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ComboBox03A extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    ComboBox<String> comboBox_1 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .id("comboBox_1")
      .prefWidth(120).build();
    //
    ComboBox<String> comboBox_2 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .editable(true)
      .id("comboBox_2")
      .prefWidth(120).build();
    //
    comboBox_1.valueProperty().addListener( selectedValue );
    comboBox_2.valueProperty().addListener( selectedValue );
    //
    HBox root = HBoxBuilder.create().children(comboBox_1, comboBox_2).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ComboBox03A").build();
    stage.show();
  }
  //
  ChangeListener<String> selectedValue = new ChangeListener<String>() {
    public void changed( ObservableValue<? extends String> value, String oldVal, String newVal ) {
      ReadOnlyProperty prop = (ReadOnlyProperty)value;
      ComboBox<String> comboBox = (ComboBox<String>)prop.getBean();
      String id    = comboBox.getId();
      int index    = comboBox.getSelectionModel().getSelectedIndex();
      System.out.println( "valueProperty "+ id +" "+ index +" "+ newVal );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
