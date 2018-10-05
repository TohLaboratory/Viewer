import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioButtonBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleGroupBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class RadioButton01 extends Application {

  public void start(Stage stage) throws Exception {

    // �f�t�H���g�ł́A�g�O���O���[�v�̒��� �ǂ̃��W�I�{�^�����I����Ԃɂ͂Ȃ�Ȃ�
    RadioButton rb1 = new RadioButton("�J��");
      rb1.setId("open");
    RadioButton rb2 = RadioButtonBuilder.create().text("�ۑ�").id("save")
      .selected(true)
      .build();
    RadioButton rb3 = RadioButtonBuilder.create().text("�I��").id("exit").build();
    //
    ToggleGroup group = ToggleGroupBuilder.create()
      .toggles( rb1, rb2, rb3 ).build();

    VBox root = VBoxBuilder.create().spacing(10)
      .children( rb1, rb2, rb3 ).build();
    Scene scene = new Scene(root);
    scene.addEventHandler( ActionEvent.ACTION, actionHandler );
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("RadioButton01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      RadioButton src = (RadioButton)e.getTarget();
      String id = src.getId();
      System.out.println( id +" �I������܂���" );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
