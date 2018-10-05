// PrintImage.java

import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
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

public class PrintImage extends Application {

    Canvas canvas = new Canvas();
    Label lblStatus = new Label("");
    PrinterJob job = null;

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
            // イメージを描く
            System.out.println(file.getAbsolutePath());
            String URL = file.getAbsolutePath();
            Image img = new Image( Paths.get( URL ).toUri().toString() );
            gc.drawImage(img, 0, 0);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ImageViewer");
        stage.setWidth(360);
        stage.setHeight(320);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuOpen = new MenuItem("開く");
        mnuOpen.setOnAction(event -> fileOpen(stage));
        MenuItem mnuPrintDialog = new MenuItem("印刷設定");
        mnuPrintDialog.setOnAction(event -> {
            if (job == null)
                job = PrinterJob.createPrinterJob();
            job.showPrintDialog(stage);
        });
        MenuItem mnuPageSetup = new MenuItem("ページ設定");
        mnuPageSetup.setOnAction(event -> {
            if (job == null)
                job = PrinterJob.createPrinterJob();
            job.showPageSetupDialog(stage);
        });
        MenuItem mnuPrint = new MenuItem("印刷");
        mnuPrint.setOnAction(event -> {
            if (job == null)
                job = PrinterJob.createPrinterJob();
            job.printPage(canvas);
            job.endJob();
        });
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuOpen, mnuPrintDialog, mnuPageSetup, mnuPrint, mnuExit);
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
