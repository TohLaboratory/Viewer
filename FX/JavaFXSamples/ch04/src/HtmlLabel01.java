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
      "<br><font size=6>フォントサイズを</font><font size=2>変えたり</font>" +
      "<br><br>" +
      "<i>斜体</i>や<u>下線付き</u>や<b>太字</b>や<s>抹消線付き</s>にしたり" +
      "<br>" +
      "<p align=center>センタリング</p>" +
      "<p align=right>右寄せもＯＫ</p>" +
      "<br><br>" +
      "標準に対して<sup>上付き</sup><sub>下付き</sub>もできます。" ;
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
