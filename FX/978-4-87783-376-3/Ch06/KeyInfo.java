// KeyInfo.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KeyInfo extends Application {

    TextArea textArea = new TextArea();
    String txt = "";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("KeyInfo");
        stage.setWidth(400);
        stage.setHeight(300);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> stage.close());
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        textArea.setEditable(false);
        textArea.setOnKeyPressed(event -> printInfo(event));
        textArea.setOnKeyTyped(event -> printInfo(event));
        textArea.setOnKeyReleased(event -> printInfo(event));

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void printInfo(KeyEvent event) {
        txt = txt + String.format("%s : %s(%s)\n",
                event.getEventType().toString(),
                event.getCharacter(),
                event.getCode().toString());
        textArea.setText( txt );
    }
}
