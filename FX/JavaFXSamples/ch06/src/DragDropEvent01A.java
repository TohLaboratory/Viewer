import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DragDropEvent01A extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";

  public void start(Stage stage) throws Exception {

    Label label_1 = new Label( "ドラッグする" );
    label_1.setFont( new Font( 32 ) );
    Label label_2 = new Label( "ドロップ目標" );
    label_2.setFont( new Font( 32 ) );
    //
    label_1.setOnDragDetected( dragDetected );
    label_2.setOnDragEntered( dragEntered );
    label_2.setOnDragOver( dragOver );
    label_2.setOnDragExited( dragExited );
    label_2.setOnDragDropped( dragDropped );
    label_1.setOnDragDone( dragDone );
    //
    VBox root = VBoxBuilder.create().children(label_1, label_2).spacing(10).build();
    Scene scene = new Scene( root );
    stage = StageBuilder.create().scene(scene).title("DragDropEvent01B")
            .x(300).y(200).width(300).height(320).build();
    stage.show();
  }
  //
  EventHandler<MouseEvent> dragDetected = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e){
      Label source = (Label)e.getSource(); 
      Dragboard db = source.startDragAndDrop( TransferMode.COPY );
      String dataToDrop = source.getText();
      ClipboardContent clipb = new ClipboardContent();
      clipb.putString( dataToDrop );
      db.setContent( clipb );
      System.out.println("DragDetected");
    }
  };
  //
  EventHandler<DragEvent> dragEntered = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Label dragTarget = (Label)e.getTarget();
      dragTarget.setTextFill( Color.RED );
      System.out.println("DragEntered");
    }
  };
  //
  EventHandler<DragEvent> dragExited = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Label dragTarget = (Label)e.getTarget();
      dragTarget.setTextFill( Color.BLACK );
      System.out.println("DragExited");
    }
  };
  //
  EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      e.acceptTransferModes( TransferMode.COPY );
      System.out.println("DragOver");
    }
  };
  //
  EventHandler<DragEvent> dragDropped = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      Label dragTarget = (Label)e.getSource();
      Dragboard db = e.getDragboard();
      if( db.hasString() ){
        String data = db.getString();
        dragTarget.setText( data );
        e.setDropCompleted(true);
      }
      System.out.println("DragDropped");
    }
  };
  //
  EventHandler<DragEvent> dragDone = new EventHandler<DragEvent>() {
    public void handle(DragEvent e){
      //e.consume();
      System.out.println("DragDone");
    }
  };
  //---------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
