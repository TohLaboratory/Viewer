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
        Menu fileMenu = new Menu("�t�@�C��");
        MenuItem mnuExit = new MenuItem("�I��");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuExit);

        Menu colorMenu = new Menu("�J���[");
        MenuItem mnuRed = new MenuItem("��");
        mnuRed.setOnAction(event -> root.setStyle("-fx-background-color:red"));
        MenuItem mnuBlue = new MenuItem("��");
        mnuBlue.setOnAction(event -> root.setStyle("-fx-background-color:blue"));
        MenuItem mnuGreen = new MenuItem("��");
        mnuGreen.setOnAction(event -> root.setStyle("-fx-background-color:green"));
        colorMenu.getItems().addAll(mnuRed, mnuBlue, mnuGreen);
        menuBar.getMenus().addAll(fileMenu, colorMenu);

        root.getChildren().addAll(menuBar);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
