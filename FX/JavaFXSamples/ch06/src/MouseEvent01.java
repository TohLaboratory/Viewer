import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class MouseEvent01 extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {

    Label label = new Label( "���x��" );
    //
    Image img = new Image( getClass().getResourceAsStream("4-87783-185-1.png") );
    ImageView imgv = new ImageView( img );
    //
    Button button_1 = new Button( "�{�^��" );
    //
    Button button_2 = new Button( "�}�E�X�C�x���g�n���h���ݒ�σ{�^��" );
    button_2.addEventHandler( MouseEvent.ANY, mouseEventHandler );
    //
    VBox root = VBoxBuilder.create().spacing(10)
      .children( label, imgv, button_1, button_2 ).build();
    Scene scene = new Scene( root );
    // 
    root.addEventHandler( MouseEvent.ANY, mouseEventHandler );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("MouseEvent01")
      .x(300).y(200).width(300).height(320).build();
    stage.show();
  }

  EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e){
      Object source = e.getSource();
      EventTarget target = e.getTarget();
      //
      EventType<? extends Event> type = e.getEventType();
      if( type == MouseEvent.MOUSE_MOVED ){
        System.out.print("\n"+ source +" "+ target +" �}�E�X�ړ�  "+ msgPoint(e) );
      }
      if( type == MouseEvent.DRAG_DETECTED ){
        System.out.print( "\n"+ source +" "+ target +" �}�E�X�h���b�O���o "+ msgPoint(e) );
      }
      // �}�E�X�{�^���������Ȃ���̈ړ�
      if( type == MouseEvent.MOUSE_DRAGGED ){
        System.out.print("\n"+ source +" "+ target +" �}�E�X�h���b�O�ړ� "+ msgPoint(e) );
      }
      // �C�x���g�Ď��ΏۈȊO�̃R���g���[���փ}�E�X���]�������������m�o����
      if( type == MouseEvent.MOUSE_ENTERED_TARGET ){
        System.out.print( "\n"+ source +" "+ target +" �}�E�X������GUI�v�f�ɓ]�� " );
      }
      // �C�x���g�Ď��ΏۈȊO�̃R���g���[������}�E�X���]�o�����������m�o����
      if( type == MouseEvent.MOUSE_EXITED_TARGET ){
        System.out.print( "\n"+ source +" "+ target +" �}�E�X������GUI�v�f����]�o " );
      }
      if( type == MouseEvent.MOUSE_ENTERED ){
        System.out.print( "\n"+ source +" "+ target +" �}�E�X���]�� " );
      }
      if( type == MouseEvent.MOUSE_EXITED ){
        System.out.print( "\n"+ source +" "+ target +" �}�E�X���]�o " );
      }
      if( type == MouseEvent.MOUSE_PRESSED ){
        System.out.print( "\n"+ source +" "+ target +" "+ msgButton(e) +"�������ꂽ "+ msgKey(e) );
      }
      if( type == MouseEvent.MOUSE_RELEASED ){
        System.out.print( "\n"+ source +" "+ target +" "+ msgButton(e) +"�������ꂽ "+ msgKey(e) );
      }
      if( type == MouseEvent.MOUSE_CLICKED ){
        System.out.print( "\n"+ source +" "+ target +" "+ msgButton(e) +"���N���b�N���ꂽ "+ msgKey(e) );
      }
    }
  };
  //-----------------------------------------------------------
  String msgPoint( MouseEvent e ){
    double x = e.getX(); double y = e.getY();
    double x1 = e.getSceneX(); double y1 = e.getSceneY();
    double x2 = e.getScreenX(); double y2 = e.getScreenY();
    String msg = String.format("\n   ���΍��W(%f %f) �V�[�����W(%f %f) �X�N���[�����W(%f %f)", x,y,x1,y1,x2,y2 );
    return( msg ); 
  }
  //----------------------------------------
  String msgButton( MouseEvent e ){
    String msg = "";
    MouseButton btn = e.getButton();
    if( btn==MouseButton.PRIMARY ){
      msg += "���{�^�� ";
    }
    if( btn==MouseButton.SECONDARY ){
      msg += "�E�{�^�� ";
    }
    if( btn==MouseButton.MIDDLE ){
      msg += "���{�^�� ";
    }
    //
    int cnt = e.getClickCount();
    msg = msg +"( �N���b�N�� "+ cnt +" ) ";
    return( msg );
  }
  //----------------------------------------
  String msgKey( MouseEvent e ){
    String msg = "";
    if( e.isShiftDown() ){
      msg += "Shift�L�[ ";
    }
    if( e.isControlDown() ){
      msg += "Ctrl�L�[ ";
    }
    if( e.isAltDown() ){
      msg += "Alt�L�[ ";
    }
    if( e.isMetaDown() ){
      msg += "Meta�L�[ ";
    }
    if( e.isShortcutDown() ){
      msg += "Shortcut�L�[ ";
    }
    return(msg);
  }
  
  //----------------------------------------
  public static void main(String[] args) {
    launch(args);
  }

}
