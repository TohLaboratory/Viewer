// InitStop.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InitStop extends Application {

    public void start(Stage stage)
    {
        stage.setTitle("InitStop");
        stage.setWidth(540);
        stage.setHeight(220);
        Label lblMsg = new Label("Hello, JavaFX");
        Font font = new Font(80);
        lblMsg.setFont(font);
        Scene scene =new Scene(lblMsg);
        stage.setScene( scene );
        stage.show();
    }
    
    public void init() 
    {
        System.out.println("èâä˙âªÇµÇ‹Ç∑ÅB");
    }
    
    public void stop()
    {
        System.out.println("èIóπÇµÇ‹Ç∑ÅB");
    }
}
