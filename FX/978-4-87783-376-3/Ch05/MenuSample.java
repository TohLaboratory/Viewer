// MenuSample.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuSample extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("MenuSample");
        stage.setWidth(280);
        stage.setHeight(220);

        VBox root = new VBox();

        MenuBar menuBar = new MenuBar();
        // menuBar.setUseSystemMenuBar(true);
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

        root.getChildren().addAll(menuBar);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
