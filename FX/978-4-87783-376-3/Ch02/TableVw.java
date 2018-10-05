// TableVw.java

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableVw extends Application {

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TableVw");
        stage.setWidth(260);
        stage.setHeight(180);

        ObservableList<Person> data = FXCollections.observableArrayList(
        		new Person("花岡", "実太", "Hanaoka@gmail.cam"),
        		new Person("山田", "花子", "HanakoY@hmail.cam"),
        		new Person("羽生", "譲", "Hanyu@yaho.ca.jp"),
        		new Person("織田", "猿男", "Saru@gmail.cam")
        		);
        TableView<Person> table = new TableView<>();
        table.itemsProperty().setValue(data);
        TableColumn<Person, String> seiColumn = new TableColumn<>("姓");
        TableColumn<Person, String> meiColumn = new TableColumn<>("名");
        TableColumn<Person, String> eMailColumn = new TableColumn<>("Eメール");
        seiColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("sei"));
        meiColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("mei"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("eMail"));
        table.getColumns().addAll(seiColumn, meiColumn, eMailColumn);

        VBox root = new VBox();
        root.getChildren().addAll(table);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
