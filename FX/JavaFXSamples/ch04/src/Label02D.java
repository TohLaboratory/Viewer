import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label02D extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    ImageView imgv1 = new ImageView( "exit.gif" );
    ImageView imgv2 = new ImageView( "exit.gif" );
    ImageView imgv3 = new ImageView( "exit.gif" );
    // 
    Label label_1 = LabelBuilder.create().text("ПoМы").graphic(imgv1)
      .style( backgroundColor ).build();
    double gap = label_1.getGraphicTextGap();
    System.out.println( "GraphicTextGap="+ gap );

    Label label_2 = LabelBuilder.create().text("ПoМы").graphic(imgv2)
      .graphicTextGap( 40 )
      .style(backgroundColor).build();

    Label label_3 = LabelBuilder.create().text("ПoМы").graphic(imgv3)
      .graphicTextGap( 40 )
      .contentDisplay( ContentDisplay.TOP )
      .style( backgroundColor ).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(200)
      .scene( scene ).title("Label02D").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
