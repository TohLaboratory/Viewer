import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextField04A extends Application {

  public void start(Stage stage) throws Exception {

    MyTextField textField = new MyTextField();

    VBox root = VBoxBuilder.create().children(textField).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(120)
      .scene( scene ).title("TextField04A").build();
    stage.show();
  }
  //------------------------------------------------
  class MyTextField extends TextField {
    Pattern intPattern = Pattern.compile("[0-9]+"); 
    public void replaceText(int start, int end, String text) {
      Matcher match = intPattern.matcher(text);
      if( match.matches() || text.equals("") ){
        super.replaceText( start, end, text );
      }
    }
    public void replaceSelection(String text) {
      Matcher match = intPattern.matcher(text);
      if( match.matches() ){
        super.replaceSelection( text );
      }
    }
  }
  //-----------------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
