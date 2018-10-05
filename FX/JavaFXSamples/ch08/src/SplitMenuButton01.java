import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
 
public class SplitMenuButton01 extends Application {
  SplitMenuButton fileButton;

  public void start(Stage stage) throws Exception {
    //
    MenuItem save = MenuItemBuilder.create().text("ï€ë∂").id("save").graphic(new ImageView("save.gif")).build();
    MenuItem exit = MenuItemBuilder.create().text("èIóπ").id("exit").graphic(new ImageView("exit.gif")).build();
    fileButton = new SplitMenuButton( save, exit );
    fileButton.setText( save.getText() );
    fileButton.setGraphic( save.getGraphic() );
    
    save.setOnAction(actionHandler);
    exit.setOnAction(actionHandler);

    BorderPane root = BorderPaneBuilder.create().top( fileButton ).build();
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(280).height(200)
      .scene( scene ).title("SplitMenuButton01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      MenuItem menuItem = (MenuItem)e.getTarget();
      fileButton.setText( menuItem.getText() );
      fileButton.setGraphic( menuItem.getGraphic() );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
