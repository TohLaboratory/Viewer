// CSSFXML.java

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CSSFXML extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("CSSFXML");
        stage.setWidth(240);
        stage.setHeight(120);
		
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("CSSFXML.fxml"));
        HBox root = fxml.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
}
