import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class PopupMenu01 extends Application {
 
  public void start(Stage stage) throws Exception {

    ContextMenu popupMenu_1 = new ContextMenu();
    MenuItem open = MenuItemBuilder.create().graphic(new ImageView("open.gif")).id("open").build();
    MenuItem save = MenuItemBuilder.create().graphic(new ImageView("save.gif")).build();
    MenuItem exit = MenuItemBuilder.create().graphic(new ImageView("exit.gif")).id("exit").build();
    popupMenu_1.getItems().addAll( open, save, exit );
    popupMenu_1.addEventHandler( ActionEvent.ACTION, actionHandler );

    ContextMenu popupMenu_2 = new ContextMenu();
    MenuItem cut = MenuItemBuilder.create().text("切り取り").id("cut").build();
    MenuItem copy = MenuItemBuilder.create().text("コピー").id("copy").build();
    MenuItem paste = MenuItemBuilder.create().text("貼り付け").id("paste").build();
    popupMenu_2.getItems().addAll( cut, copy, paste );
    popupMenu_2.addEventHandler( ActionEvent.ACTION, actionHandler );

    Label label_1 = LabelBuilder.create().graphic(new ImageView("stream.jpg")).build();
    Label label_2 = LabelBuilder.create().graphic(new ImageView("500kei01.jpg")).build();

    label_1.setContextMenu( popupMenu_1 );
    label_2.setContextMenu( popupMenu_2 );

    HBox root = HBoxBuilder.create().children(label_1, label_2).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("PopupMenu01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      MenuItem menuItem = (MenuItem)e.getTarget();
      String id = menuItem.getId();
      System.out.println( id );
    }
  };
  
  public static void main(String[] args) {
    launch(args);
  }
}
