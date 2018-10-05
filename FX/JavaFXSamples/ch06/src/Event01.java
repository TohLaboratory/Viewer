import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Event01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Label label = new Label("���x��1");
    Button button = new Button("�{�^��");
    ComboBox<String> combo = new ComboBox<String>();
    combo.getItems().addAll( "1", "2", "3" );
    combo.setEditable(true);
    ListView<String> listView = new ListView<String>();
    listView.getItems().addAll( "1", "2", "3" );
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label, button, combo, listView ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(240)
      .scene( scene ).title("Event01").build();
    //
    stage.addEventHandler( Event.ANY, eventHandler );
    //scene.addEventHandler( Event.ANY, eventHandler );
    //root.addEventHandler( Event.ANY, eventHandler );
    //
    stage.show();
  }
  //
  EventHandler<Event> eventHandler = new EventHandler<Event>() {
    public void handle(Event e){
      EventType<? extends Event> type = e.getEventType();
      Object source = e.getSource();      // �C�x���g�n���h�����ݒ肳��Ă���I�u�W�F�N�g
      EventTarget target = e.getTarget(); // �C�x���g�����������I�u�W�F�N�g
      System.out.println( "�C�x���g�^�C�v:"+ type.getName() +
    		  " || �C�x���g�^�[�Q�b�g:"+ target  +" || �C�x���g�\�[�X:"+ source );
    }
  };

  public static void main(String[] args) {
    launch(args);
  }
}
