import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField04B extends Application {

  public void start(Stage stage) throws Exception {

    TextField textField = new LimitTextField( 10 ); // 入力制限文字数 10
    //
    VBox root = VBoxBuilder.create().children(textField).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField04B").build();
    stage.show();
  }
  //------------------------------------------------
  class LimitTextField extends TextField {
    int limitCount; // 入力制限文字数を記録
    LimitTextField( int limitCount ){
      this.limitCount = limitCount;
    }
    public void replaceText( int start, int end, String currentText ) {
      String text = getText(); // 既に入力済の文字列を取得
      int totalLength = text.length() + currentText.length();
      if( totalLength <= limitCount ){
        // 今回の入力を含んでも 制限文字数に達しないなら、今回の入力を無条件で受け入れる
        super.replaceText( start, end, currentText );
      }
    }
    public void replaceSelection( String currentText ) {
      String text = getText(); // 既に入力済の文字列を取得
      int totalLength = text.length() + currentText.length();
      if( totalLength <= limitCount ){
        // 今回の入力を含んでも 制限文字数に達しないなら、今回の入力を無条件で受け入れる
        super.replaceSelection( currentText );
      }
    }
  }
  //-----------------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
