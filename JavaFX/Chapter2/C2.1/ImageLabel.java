// ImageLabel.java

import  java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageLabel extends Application  {

    @Override
    public void start(Stage stage) throws Exception
    {
	stage.setTitle("ImageTitle");
	stage.setWidth(600);
	stage.setHeight(600);
	Label  lblMsg = new Label("テキストの表示例");
	
	Label lblImg = new Label();
	Image img = new Image(Paths.get("/Users/toh/Desktop/snail2.jpg").toUri().toString());
	BackgroundImage bimg = new BackgroundImage(img,null,null,null,null);
	Background bg = new Background(bimg);
	lblImg.setMinSize(img.getWidth(), img.getHeight());
	lblImg.setBackground(bg);

	VBox root = new VBox();
	root.setAlignment(Pos.CENTER);
	root.setPadding(new Insets(10, 10, 10, 10));
	root.setSpacing(20.0);
	root.getChildren().addAll(lblMsg, lblImg);
	
	stage.setScene(new Scene(root));
	stage.show();
    }
}
