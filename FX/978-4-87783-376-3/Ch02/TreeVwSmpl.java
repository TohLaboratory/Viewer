// TreeVwSmpl.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TreeVwSmpl extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TreeVwSmpl");
        stage.setWidth(260);
        stage.setHeight(220);

        Label status = new Label();

        TreeItem<String> rootnode = new TreeItem<>("メニュー");
        rootnode.setExpanded(true);
        TreeItem<String> donnode = new TreeItem<>("丼物");
        rootnode.getChildren().add(donnode);
        TreeItem<String> donitem0 = new TreeItem<>("かつ丼");
        TreeItem<String> donitem1 = new TreeItem<>("親子丼");
        TreeItem<String> donitem2 = new TreeItem<>("天丼");
        donnode.getChildren().add(donitem0);
        donnode.getChildren().add(donitem1);
        donnode.getChildren().add(donitem2);

        TreeItem<String> mennode = new TreeItem<>("麺類");
        rootnode.getChildren().add(mennode);
        TreeItem<String> menitem1 = new TreeItem<>("ラーメン");
        TreeItem<String> menitem2 = new TreeItem<>("タヌキうどん");
        mennode.getChildren().add(menitem1);
        mennode.getChildren().add(menitem2);

        TreeView<String> treeView = new TreeView<String>(rootnode);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        treeView.setOnMouseClicked(event -> {
            TreeItem<String> ti = treeView.getSelectionModel().selectedItemProperty().get();
            if (ti != null)
                status.setText(ti.getValue() + "が選択されました。");
        });
        
        VBox root = new VBox();
        root.getChildren().addAll(treeView, status);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
