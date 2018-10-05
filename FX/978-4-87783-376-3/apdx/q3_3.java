// q3_3.java

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q3_3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q3_3");
        stage.setWidth(300);
        stage.setHeight(180);

        // ラベルを作る
        Label label = new Label();
        AnchorPane.setTopAnchor(label, 95.0);
        AnchorPane.setLeftAnchor(label,  5.0);

        // ボタンを3個作る
        Button topBtn[] = new Button[3];
        for (int i=0; i<3;i++) {
          topBtn[i] = new Button(Integer.toString(i));
          topBtn[i].setPrefWidth(60);
          String txt = String.format("button3がクリックされました。", i);
          topBtn[i].setOnAction(event ->label.setText(txt));
        }
        // HBoxにButtonを配置する
        HBox top = new HBox(5);
        top.getChildren().addAll(topBtn);

        // CheckBoxを作る
        VBox left = new VBox();
        CheckBox checkBox[] = new CheckBox[4];
        for (int i=0; i<4;i++) {
			String txt = "CheckBox" + Integer.toString(i);
        	checkBox[i] = new CheckBox(txt);
            checkBox[i].setPrefWidth(120);
            checkBox[i].setOnAction(event -> {
        		String s = "";
            	for (int j=0; j<4 ; j++) {
            	    if (checkBox[j].isSelected())
            		    s = s + checkBox[j].getText();
            	}
                label.setText(s + "が選択されました。");
            });
        }
        left.getChildren().addAll(checkBox);
        
        // ListViewを作る
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
                        label.setText(new_val + "が選択されました。");
                    }
                });



        // BorderPanに配置する
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setLeft(left);
        root.setRight(listView);
        root.setBottom(label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
