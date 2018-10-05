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

        // ���C�����j���[
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
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
        
        // �R���e�L�X�g���j���[
        MenuItem pmnuRed = new MenuItem("��");
        pmnuRed.setOnAction(event -> lbl.setStyle("-fx-background-color:red"));
        MenuItem pmnuBlue = new MenuItem("��");
        pmnuBlue.setOnAction(event -> lbl.setStyle("-fx-background-color:blue"));
        MenuItem pmnuGreen = new MenuItem("��");
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
