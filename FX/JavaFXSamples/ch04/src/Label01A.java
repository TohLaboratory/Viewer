import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label01A extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
	  //
    Label label_1 = new Label("ラベル");
    label_1.setPrefSize( 120, 20 );
    label_1.setStyle( backgroundColor ); 
    //
    Label label_2 = LabelBuilder.create()
      .text( "LabelBuilder" )
      .prefWidth(240).prefHeight(20)
      .style( backgroundColor )
      .build(); 
    //
    Label label_3 = LabelBuilder.create()
      .text("ラベルにセットする文字列に下線を付けられます。")
      .prefWidth(240).prefHeight(20)
      .underline( true )
      .style( backgroundColor ).build(); 
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label01A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
