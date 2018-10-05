// ToolBarSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ToolBarSmpl extends Application {

    ToolBar toolBar = null;
    String [] btnText = {"��", "��", "��", "��", "���F", "���F", "�O���["};
    Color [] btnColor = {Color.BLUE, Color.RED, Color.BLACK, Color.
            GREEN, Color.YELLOW, Color.BROWN, Color.GRAY};
    Canvas canvas = new Canvas(250,180);
    
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("ToolBarSmpl");
        stage.setWidth(300);
        stage.setHeight(200);

        Button button[] = new Button[7];
        for (int i=0; i<7;i++) {
            button[i] = new Button(btnText[i]);
            button[i].setPrefWidth(60);
            button[i].setPrefHeight(20);
            Background bg = new Background(
                    new BackgroundFill(btnColor[i], null, null));
            button[i].setBackground(bg);
            int intColor = i;
            button[i].setOnAction(event -> selectColor(intColor));
        }
        toolBar = new ToolBar(button);

        selectColor(0);

        Label statusBar = new Label();
        statusBar.setText("�u���[");

        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(canvas);
        root.setBottom(statusBar);

        stage.setScene(new Scene(root));
        stage.show();
    }

    void selectColor(int col)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // �h��ׂ�����`��`��
        gc.setFill(btnColor[col]);
        gc.fillRect(60,20,100,60);
    }
}
