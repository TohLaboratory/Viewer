// ImageViewer.java

import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageViewer extends Application {

    Canvas canvas = new Canvas();
    Label lblStatus = new Label("");

    void fileOpen(Stage stage){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG Files", "*.png"),
            new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fc.showOpenDialog(stage);
        if (file.isFile()) {
            lblStatus.setText(file.getAbsolutePath());
            GraphicsContext gc = canvas.getGraphicsContext2D();
            // ƒCƒ[ƒW‚ð•`‚­
            System.out.println(file.getAbsolutePath());
            String URL = file.getAbsolutePath();
            Image img = new Image( Paths.get( URL ).toUri().toString() );
            gc.drawImage(img, 0, 0);
        }
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("ImageViewer");
        stage.setWidth(360);
        stage.setHeight(320);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuOpen = new MenuItem("Open");
        mnuOpen.setOnAction(event -> fileOpen(stage));
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuOpen, mnuExit);
        menuBar.getMenus().add(fileMenu);
        canvas.setWidth(360);
        canvas.setHeight(300);

        VBox root = new VBox();
        ScrollPane scrl = new ScrollPane(canvas); 
        root.setPadding(new Insets(5, 5, 5, 5));
        root.setSpacing(10.0);
        root.getChildren().addAll(menuBar, scrl, lblStatus);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
