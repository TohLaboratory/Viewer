import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Label;

public class DragDropEvent02 extends Application {
  VBox root;

  public void start(Stage stage) throws Exception {

    root = new VBox();
    Scene scene = new Scene( root );
    stage = StageBuilder.create().scene(scene).title("DragDropEvent02")
            .x(200).y(100).width(480).height(800).build();

    root.setOnDragOver( dragOver );
    root.setOnDragDropped( dragDropped );

    stage.show();
  }
  //
  EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      e.acceptTransferModes( TransferMode.COPY );
    }
  };
  //-------------------------------------------------------------------
  EventHandler<DragEvent> dragDropped = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Object source = e.getSource();
      Dragboard db = e.getDragboard();
      Set<DataFormat> types = db.getContentTypes();
      Iterator<DataFormat> iter = types.iterator();
      while( iter.hasNext() ){
        DataFormat format = iter.next();
        //
        if( format == DataFormat.PLAIN_TEXT ){
          String text = db.getString();
          System.out.println( "DataFormat.TEXT "+ text );
          Label label = new Label( text );
          root.getChildren().add(label);
        }
        //
        if( format == DataFormat.IMAGE ){
          Image img = db.getImage();
          System.out.println( "DataFormat.IMAGE "+ img );
          ImageView imgv = new ImageView(img);
          root.getChildren().add(imgv);
        }
        //
        if( format == DataFormat.URL ){
          String url = db.getUrl();
          System.out.println( "DataFormat.URL "+ url );
          WebView webView = new WebView();
          WebEngine we = webView.getEngine();
          we.load( url );
          root.getChildren().add(webView);
        }
        //
        if( format == DataFormat.HTML ){
          String html = db.getHtml();
          System.out.println( "DataFormat.HTML "+ html );
          WebView webView = new WebView();
          WebEngine we = webView.getEngine();
          we.loadContent( html );
          root.getChildren().add(webView);
        }
        //
        if( format == DataFormat.FILES ){
          System.out.print( "DataFormat.FILES  " );
          List<File> files = db.getFiles();
          for( int i=0 ; i< files.size() ; i++ ){
            System.out.println( files.get(i) );
          }
        }
        //
        if( format == DataFormat.RTF ){
          String rtf = db.getRtf();
          System.out.println( "DataFormat.RTF "+ rtf );
        }
      }
      //
      e.setDropCompleted(true);
    }
  };
  //----------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
