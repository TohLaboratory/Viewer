// LstView.java

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LstView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("LstView");
        stage.setWidth(300);
        stage.setHeight(150);

        HBox root = new HBox();
        Label lbl = new Label();
        
        ObservableList<String> names = FXCollections.observableArrayList(
                "Blue", "Green",  "Yellow",  "Cyan", "Red", "Whilte", "Black");
        ListView<String> listView = new ListView<String>(names);
        listView.setPrefWidth(80);
        listView.setPrefHeight(120);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().select(0);
        listView.setEditable(false);
        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> ov,
                            String old_val, String new_val) {
                        lbl.setText(new_val + "Ç™ëIëÇ≥ÇÍÇ‹ÇµÇΩÅB");
                    }
                });

        root.setSpacing(10);
        root.getChildren().addAll(listView, lbl);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
