import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

import java.io.File;
import java.util.Properties;

public class Image01A extends Application {
	
  public void start(Stage stage) throws Exception {
    //
    ImageView imgv1 = new ImageView( "4-87783-185-1.png" ); 

    // 相対パスの基準は classpathで指定されているフォルダ
    Properties prop = System.getProperties();
    Object classPath = prop.get("java.class.path");
    System.out.println( "classPath のフォルダ  = "+ classPath );
    //
    File file = new File( "4-87783-185-1.png" );
    String path = file.toURI().getPath();
    System.out.println( "このファイルの絶対パス = "+ path );
    System.out.println();
    //    
    // このクラスファイルが存在しているフォルダーを基準にする場合は:
    String filePath = getClass().getResource("4-87783-185-1.png").toString();
    ImageView imgv2 = new ImageView( filePath );
    System.out.println( "このファイルの絶対パス(2) = "+ path );
    //
    HBox root = HBoxBuilder.create().spacing(10)
      .children( imgv1, imgv2 ).build();
    Scene scene = new Scene( root );
    //
    stage = StageBuilder.create()
      .scene( scene ).title("Image01A").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
