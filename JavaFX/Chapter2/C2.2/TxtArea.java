// TxtArea.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TxtArea  extends Application {

    @Override
    public void start(Stage stage) throws Exception {
	stage.setTitle("TxtArea");
	stage.setWidth(360);
	stage.setHeight(300);
	TextArea textArea = new TextArea();
	textArea.setPrefRowCount(5);
	textArea.setPrefColumnCount(20);
	// 幅と高さを直接指定する場合
	// textArea.setMaxWidth(200);
	// textArea.setMaxHeight(60);
	textArea.setText("複数行の文字列\n2行目\n3行目\n5行目\n5行目\n6行目\n");
	
	// 表示用のTextAreaを作成する。
	TextArea displayArea = new TextArea();
	displayArea.setPrefRowCount(5);       // 行数の指定
	displayArea.setPrefColumnCount(20);   // 桁数の指定
	// 編集できないようにする
	displayArea.setEditable(false);
	// TextFieldを結合する
	displayArea.textProperty().bind(textArea.textProperty());

	VBox root = new VBox();
	root.setAlignment(Pos.CENTER);
	root.setPadding(new Insets(10,10,10,10));
	root.setSpacing(20.0);
	root.getChildren().addAll(textArea, displayArea);

	stage.setScene(new Scene(root));
	stage.show();
    }
}
