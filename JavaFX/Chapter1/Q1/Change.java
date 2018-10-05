// Change.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Change extends Application  {
    Label label = new Label("Hello, Java!");

    @Override
    public  void  start(Stage stage) throws Exception  {
	stage.setTitle("Change");
	stage.setWidth(200);
	stage.setHeight(155);
	
	Button btnGo = new Button("Click");
	btnGo.setOnMouseClicked(event -> updateValue(stage));

	VBox root = new VBox();
	root.setAlignment(Pos.TOP_CENTER);
	root.setPadding(new Insets(5, 5, 5, 5));
	root.setSpacing(5.0);
	root.getChildren().addAll(label, btnGo);

	stage.setScene(new Scene(root));
	stage.show();
    }

    // BMI値を計算する
    void updateValue(Stage stage)  {
	label.setText("Hello, JavaFX!");
    }
}
