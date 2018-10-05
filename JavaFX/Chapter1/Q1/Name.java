import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Name extends Application {

    @Override
    public void start(Stage stage)  {
	stage.setTitle("Name");
	stage.setWidth(540);
	stage.setHeight(220);
	Label name = new Label("Hiroyuki Toh");
	name.setFont(new Font(80));
	stage.setScene(new Scene(name));
	stage.show();
    }
}
