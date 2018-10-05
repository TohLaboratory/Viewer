// JavaFXWnd.java

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class ButtonSmpl  extends  Application  {
    @Override
    public  void  start(Stage stage)  {
	stage.setTitle("ButtonSmpl");
	stage.setWidth(240);
	stage.setHeight(120);

	Label lblMsg = new Label("Hello, JavaFX");
	lblMsg.setFont(new Font(12));

	Button btn0k = new Button("閉じる");
	btn0k.setPrefWidth(120);
        btn0k.setOnAction(event -> Platform.exit());

	VBox root = new VBox();
	root.setAlignment(Pos.CENTER);
	root.setPadding(new Insets(10,10,10,10));
	root.setSpacing(20.0);
	root.getChildren().addAll(lblMsg, btn0k);
		
	stage.setScene(new Scene(root));
	stage.show();
    }
}

