import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField04 extends Application {

  public void start(Stage stage) throws Exception {

    MyTextField textField = new MyTextField();

    VBox root = VBoxBuilder.create().children(textField).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField04").build();
    stage.show();
  }
  //------------------------------------------------
  class MyTextField extends TextField {
    //
    // フィールドへのキー入力の場合は replaceText()が呼び出される
    public void replaceText(int start, int end, String text) {
      System.out.println( "*replaceText() start:"+ start +" end:"+ end +" "+ text );
      super.replaceText( start, end, text );
    }
    //
    // クリップボードからの貼り付けの場合は、replaceSelection()が呼び出される
    public void replaceSelection(String text) {
      System.out.println( "*replaceSelection() "+ text );
      super.replaceSelection( text );
    }
  }
  //-----------------------------------------------
  //
  public static void main(String[] args) {
    launch(args);
  }
}
