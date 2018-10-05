import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.WindowEvent;

public class WindowEvent02 extends Application {

  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create().width(320).height(240)
      .scene( scene ).title("WindowEvent02").build();
    //
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent e){
        int ans = JOptionPane.showConfirmDialog(null, "本当に終了しますか", null, JOptionPane.YES_NO_OPTION);
        if( ans == JOptionPane.NO_OPTION){
          e.consume(); // これを実行するとウインドウは閉じない。
        }
      }
    });
    //
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
