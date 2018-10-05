import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Font01 extends Application {
 
  public void start(Stage stage) throws Exception {
    //
    // デフォルトフォントを取得
    Font defaultFont = Font.getDefault();
    String fontName = defaultFont.getName();
    double size     = defaultFont.getSize();
    System.out.println( fontName +" "+ size );
    System.exit(0);
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
