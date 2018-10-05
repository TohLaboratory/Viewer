import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class Menu02 extends Application {
 
  public void start(Stage stage) throws Exception {

    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("ファイル");
    
    MenuItem open = MenuItemBuilder.create().text("開く _O").id("open").build();
    MenuItem save = MenuItemBuilder.create().text("保存 _S").id("save").build();
    MenuItem separator = new SeparatorMenuItem();
    MenuItem exit = MenuItemBuilder.create().text("終了 _X").id("exit").build();

    menuBar.getMenus().addAll( fileMenu );
    fileMenu.getItems().addAll( open, save, separator, exit );

    fileMenu.addEventHandler( ActionEvent.ACTION, actionHandler );

    BorderPane root = BorderPaneBuilder.create().top( menuBar ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("Menu02").build();
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
