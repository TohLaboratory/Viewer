import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

public class TextField01 extends Application {

  public void start(Stage stage) throws Exception {

    TextField tf_1 = new TextField();
    TextField tf_2 = new TextField("�e�L�X�g�̏����l");
    TextField tf_3 = TextFieldBuilder.create().text("�e�L�X�g�̏����l").build();
    TextField tf_4 = TextFieldBuilder.create().promptText("�v�����v�g�e�L�X�g").build();
    TextField tf_5 = TextFieldBuilder.create().alignment(Pos.TOP_LEFT).build();
    TextField tf_6 = TextFieldBuilder.create().alignment(Pos.TOP_CENTER).build();
    TextField tf_7 = TextFieldBuilder.create().alignment(Pos.TOP_RIGHT).build();

    VBox root = VBoxBuilder.create().
      children(tf_1, tf_2, tf_3, tf_4, tf_5, tf_6, tf_7).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240)
      .scene( scene ).title("TextField01").build();
    stage.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
