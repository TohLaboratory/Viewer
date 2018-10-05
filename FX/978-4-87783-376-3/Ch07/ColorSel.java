// ColorSel.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorSel extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("ColorSel");
        stage.setWidth(420);
        stage.setHeight(340);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        final ColorPicker colorPicker = new ColorPicker();
        VBox center = new VBox();
        center.getChildren().add(colorPicker);

        colorPicker.setOnAction(event -> {
            Color col = colorPicker.getValue();
            Background bg = new Background(new BackgroundFill(col, null, null));
            center.setBackground(bg);
        });

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(center);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
