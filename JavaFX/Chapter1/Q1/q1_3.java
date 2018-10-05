
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class q1_3  extends  Application  {

    public static void main(String... args)  {
	if (args.length != 0 && args[0].equals("Password"))  {
            Application.launch(args);
        }
        else   System.exit(0);
    }


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

