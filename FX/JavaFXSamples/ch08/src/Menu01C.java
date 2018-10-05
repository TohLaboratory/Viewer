import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class Menu01C extends Application {
  // RadioMenuItem
  public void start(Stage stage) throws Exception {
    //
    MenuBar menuBar = new MenuBar();
    Menu editMenu = new Menu("編集");

    RadioMenuItem cut = RadioMenuItemBuilder.create().text("切り取り").id("cut").build();
    RadioMenuItem copy = RadioMenuItemBuilder.create().text("コピー").id("copy").build();

    ToggleGroup group = new ToggleGroup();
    cut.setToggleGroup( group );
    copy.setToggleGroup( group );
    
    editMenu.getItems().addAll( cut, copy );
    editMenu.addEventHandler(ActionEvent.ACTION, actionHandler);

    menuBar.getMenus().addAll( editMenu );

    BorderPane root = BorderPaneBuilder.create().top( menuBar ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("Menu01C").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      RadioMenuItem item = (RadioMenuItem)e.getTarget();
      System.out.print( item.getId() );
      boolean selected = item.isSelected();
      if( selected ) System.out.println( " 選択されました" );
      else           System.out.println( " 解除されました" );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
