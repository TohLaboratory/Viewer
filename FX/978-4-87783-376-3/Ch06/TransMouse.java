// TransMouse.java

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TransMouse extends Application {

    TextArea textArea = new TextArea();
    String txt = "";
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TransMouse");
        stage.setWidth(400);
        stage.setHeight(300);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> stage.close());
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        // マウスイベントを受け取らないようにする
        textArea.setMouseTransparent(true);

        textArea.setEditable(false);

        BorderPane root = new BorderPane();
        root.setOnMouseClicked(event -> printInfo(event));
        root.setOnMouseEntered(event -> printInfo(event));
        root.setOnMouseExited(event -> printInfo(event));
        root.setOnMousePressed(event -> printInfo(event));
        root.setOnMouseReleased(event -> printInfo(event));
        root.setTop(menuBar);
        root.setCenter(textArea);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    void printInfo(Event event) {
        txt = txt + String.format("%s\n",  event.getEventType().toString());
        textArea.setText( txt );
    }
}
