// Coordinates.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Coordinates extends Application {

    TextArea textArea = new TextArea();
    String txt = "";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Coordinates");
        stage.setWidth(400);
        stage.setHeight(300);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> stage.close());
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        textArea.setMouseTransparent(true);
        textArea.setEditable(false);

        BorderPane root = new BorderPane();
        root.setOnMouseClicked(event -> printInfo(event));
        root.setTop(menuBar);
        root.setCenter(textArea);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void printInfo(MouseEvent event) {
        txt = txt + String.format("(%5.0f,%5.0f) (%5.0f,%5.0f) (%5.0f,%5.0f)\n",
        event.getSceneX(), event.getSceneY(),
        event.getScreenX(), event.getScreenY(),
        event.getX(), event.getY());
        textArea.setText( txt );
    }
}
