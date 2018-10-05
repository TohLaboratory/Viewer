// Choice.java

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Choice extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Choice");
        stage.setWidth(280);
        stage.setHeight(180);

        // 色を表示する四角形
        Rectangle rect = new Rectangle(200, 200);

        // ChoiceBox
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().addAll("RED", "BLUE", "GREEN", "LIGHTGRAY");
        cb.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> ov,
                            String old_val, String new_val) {
                        rect.setFill(Color.valueOf(cb.getValue()));
                    }
                });
        cb.getSelectionModel().selectFirst();

        HBox root = new HBox();
        root.getChildren().addAll(cb, rect);

        stage.setScene(new Scene(root));
        stage.show();
    }
}