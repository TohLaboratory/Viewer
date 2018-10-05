import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class Menu02A extends Application {
 
  public void start(Stage stage) throws Exception {

    MenuBar menuBar = new MenuBar();
    Menu editMenu = new Menu("�ҏW");

    MenuItem open = MenuItemBuilder.create().text( "�J��" ).id("open")
      .accelerator( KeyCombination.keyCombination("Alt+O") ).build();

    MenuItem cut = MenuItemBuilder.create().text( "�؂���" ).id("cut")
      .accelerator( KeyCombination.keyCombination("Ctrl+X") ).build();

    MenuItem copy = MenuItemBuilder.create().text( "�R�s�[" ).id("copy")
      .accelerator( KeyCombination.keyCombination("Ctrl+C") ).build();

    MenuItem paste = MenuItemBuilder.create().text( "�\��t��" ).id("paste")
      .accelerator(KeyCombination.keyCombination("Ctrl+V")).build();

    MenuItem delete = MenuItemBuilder.create().text( "�폜" ).id("del")
      .accelerator( KeyCombination.keyCombination("Delete") ).build();

    MenuItem insert = MenuItemBuilder.create().text( "�}��" ).id("ins")
      .accelerator( KeyCombination.keyCombination("Insert") ).build();

    MenuItem space = MenuItemBuilder.create().text( "��" ).id("space")
      .accelerator( KeyCombination.keyCombination("Space") ).build();

    MenuItem next = MenuItemBuilder.create().text( "��������" ).id("next")
      .accelerator( KeyCombination.keyCombination("F3") ).build();

    MenuItem prev = MenuItemBuilder.create().text( "�O������" ).id("prev")
      .accelerator( KeyCombination.keyCombination("Shift+F3") ).build();

    MenuItem undo = MenuItemBuilder.create().text( "���ɖ߂�" ).id("undo")
      .accelerator( KeyCombination.keyCombination("BackSpace") ).build();

    MenuItem redo = MenuItemBuilder.create().text( "��蒼��" ).id("redo")
      .accelerator( KeyCombination.keyCombination("Shift+BackSpace") ).build();

    MenuItem ok = MenuItemBuilder.create().text( "����" ).id("ok")
      .accelerator( KeyCombination.keyCombination("Enter") ).build();

    MenuItem exit = MenuItemBuilder.create().text( "�I��" ).id("exit")
      .accelerator( KeyCombination.keyCombination("Ctrl+Alt+X") ).build();

    MenuItem cancel = MenuItemBuilder.create().text( "���" ).id("cancel")
      .accelerator( KeyCombination.keyCombination("Esc") ).build();

    MenuItem home = MenuItemBuilder.create().text( "�擪��" ).id("top")
      .accelerator( KeyCombination.keyCombination("Home") ).build();

    MenuItem tab  = MenuItemBuilder.create().text( "�i��" ).id("tab")
      .accelerator( KeyCombination.keyCombination("Tab") ).build();

    MenuItem end = MenuItemBuilder.create().text( "������" ).id("end")
      .accelerator( KeyCombination.keyCombination("End") ).build();

    MenuItem pageUp = MenuItemBuilder.create().text( "1�y�[�W���" ).id("pageUp")
      .accelerator( KeyCombination.keyCombination("Page Up") ).build();
    
    MenuItem pageDown = MenuItemBuilder.create().text( "1�y�[�W����" ).id("pageDown")
      .accelerator( KeyCombination.keyCombination("Page Down") ).build();

    MenuItem up = MenuItemBuilder.create().text( "���" ).id("up")
      .accelerator( KeyCombination.keyCombination("Up") ).build();

    MenuItem down = MenuItemBuilder.create().text( "����" ).id("down")
      .accelerator( KeyCombination.keyCombination("Down") ).build();

    MenuItem left = MenuItemBuilder.create().text( "����" ).id("left")
      .accelerator( KeyCombination.keyCombination("Left") ).build();

    MenuItem right = MenuItemBuilder.create().text( "�E��" ).id("right")
      .accelerator( KeyCombination.keyCombination("Right") ).build();

    MenuItem sep1 = new SeparatorMenuItem();
    MenuItem sep2 = new SeparatorMenuItem();
    MenuItem sep3 = new SeparatorMenuItem();
    MenuItem sep4 = new SeparatorMenuItem();

    menuBar.getMenus().addAll( editMenu );
    editMenu.getItems().addAll( open, sep1, cut, copy, paste, delete, insert, space, sep2, next, prev, undo, redo,
                                sep3, ok, exit, cancel, sep4, home, tab, end, pageUp, pageDown, up, down, left, right );

    editMenu.addEventHandler( ActionEvent.ACTION, actionHandler );

    BorderPane root = BorderPaneBuilder.create().top( menuBar ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(240).height(600)
      .scene( scene ).title("Menu02A").build();
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
