// RGBColor.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RGBColor extends Application {

    Slider sliderR = new Slider(0, 255, 128);
    Slider sliderG = new Slider(0, 255, 128);
    Slider sliderB = new Slider(0, 255, 128);
    Canvas canvas = new Canvas(600, 80);

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("RGBColor");
        stage.setWidth(400);
        stage.setHeight(280);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        Label lblR = new Label("Red:");
        sliderR.setMaxWidth(380);
        sliderR.setShowTickMarks(true);
        sliderR.setShowTickLabels(true);
        sliderR.setMajorTickUnit(64.0);
        sliderR.setBlockIncrement(1.0);
        sliderR.setOnKeyPressed(event -> updateColor());
        sliderR.setOnMouseMoved(event ->  updateColor());
        Label lblG = new Label("Green:");
        sliderG.setMaxWidth(380);
        sliderG.setShowTickMarks(true);
        sliderG.setShowTickLabels(true);
        sliderG.setMajorTickUnit(64.0);
        sliderG.setBlockIncrement(1.0);
        sliderG.setOnKeyPressed(event -> updateColor());
        sliderG.setOnMouseMoved(event -> updateColor());
        Label lblB = new Label("Brue:");
        sliderB.setMaxWidth(380);
        sliderB.setShowTickMarks(true);
        sliderB.setShowTickLabels(true);
        sliderB.setMajorTickUnit(64.0);
        sliderB.setBlockIncrement(1.0);
        sliderB.setOnKeyPressed(event -> updateColor());
        sliderB.setOnMouseMoved(event -> updateColor());

        VBox panel = new VBox();
        panel.getChildren().addAll(
                lblR, sliderR, lblG, sliderG, lblB, sliderB, canvas);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(panel);
        
        updateColor();

        stage.setScene(new Scene(root));
        stage.show();
    }
    
    void updateColor()
    {
        Color col = Color.rgb(
                (int)sliderR.getValue(),
                (int)sliderG.getValue(),
                (int)sliderB.getValue());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(col);
        gc.fillRect(0, 0, 660,  80);
    }
}
