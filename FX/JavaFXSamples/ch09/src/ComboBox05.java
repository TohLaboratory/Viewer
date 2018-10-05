import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class ComboBox05 extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    ImageView imgv_open = new ImageView( "open.gif" );
    ImageView imgv_save = new ImageView( "save.gif" );
    ImageView imgv_exit = new ImageView( "exit.gif" );
    Label label_open = new Label( "開く", new ImageView( "open.gif" ) );
    Label label_save = new Label( "保存", new ImageView( "save.gif" ) );
    Label label_exit = new Label( "終了", new ImageView( "exit.gif" ) );
    //
    ComboBox<ImageView> comboBox_1 = ComboBoxBuilder.<ImageView>create()
      .items( FXCollections.observableArrayList(imgv_open, imgv_save, imgv_exit) )
      .promptText("ファイルに対する操作 1")
      .id("file_1").prefWidth(120).build();
    comboBox_1.valueProperty().addListener( selectedValue );
    //
    ComboBox<Label> comboBox_2 = ComboBoxBuilder.<Label>create()
      .items( FXCollections.observableArrayList(label_open, label_save, label_exit) )
      .promptText("ファイルに対する操作 2")
      .id("file_2").prefWidth(120).build();
    comboBox_2.valueProperty().addListener( selectedValue );
    //
    HBox root = HBoxBuilder.create().children( comboBox_1, comboBox_2 ).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ComboBox05").build();
    stage.show();
  }
  //
  ChangeListener<Node> selectedValue = new ChangeListener<Node>() {
    public void changed( ObservableValue<? extends Node> value, Node oldVal, Node newVal ) {
      ReadOnlyProperty prop = (ReadOnlyProperty)value;
      ComboBox<Node> comboBox = (ComboBox<Node>)prop.getBean();
      String id    = comboBox.getId();
      int index    = comboBox.getSelectionModel().getSelectedIndex();
      System.out.println( id +" "+ index +" "+ newVal );
    }
  };
  
  public static void main(String[] args) {
    launch(args);
  }
}
