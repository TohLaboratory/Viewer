import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField04B extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField = new LimitTextField( 10 ); // ���͐��������� 10
    //
    VBox root = VBoxBuilder.create().children(textField).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField04B").build();
    stage.show();
  }
  //------------------------------------------------
  class LimitTextField extends TextField {
    int limitCount; // ���͐������������L�^
    LimitTextField( int limitCount ){
      this.limitCount = limitCount;
    }
    public void replaceText( int start, int end, String currentText ) {
      String text = getText(); // ���ɓ��͍ς̕�������擾
      int totalLength = text.length() + currentText.length();
      if( totalLength <= limitCount ){
        // ����̓��͂��܂�ł� �����������ɒB���Ȃ��Ȃ�A����̓��͂𖳏����Ŏ󂯓����
        super.replaceText( start, end, currentText );
      }
    }
    public void replaceSelection( String currentText ) {
      String text = getText(); // ���ɓ��͍ς̕�������擾
      int totalLength = text.length() + currentText.length();
      if( totalLength <= limitCount ){
        // ����̓��͂��܂�ł� �����������ɒB���Ȃ��Ȃ�A����̓��͂𖳏����Ŏ󂯓����
        super.replaceSelection( currentText );
      }
    }
  }
  //-----------------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
