import javafx.application.Application;
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

public class ComboBox03 extends Application {
 
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
    comboBox_1.setOnAction( actionComboBox );
    comboBox_2.setOnAction( actionComboBox );
    //
    HBox root = HBoxBuilder.create().children(comboBox_1, comboBox_2).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ComboBox03").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionComboBox = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      ComboBox<String> comboBox = (ComboBox<String>)e.getTarget();
      String id    = comboBox.getId();
      int index    = comboBox.getSelectionModel().getSelectedIndex();
      String value = comboBox.getValue();
      System.out.println( "Action "+ id +" "+ index +" "+ value );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
