// q1_3.java

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

public class q1_3 extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("q1_3");
        stage.setWidth(240);
        stage.setHeight(120);

        Label lblMsg = new Label("Hello, Java!");
        lblMsg.setFont(new Font(12));

        Button btnOk = new Button("閉じる");
        btnOk.setPrefWidth(120);
        btnOk.setOnAction(event -> Platform.exit());

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20.0);
        root.getChildren().addAll(lblMsg, btnOk);

        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void main(String... args) {

        if (args.length < 1) {
        	System.out.println("引数にパスワードを指定してください。");
        	System.exit(-1);
        }

        if (! args[0].equals("Password")) {
        	System.out.println("パスワードが違います。");
        	System.exit(-1);
        }

        Application.launch(args);

   	}

}