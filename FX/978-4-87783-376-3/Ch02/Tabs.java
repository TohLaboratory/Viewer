// Tabs.java

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tabs extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Tabs");
        stage.setWidth(230);
        stage.setHeight(220);

        Label statusBar = new Label();

        ListView<String> listView1 = new ListView<>();
        listView1.setItems(FXCollections.observableArrayList(
                "カツどん", "親子丼", "天丼", "牛丼", "玉丼"));
        Tab tab1 = new Tab("丼もの");
        tab1.setContent(listView1);
        listView1.getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<String>() {
                 @Override
                 public void changed(ObservableValue<? extends String> ov,
                      String old_val, String new_val) {
                      statusBar.setText(new_val + "が選択されました。");
                 }
        });

        ListView<String> listView2 = new ListView<>();
        listView2.setItems(FXCollections.observableArrayList(
                "そば", "うどん", "スパゲティー", "山菜そば", "鍋焼きうどん"));
        Tab tab2 = new Tab("麺類");
        tab2.setContent(listView2);
        listView2.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> ov,
                  String old_val, String new_val) {
                  statusBar.setText(new_val + "が選択されました。");
             }
        });

        ListView<String> listView3 = new ListView<>();
        listView3.setItems(FXCollections.observableArrayList(
                "カツカレー", "ヒレカツ定食", "日替わり定食", "ライス", "カレー"));
        Tab tab3 = new Tab("その他");
        tab3.setContent(listView3);
        listView3.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<String>() {
             @Override
             public void changed(ObservableValue<? extends String> ov,
                  String old_val, String new_val) {
                  statusBar.setText(new_val + "が選択されました。");
             }
        });
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        VBox panel = new VBox();
        panel.getChildren().addAll(tabPane, statusBar);

        stage.setScene(new Scene(panel));
        stage.show();
    }
}
