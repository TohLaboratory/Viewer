import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class FileChooser01 extends Application {

    @Override
	public void start(final Stage primaryStage) {
        Button btn = new Button();
        btn.setText("ファイル選択");

        final DirectoryChooser fc = new DirectoryChooser();
        fc.setTitle("ファイル選択");

        final Label label = new Label();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
		public void handle(ActionEvent event) {
                File importFile = fc.showOpenDialog(primaryStage);

                if (importFile != null) {
                    label.setText(importFile.getPath().toString());
                }
            }
	    });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(btn, label);

        StackPane root = new StackPane();
        root.getChildren().addAll(vbox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
