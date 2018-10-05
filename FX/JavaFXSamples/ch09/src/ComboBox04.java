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
      .items( FXCollections.observableArrayList("�V�K", "�J��", "�ۑ�", "�I��") )
      .editable(true)
      .id("comboBox_1").build();
    //
    ComboBox<String> comboBox_2 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("�؂���", "�R�s�[", "�\��t��", "�폜") )
      .id("comboBox_2").build();
    //
    ComboBox<String> comboBox_3 = ComboBoxBuilder.<String>create()
      .items( FXCollections.observableArrayList("����", "��������", "�O������", "�u��") )
      .id("comboBox_3").build();
    //
    comboBox_1.valueProperty().addListener( selectedValue );
    comboBox_2.valueProperty().addListener( selectedValue );
    comboBox_3.valueProperty().addListener( selectedValue );
    comboBox_1.setOnAction( actionComboBox );
    comboBox_2.setOnAction( actionComboBox );
    comboBox_3.setOnAction( actionComboBox );
    //
    comboBox_1.setValue( "�t�@�C��" );               // ���ږ����w�肷��
    comboBox_2.getSelectionModel().select("�R�s�["); // ���ږ��őI������
    comboBox_3.getSelectionModel().select(3);        // �C���f�b�N�X�ԍ��őI������
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
      System.out.println( "�A�N�V�����C�x���g "+ id +" "+ value );
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
