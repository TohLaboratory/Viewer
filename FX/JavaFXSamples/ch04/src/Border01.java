import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Border01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    Label label_1 = LabelBuilder.create().text("���F �̃{�[�_�[")
      .style( "-fx-border-color:black" )
      .prefWidth(240).prefHeight(80).build();

    Label label_2 = LabelBuilder.create().text("��10 �{�[�_�[")
      .style( "-fx-border-width:10 ; -fx-border-color:black" )
      .prefWidth(240).prefHeight(80).build();

    Label label_3 = LabelBuilder.create().text("������ 20�k�߂�")
      .style( "-fx-border-insets:20 ; -fx-border-width:5 ; -fx-border-color:black" )
      .prefWidth(240).prefHeight(80).build();

    Label label_4 = LabelBuilder.create().text("�R�[�i�[�~�ʔ��a20")
      .style( "-fx-border-radius:20 ; -fx-border-width:5 ; -fx-border-color:black" )
      .prefWidth(240).prefHeight(80).build();

    Label label_5 = LabelBuilder.create().text("�_�b�V��")
      .style( "-fx-border-style:dashed ; -fx-border-width:5 ; -fx-border-color:black" )
      .prefWidth(240).prefHeight(80).build();

    Label label_6 = LabelBuilder.create().text("�h�b�g")
      .style( "-fx-border-style:dotted ; -fx-border-width:5 ; -fx-border-color:black" )
      .prefWidth(240).prefHeight(80).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2, label_3, label_4, label_5, label_6 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Border01A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
