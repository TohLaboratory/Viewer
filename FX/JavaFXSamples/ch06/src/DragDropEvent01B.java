import org.pnuts.xml.newDocument;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DragDropEvent01B extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {

    ImageView imgv = new ImageView( "4-87783-185-1.png" );

    Label label_2 = new Label( "ドロップ目標" );
    label_2.setFont( new Font( 32 ) );
    label_2.setPrefSize( 300, 240 );
    label_2.setStyle(backgroundColor);
    label_2.setId("target");
    //
    imgv.setOnDragDetected( dragDetected );
    label_2.setOnDragEntered( dragEntered );
    label_2.setOnDragOver( dragOver );
    label_2.setOnDragExited( dragExited );
    label_2.setOnDragDropped( dragDropped );
    imgv.setOnDragDone( dragDone );
    //
    VBox root = VBoxBuilder.create().children(imgv, label_2).spacing(10).build();
    Scene scene = new Scene( root );
    stage = StageBuilder.create().scene(scene).title("DragDropEvent01B")
            .x(300).y(200).width(300).height(400).build();
    stage.show();
  }
  //
  EventHandler<MouseEvent> dragDetected = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e){
      ImageView source = (ImageView)e.getSource();
      Dragboard db = source.startDragAndDrop( TransferMode.COPY );
      Image dataToDrop = source.getImage();
      ClipboardContent clipb = new ClipboardContent();
      clipb.putImage( dataToDrop );
      db.setContent( clipb );
    }
  };
  //
  EventHandler<DragEvent> dragEntered = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Label dragTarget = (Label)e.getTarget();
      dragTarget.setTextFill( Color.RED );
    }
  };
  //
  EventHandler<DragEvent> dragExited = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Label dragTarget = (Label)e.getTarget();
      dragTarget.setTextFill( Color.BLACK );
    }
  };
  //
  EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      e.acceptTransferModes( TransferMode.COPY );
    }
  };
  //
  EventHandler<DragEvent> dragDropped = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Label dragTarget = (Label)e.getSource();
      Dragboard db = e.getDragboard();
      if( db.hasImage() ){
        Image data = db.getImage();
        ImageView imgv = new ImageView(data); 
        dragTarget.setGraphic( imgv );
        e.setDropCompleted(true);
      }
    }
  };
  //
  EventHandler<DragEvent> dragDone = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
    }
  };
  //----------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
