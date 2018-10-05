package package_Image01B; //**

import java.util.Properties;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Image01B extends Application {
	//
  public void start(Stage stage) throws Exception {
    //
    Properties prop = System.getProperties();
    Object classPath = prop.get("java.class.path");
    System.out.println( "classPath="+ classPath );
    //
    // 相対パスの基準は、このクラスのクラスファイルがあるディレクトリーではなく
    //   classpathで指定されているディレクトリー
    ImageView imgv = new ImageView( "image/4-87783-185-1.png" );
    //
    Group root = GroupBuilder.create()
      .children( imgv )
      .build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image01B")
      .build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
