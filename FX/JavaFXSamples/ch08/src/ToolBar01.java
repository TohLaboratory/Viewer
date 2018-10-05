import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class ToolBar01 extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";
 
  public void start(Stage stage) throws Exception {

    ToolBar tool_1 = new ToolBar(
      ButtonBuilder.create().id("open").graphic(new ImageView("open.gif")).build(),
      ButtonBuilder.create().id("save").graphic(new ImageView("save.gif")).build(),
      ButtonBuilder.create().id("exit").graphic(new ImageView("exit.gif")).build()
    );
    //
    ToolBar tool_2 = new ToolBar(
      ButtonBuilder.create().id("cut").text("切り取り").build(),
      ButtonBuilder.create().id("copy").text("コピー").build(),
      ButtonBuilder.create().id("paste").text("貼り付け").build()
    );
    tool_2.setOrientation(Orientation.VERTICAL);

    BorderPane root = BorderPaneBuilder.create().top(tool_1).left(tool_2).build();
    Scene scene = new Scene(root);
    scene.addEventFilter( ActionEvent.ACTION, actionHandler );
    //
    stage = StageBuilder.create().width(280).height(300)
      .scene( scene ).title("ToolBar01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      Button bt = (Button)e.getTarget();
      String id = bt.getId();
      System.out.println( id );
    }
  };
  
  public static void main(String[] args) {
    launch(args);
  }
}
