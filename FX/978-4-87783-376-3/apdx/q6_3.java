// q6_3.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class q6_3 extends Application {

    String txt = "";
    int count = 0;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("q6_3");
        stage.setWidth(300);
        stage.setHeight(220);
        
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> stage.close());
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        TextArea textArea = new TextArea();
        textArea.prefHeight(200);
        textArea.prefWidth(300);
        textArea.setOnKeyPressed(event -> moveWnd(stage, event));

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);


        stage.setScene(new Scene(root));
        stage.show();
    }
    
    void moveWnd(Stage stage, KeyEvent event){
        KeyCode code = event.getCode();
        if (code == KeyCode.UP )
            stage.setY(stage.getY() - 10);
        if (code == KeyCode.DOWN )
            stage.setY(stage.getY() + 10);
        if (code == KeyCode.LEFT )
            stage.setX(stage.getX() - 10);
        if (code == KeyCode.RIGHT)
            stage.setX(stage.getX() + 10);
    }
}
