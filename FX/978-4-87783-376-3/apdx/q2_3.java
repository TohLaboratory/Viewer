// q2_3.java

import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q2_3 extends Application {

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q2_3");
        stage.setWidth(260);
        stage.setHeight(220);

        Label status = new Label();

        TreeView<File> tree = new TreeView<>();
        tree.setRoot(createTree(new File(".")));
        tree.setCellFactory( (event) ->
        	new TreeCell<File>() {
        	@Override
        	protected void updateItem(File item, boolean empty) {
        		super.updateItem(item, empty);
        		if (item != null) {
        			setText(item.getName());
        			setGraphic(getTreeItem().getGraphic());
        		} else {
        			setText("");
        			setGraphic(null);
        		}

        	}
        });

        tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tree.setOnMouseClicked(event -> {
            TreeItem<File> ti = tree.getSelectionModel().selectedItemProperty().get();
            if (ti != null)
                status.setText(ti.getValue().getName() + "が選択されました。");
        });

        VBox root = new VBox();
        root.getChildren().addAll(tree, status);

        stage.setScene(new Scene(root));
        stage.show();
    }

    private TreeItem<File> createTree(File file) {
	    TreeItem<File> item = new TreeItem<>(file);
        File[] childs = file.listFiles();
        if (childs != null) {
            for (File child: childs) {
                item.getChildren().add(createTree(child));
            }
            // フォルダ（ディレクトリ）のアイコンを設定する
            Image img = new Image(Paths.get("image/folder.png").toUri().toString());
            item.setGraphic(new ImageView(img));
        } else {
            // ファイルであることを示すアイコンを設定する
            Image img = new Image(Paths.get("image/text.png").toUri().toString());
            item.setGraphic(new ImageView(img));
        }
        return item;
    }
}
