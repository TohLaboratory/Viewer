import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class HtmlLabel01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    String htmlText = 
      "<html>" +
      "<br><font size=6>�t�H���g�T�C�Y��</font><font size=2>�ς�����</font>" +
      "<br><br>" +
      "<i>�Α�</i>��<u>�����t��</u>��<b>����</b>��<s>�������t��</s>�ɂ�����" +
      "<br>" +
      "<p align=center>�Z���^�����O</p>" +
      "<p align=right>�E�񂹂��n�j</p>" +
      "<br><br>" +
      "�W���ɑ΂���<sup>��t��</sup><sub>���t��</sub>���ł��܂��B" ;
    WebView webView = new WebView();
    WebEngine we = webView.getEngine();
    we.loadContent( htmlText );
    //
    Label label_1 = LabelBuilder.create().graphic( webView )
      .prefWidth(320).prefHeight(240).build();
    //
    VBox root = VBoxBuilder.create().spacing( 10 )
      .children( label_1 ).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("HtmlLabel01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
