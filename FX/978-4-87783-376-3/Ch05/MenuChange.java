// MenuChange.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuChange extends Application {

    BorderPane root = null;
    MenuItem mnuColor = null;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("MenuChange");
        stage.setWidth(400);
        stage.setHeight(200);

        mnuColor = new MenuItem("”wŒi‚ğÔ‚É");
        // ƒƒCƒ“ƒƒjƒ…[
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ƒtƒ@ƒCƒ‹");
        mnuColor.setOnAction(event -> chageBGColor());
        MenuItem mnuExit = new MenuItem("I—¹");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuColor, mnuExit);
        menuBar.getMenus().addAll(fileMenu);

        root = new BorderPane();
        root.setTop(menuBar);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void chageBGColor()
    {

        if (mnuColor.getText().equals("”wŒi‚ğÔ‚É"))
        {
            root.setStyle("-fx-background-color:red");
            mnuColor.setText("”wŒi‚ğ”’‚É");
        } else {
            root.setStyle("-fx-background-color:white");
            mnuColor.setText("”wŒi‚ğÔ‚É");
        }
    }
}
