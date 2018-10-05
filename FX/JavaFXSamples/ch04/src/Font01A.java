import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class Font01A extends Application {
 
  public void start(Stage stage) throws Exception {
    // ƒtƒHƒ“ƒg–¼ˆê——‚ðŽæ“¾
    List<String> fontFamilies = Font.getFamilies();
    //
    ListView<String> nameList = new ListView<String>();
    nameList.getItems().addAll(fontFamilies);
    BorderPane root = BorderPaneBuilder.create().center(nameList).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Font01A").build();
    stage.show();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
