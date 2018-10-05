// JavaFXWnd.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class JavaFXWnd  extends  Application  {
    @Override
    public  void  start(Stage stage)  {
	stage.setTitle("Hello, JavaFX");
	stage.setWidth(540);
	stage.setHeight(200);
	Label lblMsg = new Label("Hello, JavaFX");
	lblMsg.setFont(new Font(80));
	stage.setScene(new Scene(lblMsg));
	stage.show();
    }
}
