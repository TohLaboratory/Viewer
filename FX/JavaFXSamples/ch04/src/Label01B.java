import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Label01B extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    //
    Label label_1 = LabelBuilder.create()
      .text("���x���ɃZ�b�g���镶����� �ʏ�͂P�s�ł��B")
      .prefWidth(240).prefHeight(20)
      .style( backgroundColor ).build(); 
    //
    Label label_2 = LabelBuilder.create()
      .text( "������������܂� ���̃x���́A\n���s�R�[�h�����邱�Ƃɂ���� \n"+
                     "���s����āA\n�����s�ŕ\������܂��B" )
      .style( backgroundColor ).build(); 
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1, label_2 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Label01B").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
