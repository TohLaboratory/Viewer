import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class PopupMenu02 extends Application {
 
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

    ImageView imgv_1 = new ImageView("stream.jpg");
    ImageView imgv_2 = new ImageView("500kei01.jpg");

    imgv_1.setOnContextMenuRequested( new ContextMenuEventHandler( popupMenu_1 ) );
    imgv_2.setOnContextMenuRequested( new ContextMenuEventHandler( popupMenu_2 ) );

    HBox root = HBoxBuilder.create().children(imgv_1, imgv_2).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create()
      .scene( scene ).title("PopupMenu02").build();
    stage.show();
  }
  //
  class ContextMenuEventHandler implements EventHandler<ContextMenuEvent> {
    ContextMenu popupMenu;
    public ContextMenuEventHandler( ContextMenu popupMenu ){
      this.popupMenu = popupMenu;
    }
    public void handle( ContextMenuEvent e ) {
      Node base = (Node)e.getTarget();
      double x = e.getX();
      double y = e.getY();
      popupMenu.show( base, Side.LEFT, x, y );
    }
  };
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
