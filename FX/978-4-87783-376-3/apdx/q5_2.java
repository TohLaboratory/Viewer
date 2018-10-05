// q5_2.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class q5_2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q5_2");
        stage.setWidth(280);
        stage.setHeight(180);

        VBox root = new VBox();
        Line line1 = new Line(10, 120, 200, 120);
        line1.setStrokeWidth(1.0);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(false);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuExit);

        Menu lineMenu = new Menu("線");
        Menu mnuStrokeWidth = new Menu("太さ");
        lineMenu.getItems().addAll(mnuStrokeWidth);

        MenuItem mnuWidth05 = new MenuItem("0.5");
        mnuWidth05.setOnAction(event -> line1.setStrokeWidth(0.5));
        MenuItem mnuWidth1 = new MenuItem("1.0");
        mnuWidth1.setOnAction(event -> line1.setStrokeWidth(1.0));
        MenuItem mnuWidth3 = new MenuItem("3.0");
        mnuWidth3.setOnAction(event -> line1.setStrokeWidth(3.0));
        MenuItem mnuWidth5 = new MenuItem("5.0");
        mnuWidth5.setOnAction(event -> line1.setStrokeWidth(5.0));
        mnuStrokeWidth.getItems().addAll(mnuWidth05, mnuWidth1, mnuWidth3, mnuWidth5);

        Menu mnuStrokeColor = new Menu("色");
        lineMenu.getItems().addAll(mnuStrokeColor);
        MenuItem mnuBlack = new MenuItem("黒");
        mnuBlack.setOnAction(event -> line1.setStroke(Color.BLACK));
        MenuItem mnuRed = new MenuItem("赤");
        mnuRed.setOnAction(event -> line1.setStroke(Color.RED));
        MenuItem mnuBlue = new MenuItem("青");
        mnuBlue.setOnAction(event -> line1.setStroke(Color.BLUE));
        mnuStrokeColor.getItems().addAll(mnuBlack, mnuRed, mnuBlue);

        menuBar.getMenus().addAll(fileMenu, lineMenu);

        root.setSpacing(30);
        root.getChildren().addAll(menuBar, line1);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
