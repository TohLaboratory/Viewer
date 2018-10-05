import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class ComboBox04 extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    ComboBox<String> comboBox_1 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .editable(true)
      .id("comboBox_1").build();
    //
    ComboBox<String> comboBox_2 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("切り取り", "コピー", "貼り付け", "削除") )
      .id("comboBox_2").build();
    //
    ComboBox<String> comboBox_3 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("検索", "次を検索", "前を検索", "置換") )
      .id("comboBox_3").build();
    //
    comboBox_1.valueProperty().addListener( selectedValue );
    comboBox_2.valueProperty().addListener( selectedValue );
    comboBox_3.valueProperty().addListener( selectedValue );
    comboBox_1.setOnAction( actionComboBox );
    comboBox_2.setOnAction( actionComboBox );
    comboBox_3.setOnAction( actionComboBox );
    //
    comboBox_1.setValue( "ファイル" );               // 項目名を指定する
    comboBox_2.getSelectionModel().select("コピー"); // 項目名で選択する
    comboBox_3.getSelectionModel().select(3);        // インデックス番号で選択する
    //
    HBox root = HBoxBuilder.create().children(comboBox_1, comboBox_2, comboBox_3).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ComboBox04").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionComboBox = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      ComboBox<String> comboBox = (ComboBox<String>)e.getTarget();
      String id    = comboBox.getId();
      String value = comboBox.getValue();
      System.out.println( "アクションイベント "+ id +" "+ value );
    }
  };
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
