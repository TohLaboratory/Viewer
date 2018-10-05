// TreeTbl.java

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TreeTbl extends Application {

	@SuppressWarnings("unchecked")
	@Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TreeTbl");
        stage.setWidth(460);
        stage.setHeight(300);

        TreeTableView<File> treeTable = new TreeTableView<>();

        TreeTableColumn<File,String> nameColumn = new TreeTableColumn<>("名前");
        nameColumn.setMinWidth(140);
        TreeTableColumn<File,Long> sizeColumn = new TreeTableColumn<>("サイズ");
        TreeTableColumn<File,Date> modifiedColumn = new TreeTableColumn<>("更新日時");

        nameColumn.setCellValueFactory( 
        		f -> new SimpleStringProperty(f.getValue().getValue().getName()));
        sizeColumn.setCellValueFactory(
        		f -> new SimpleObjectProperty<>(f.getValue().getValue().length()));
        modifiedColumn.setCellValueFactory(
        		f -> new SimpleObjectProperty<>(new Date(f.getValue().getValue().lastModified())));
        treeTable.setRoot(createTree(new File(".")));
        treeTable.getColumns().setAll(nameColumn, sizeColumn, modifiedColumn);

        stage.setScene(new Scene(treeTable));
        stage.show();
    }

    private TreeItem<File> createTree(File file) 
    {
    	// ファイルのツリー項目を作成する
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
