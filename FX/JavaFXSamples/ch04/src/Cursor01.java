import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Cursor01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Label label_1 = LabelBuilder.create().text(" Cursor.CLOSED_HAND ")
      .cursor( Cursor.CLOSED_HAND ).build();

    Label label_2 = LabelBuilder.create().text(" Cursor.OPEN_HAND ")
      .cursor( Cursor.OPEN_HAND ).build();

    Label label_3 = LabelBuilder.create().text(" Cursor.CROSSHAIR ")
      .cursor( Cursor.CROSSHAIR ).build();

    Label label_4 = LabelBuilder.create().text(" Cursor.DEFAULT ")
      .cursor( Cursor.DEFAULT ).build();

    Label label_5 = LabelBuilder.create().text(" Cursor.WAIT ")
      .cursor( Cursor.WAIT ).build();

    Label label_6 = LabelBuilder.create().text(" Cursor.DISAPPEAR ")
      .cursor( Cursor.DISAPPEAR ).build();

    Label label_7 = LabelBuilder.create().text(" Cursor.H_RESIZE ")
      .cursor( Cursor.H_RESIZE ).build();

    Label label_8 = LabelBuilder.create().text(" Cursor.V_RESIZE ")
      .cursor( Cursor.V_RESIZE ).build();

    Label label_9 = LabelBuilder.create().text(" Cursor.N_RESIZE ")
      .cursor( Cursor.N_RESIZE ).build();

    Label label_10 = LabelBuilder.create().text(" Cursor.E_RESIZE ")
      .cursor( Cursor.E_RESIZE ).build();

    Label label_11 = LabelBuilder.create().text(" Cursor.W_RESIZE ")
      .cursor( Cursor.W_RESIZE ).build();

    Label label_12 = LabelBuilder.create().text(" Cursor.S_RESIZE ")
      .cursor( Cursor.S_RESIZE ).build();

    Label label_13 = LabelBuilder.create().text(" Cursor.NE_RESIZE ")
      .cursor( Cursor.NE_RESIZE ).build();

    Label label_14 = LabelBuilder.create().text(" Cursor.NW_RESIZE ")
      .cursor( Cursor.NW_RESIZE ).build();

    Label label_15 = LabelBuilder.create().text(" Cursor.SE_RESIZE ")
      .cursor( Cursor.SE_RESIZE ).build();

    Label label_16 = LabelBuilder.create().text(" Cursor.SW_RESIZE ")
      .cursor( Cursor.SW_RESIZE ).build();

    Label label_17 = LabelBuilder.create().text(" Cursor.NONE ")
      .cursor( Cursor.NONE ).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9,
                 label_10, label_11, label_12, label_13, label_14, label_15, label_16, label_17 )
      .build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(200)
      .scene( scene ).title("Cursor01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
