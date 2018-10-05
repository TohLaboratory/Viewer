// InitStop.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InitStop2 extends Application {

    public static void main(String... args)  {
	Application.launch(args);
    }

  @Override
  public void start(Stage stage)  {
      stage.setTitle("InitStop");
      stage.setWidth(540);
      stage.setHeight(220);
      Label lblMsg = new Label("Hello JavaFX");
      Font font = new Font(80);
      lblMsg.setFont(font);
      Scene scene = new Scene(lblMsg);
      stage.setScene(scene);
      stage.show();
  }

  public void init()  {
      System.out.println("初期化します");
  }

  public void  stop()  {
      System.out.println("終了します");
  } 

}
