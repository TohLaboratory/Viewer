import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class Menu01 extends Application {
  String backgroundColor = "-fx-background-color: rgb(180,180,180)";
 
  public void start(Stage stage) throws Exception {

    MenuBar menuBar = new MenuBar();
    Menu editMenu = new Menu("�ҏW");
    MenuItem cut = MenuItemBuilder.create().text("�؂���").id("cut").build();
    MenuItem copy = MenuItemBuilder.create().text("�R�s�[").id("copy").build();
    MenuItem paste = MenuItemBuilder.create().text("�\��t��").id("paste").build();
    Menu search = new Menu("����");
    MenuItem searchNext = MenuItemBuilder.create().text("��������").id("next").build();
    MenuItem searchPrev = MenuItemBuilder.create().text("�O������").id("prev").build();

    menuBar.getMenus().addAll( editMenu );
    editMenu.getItems().addAll( cut, copy, paste, search );
    search.getItems().addAll( searchNext, searchPrev );

    editMenu.addEventHandler( ActionEvent.ACTION, actionHandler );
    search.addEventHandler( ActionEvent.ACTION, actionHandler );

    BorderPane root = BorderPaneBuilder.create().top( menuBar ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(200)
      .scene( scene ).title("Menu01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      MenuItem menuItem = (MenuItem)e.getTarget();
      String id = menuItem.getId();
      Menu parent = menuItem.getParentMenu();
      String parentText = parent.getText();
      System.out.println( "���j���[���ڂ�ID: "+ id +"  �e���j���[�̃e�L�X�g: "+ parentText );
    }
  };
  
  public static void main(String[] args) {
    launch(args);
  }
}
