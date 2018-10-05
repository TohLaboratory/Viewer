// DragMouse.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class DragMouse extends Application {

    Pane canvas = new Pane();
    Path path = new Path();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("DragMouse");
        stage.setWidth(400);
        stage.setHeight(300);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> stage.close());
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);
        canvas.getChildren().add(path);
        canvas.setOnMousePressed(event -> printInfo(event));
        canvas.setOnMouseDragged(event -> printInfo(event));

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void printInfo(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            path.getElements().add(new MoveTo(event.getX(), event.getY()));
        }
        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            path.getElements().add(new LineTo(event.getX(), event.getY()));
        }
    }
}
