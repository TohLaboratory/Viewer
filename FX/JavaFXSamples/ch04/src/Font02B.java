import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Font02B extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    String backgroundColor = "-fx-background-color: rgb(180,180,180)";
    //
    Font font_1 = Font.font( "Arial", FontPosture.REGULAR, 32 );
    Label label_1 = LabelBuilder.create().text( "Arial regular" )
      .font( font_1 )
      .style(backgroundColor).build();
    //
    Font font_2 = Font.font( "Arial", FontWeight.BOLD, FontPosture.REGULAR, 32 );
    Label label_2 = LabelBuilder.create().text( "Arial regular" )
      .font( font_2 )
      .style(backgroundColor).build();
    //
    Font font_3 = Font.font( "Arial", FontPosture.ITALIC, 32 );
    Label label_3 = LabelBuilder.create().text( "Arial italic" )
      .font( font_3 )
      .style(backgroundColor).build();
    //
    Font font_4 = Font.font( "Arial", FontWeight.BOLD, FontPosture.ITALIC, 32 );
    Label label_4 = LabelBuilder.create().text( "Arial italic" )
      .font( font_4 )
      .style(backgroundColor).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3, label_4 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Font02B").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
