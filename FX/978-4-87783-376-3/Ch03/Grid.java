// Grid.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Grid extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Grid");
        stage.setWidth(240);
        stage.setHeight(115);
        stage.initStyle(StageStyle.DECORATED);

        Button btnUp = new Button("Up");
        btnUp.setPrefWidth(80);
        btnUp.setOnAction(event -> stage.setY(stage.getY() - 10));
        Button btnDown = new Button("Down");
        btnDown.setPrefWidth(80);
        btnDown.setOnAction(event -> stage.setY(stage.getY() + 10));
        Button btnLeft = new Button("Left");
        btnLeft.setPrefWidth(80);
        btnLeft.setOnAction(event -> stage.setX(stage.getX() - 10));
        Button btnRight = new Button("Right");
        btnRight.setPrefWidth(80);
        btnRight.setOnAction(event -> stage.setX(stage.getX() + 10));
        Button btnClose = new Button("Close");
        btnClose.setPrefWidth(80);
        btnClose.setOnAction(event -> Platform.exit()); // èIóπÇ∑ÇÈ

        GridPane grid = new GridPane();
        GridPane.setConstraints(btnUp, 1, 0);
        GridPane.setConstraints(btnDown, 1, 2);
        GridPane.setConstraints(btnLeft, 0, 1);
        GridPane.setConstraints(btnRight, 2, 1);
        GridPane.setConstraints(btnClose, 1, 1);
        grid.getChildren().addAll(btnUp, btnDown, btnLeft, btnRight, btnClose);

        stage.setScene(new Scene(grid));
        stage.show();
    }
}
