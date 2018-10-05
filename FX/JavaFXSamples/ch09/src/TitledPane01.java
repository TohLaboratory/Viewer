import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class TitledPane01 extends Application {

  public void start(Stage stage) throws Exception {
    // 
    ImageView imgv_1 = new ImageView( "open.gif" );
    ImageView imgv_2 = new ImageView( "save.gif" );
    ImageView imgv_3 = new ImageView( "exit.gif" );

    Label label_1 = LabelBuilder.create().graphic( imgv_1 ).build();
    Label label_2 = LabelBuilder.create().graphic( imgv_2 ).build();
    Label label_3 = LabelBuilder.create().graphic( imgv_3 ).build();
    //
    TitledPane titledPane_1 = new TitledPane( "タイトルドペイン 1", label_1 );
    //
    VBox group = VBoxBuilder.create()
      .children( label_2, label_3 ).build();
    TitledPane titledPane_2 = new TitledPane( "タイトルドペイン 2", group );
    //
    VBox root = VBoxBuilder.create().spacing(10).prefWidth(240).prefHeight(240)
      .children( titledPane_1, titledPane_2 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("TitledPane01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
