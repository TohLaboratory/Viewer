import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Font02A extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {
    String backgroundColor = "-fx-background-color: rgb(180,180,180)";
    //
    Font font = new Font( 32 );
    Label label = LabelBuilder.create().text( "���x�� 0 \n �t�H���g�� �w��Ȃ��Ȃ�" )
      .font( font )
      .style(backgroundColor).build();
    //
    Font font1 = new Font( "�t�H���g�� �s�K��", 32 );
    Label label_1 = LabelBuilder.create().text( "���x�� 1 \n �t�H���g�� �s�K�؂Ȃ�" )
      .font( font1 )
      .style(backgroundColor).build();
    //
    Font font2 = new Font( "MS Mincho", 32 );
    Label label_2 = LabelBuilder.create().text( "���x�� 2 \n �t�H���g �l�r ����" )
      .font( font2 )
      .style(backgroundColor).build();
    //
    Font font3 = new Font( "MS PMincho", 32 );
    Label label_3 = LabelBuilder.create().text( "���x�� 3 \n �t�H���g �l�r �o����" )
      .font( font3 )
      .style(backgroundColor).build();
    //
    Font font4 = new Font( "MS Gothic", 32 );
    Label label_4 = LabelBuilder.create().text( "���x�� 4 \n �t�H���g �l�r �S�V�b�N" )
      .font( font4 )
      .style(backgroundColor).build();
    //
    Font font5 = new Font( "MS PGothic", 32 );
    Label label_5 = LabelBuilder.create().text( "���x�� 5 \n �t�H���g �l�r �o�S�V�b�N" )
      .font( font5 )
      .style(backgroundColor).build();
    //
    Font font6 = new Font( "MS UI Gothic", 32 );
    Label label_6 = LabelBuilder.create().text( "���x�� 6 \n �t�H���g MS UI Gothic" )
      .font( font6 )
      .style(backgroundColor).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label, label_1, label_2, label_3, label_4, label_5, label_6 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("Font02A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

