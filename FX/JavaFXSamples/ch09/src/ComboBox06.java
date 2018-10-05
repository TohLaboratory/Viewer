import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.util.Callback;
import java.util.HashMap;

public class ComboBox06 extends Application {
 
  HashMap<String,String> map = new HashMap<String,String>();
  
  public void start(Stage stage) throws Exception {
    //
    ComboBox<String> comboBox = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("開く", "保存", "終了") )
      .prefWidth(120).id("file").build();
    comboBox.valueProperty().addListener( selectedValue );
    //
    map.put( "開く", "open.gif" );
    map.put( "保存", "save.gif" );
    map.put( "終了", "exit.gif" );
    //
    comboBox.setButtonCell( new MyListCell() );
    comboBox.setCellFactory( new Callback<ListView<String>, ListCell<String>>(){
      public ListCell<String> call( ListView<String> list ){
        return( new MyListCell() );
      }
    });
    //
    HBox root = HBoxBuilder.create().children( comboBox ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("ComboBox06").build();
    stage.show();
  }
  //
  class MyListCell extends ListCell<String> {
    public void updateItem( String item, boolean empty ){
      super.updateItem( item, empty );
      if( empty ) return;
      setText( item );
      String imageName = map.get(item);
      setGraphic( new ImageView( imageName ) );
    }
  }
  //
  ChangeListener<String> selectedValue = new ChangeListener<String>() {
    public void changed( ObservableValue<? extends String> value, String oldVal, String newVal ) {
      ReadOnlyProperty prop = (ReadOnlyProperty)value;
      ComboBox<String> comboBox = (ComboBox<String>)prop.getBean();
      String id    = comboBox.getId();
      int index    = comboBox.getSelectionModel().getSelectedIndex();
      System.out.println( id +" "+ index +" "+ newVal );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
