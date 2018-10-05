import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class Menu01A extends Application {
 
  public void start(Stage stage) throws Exception {

    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("ファイル");
    
    MenuItem open = MenuItemBuilder.create().text("開く").id("open")
      .graphic(new ImageView("open.gif")).build();
    
    MenuItem save = MenuItemBuilder.create().text("保存").id("save")
      .graphic(new ImageView("save.gif")).build();
    
    MenuItem separator = new SeparatorMenuItem();
    
    MenuItem exit = MenuItemBuilder.create().text("終了").id("exit")
      .graphic(new ImageView("exit.gif")).build();

    menuBar.getMenus().addAll( fileMenu );
    fileMenu.getItems().addAll( open, save, separator, exit );

    fileMenu.addEventHandler( ActionEvent.ACTION, actionHandler );

    BorderPane root = BorderPaneBuilder.create().top( menuBar ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(240)
      .scene( scene ).title("Menu01A").build();
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
