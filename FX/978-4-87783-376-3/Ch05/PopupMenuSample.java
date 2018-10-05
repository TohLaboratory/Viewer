// PopupMenuSample.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PopupMenuSample extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("PopupMenuSample");
        stage.setWidth(400);
        stage.setHeight(200);

        BorderPane root = new BorderPane();
        Label lbl = new Label("Hello, JavaFX!");
        lbl.setFont(new Font(36));

        // メインメニュー
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuExit);

        Menu colorMenu = new Menu("カラー");
        MenuItem mnuRed = new MenuItem("赤");
        mnuRed.setOnAction(event -> root.setStyle("-fx-background-color:red"));
        MenuItem mnuBlue = new MenuItem("青");
        mnuBlue.setOnAction(event -> root.setStyle("-fx-background-color:blue"));
        MenuItem mnuGreen = new MenuItem("緑");
        mnuGreen.setOnAction(event -> root.setStyle("-fx-background-color:green"));
        colorMenu.getItems().addAll(mnuRed, mnuBlue, mnuGreen);
        menuBar.getMenus().addAll(fileMenu, colorMenu);
        
        // コンテキストメニュー
        MenuItem pmnuRed = new MenuItem("赤");
        pmnuRed.setOnAction(event -> lbl.setStyle("-fx-background-color:red"));
        MenuItem pmnuBlue = new MenuItem("青");
        pmnuBlue.setOnAction(event -> lbl.setStyle("-fx-background-color:blue"));
        MenuItem pmnuGreen = new MenuItem("緑");
        pmnuGreen.setOnAction(event -> lbl.setStyle("-fx-background-color:green"));
        final ContextMenu popup = new ContextMenu();
        popup.getItems().addAll(pmnuRed, pmnuBlue, pmnuGreen);
                             
        lbl.setContextMenu(popup);
        
        root.setTop(menuBar);
        root.setCenter(lbl);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
