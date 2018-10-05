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

        TreeTableColumn<File,String> nameColumn = new TreeTableColumn<>("���O");
        nameColumn.setMinWidth(140);
        TreeTableColumn<File,Long> sizeColumn = new TreeTableColumn<>("�T�C�Y");
        TreeTableColumn<File,Date> modifiedColumn = new TreeTableColumn<>("�X�V����");

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
    	// �t�@�C���̃c���[���ڂ��쐬����
    	TreeItem<File> item = new TreeItem<>(file);
    	File[] childs = file.listFiles();
    	if (childs != null) {
    		for (File child: childs) {
    			item.getChildren().add(createTree(child));
    		}
    		// �t�H���_�i�f�B���N�g���j�̃A�C�R����ݒ肷��
    		Image img = new Image(Paths.get("image/folder.png").toUri().toString());
    		item.setGraphic(new ImageView(img));
    	} else {
    		// �t�@�C���ł��邱�Ƃ������A�C�R����ݒ肷��
    		Image img = new Image(Paths.get("image/text.png").toUri().toString());
    		item.setGraphic(new ImageView(img));
    	}
		return item;
    }
}
