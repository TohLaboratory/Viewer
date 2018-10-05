// Spliter.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Spliter extends Application {

    @Override
    public void start(Stage stage) throws Exception 
    {
        stage.setTitle("Spliter");
        stage.setWidth(280);
        stage.setHeight(200);

        SplitPane splitPane = new SplitPane();
        // TextArea
        TextArea txtl = new TextArea("���̃e�L�X�g�ł��B");
        TextArea txtr = new TextArea("����́A�E�̃e�L�X�g�ł��B");

        splitPane.getItems().addAll(txtl, txtr);
        splitPane.setDividerPositions(0.5f);

        stage.setScene(new Scene(splitPane));
        stage.show();
    }
}