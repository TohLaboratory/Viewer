import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.ListViewBuilder;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ComboBox02 extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    ComboBox<String> comboBox = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .id("comboBox").prefWidth(120).build();
    SelectionModel<String> selectionModel_1 = comboBox.getSelectionModel();
    selectionModel_1.selectedIndexProperty().addListener( new SelectedIndex( comboBox ) );
    //
    ListView<String> listView = ListViewBuilder.<String>create()
      .items( FXCollections.observableArrayList("新規", "開く", "保存", "終了") )
      .id("listView").prefWidth(120).build();
    SelectionModel<String> selectionModel_2 = listView.getSelectionModel();
    selectionModel_2.selectedIndexProperty().addListener( new SelectedIndex( listView ) );
    //
    TreeItem<String> treeRoot = new TreeItem<String>("ファイル");
    treeRoot.setExpanded(true);
    treeRoot.getChildren().addAll(
      new TreeItem<String>("新規"),
      new TreeItem<String>("開く"),
      new TreeItem<String>("保存"),
      new TreeItem<String>("終了")
    );
    TreeView<String> treeView = new TreeView<String>(treeRoot);
    treeView.setId("treeView");
    treeView.setPrefWidth(120);
    SelectionModel<TreeItem<String>> selectionModel_3 = treeView.getSelectionModel();
    selectionModel_3.selectedIndexProperty().addListener( new SelectedIndex( treeView ) );
    //
    HBox root = HBoxBuilder.create().children(comboBox, listView, treeView).spacing(10).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(380).height(200)
      .scene( scene ).title("ComboBox02").build();
    stage.show();
  }
  //
  class SelectedIndex implements ChangeListener<Number> {
    Control control;
    SelectedIndex( Control control ){
      this.control = control;
    }
    //
    public void changed( ObservableValue<? extends Number> value, Number oldVal, Number newVal ) {
      ReadOnlyProperty prop = (ReadOnlyProperty)value;
      SelectionModel<String> model = (SelectionModel<String>)prop.getBean();
      Object item = model.getSelectedItem();
      String id = control.getId();
      System.out.println( id +" インデックス: "+ newVal +"/ 項目: "+ item );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
