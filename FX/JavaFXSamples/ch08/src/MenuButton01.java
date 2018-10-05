import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class MenuButton01 extends Application {

  public void start(Stage stage) throws Exception {
    //
    MenuItem save = MenuItemBuilder.create().text("保存").id("save").graphic(new ImageView("save.gif")).build();
    MenuItem exit = MenuItemBuilder.create().text("終了").id("exit").graphic(new ImageView("exit.gif")).build();
    MenuButton fileButton = new MenuButton(" ファイル", new ImageView("open.gif") );
    fileButton.getItems().addAll( save, exit );
    //
    MenuItem cut = MenuItemBuilder.create().text("切り取り").id("cut").build();
    MenuItem copy = MenuItemBuilder.create().text("コピー").id("copy").build();
    MenuButton editButton = new MenuButton( "編集" );
    editButton.getItems().addAll( cut, copy );
    
    save.setOnAction(actionHandler);
    exit.setOnAction(actionHandler);
    cut.setOnAction(actionHandler);
    copy.setOnAction(actionHandler);

    HBox root = HBoxBuilder.create().children( fileButton, editButton ).spacing(5).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("MenuButton01").build();
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
  //
  public static void main(String[] args) {
    launch(args);
  }
}
