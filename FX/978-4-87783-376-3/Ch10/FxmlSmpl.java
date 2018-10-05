// FxmlSmpl.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FxmlSmpl extends Application {
	
    @Override
    public void start(Stage stage) throws Exception 
    {
        stage.setTitle("FxmlSmpl");
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxmlSmpl.fxml"));
        HBox root = fxml.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
