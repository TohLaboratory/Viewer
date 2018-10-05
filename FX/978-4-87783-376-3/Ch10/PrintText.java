// PrintText.java

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrintText extends Application {

    TextArea txtArea = new TextArea();
    Label lblStatus = new Label("");

    void fileOpen(Stage stage)
    {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("Java Files", "*.java"),
            new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fc.showOpenDialog(stage);
        if (file != null && file.isFile() == true) {
            String txt = "";
            try{
              List<String> list = Files.readAllLines(file.toPath());
              for (String s: list)
                txt += s + "\n";
            }catch(FileNotFoundException e){
              System.out.println(e);
            }catch(IOException e){
              System.out.println(e);
            }
            txtArea.setText(txt);
            lblStatus.setText(file.getPath());
        }
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("PrintText");

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuOpen = new MenuItem("開く");
        mnuOpen.setOnAction(event -> fileOpen(stage));
        MenuItem mnuPrint = new MenuItem("印刷");
        mnuPrint.setOnAction(event -> {
            PrinterJob job = PrinterJob.createPrinterJob();
            job.printPage(txtArea);
            job.endJob();
        });
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuOpen, mnuPrint, mnuExit);
        menuBar.getMenus().add(fileMenu);

        txtArea.setPrefRowCount(20);
        txtArea.setPrefColumnCount(80);
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, txtArea, lblStatus);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
