// InlineCSS.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InlineCSS extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("InlineCSS");
        stage.setWidth(240);
        stage.setHeight(120);
  
       VBox root = new VBox();
       Label lbl = new Label("Hello");
       Button btn = new Button("Click me!");
       btn.setStyle("-fx-effect: dropshadow(three-pass-box, gray, 1.0, 1.0, 3.0, 3.0);");
       btn.setOnAction(event -> lbl.setText("Clicked"));
       root.setAlignment(Pos.CENTER);
       root.setPadding(new Insets(10, 10, 10, 10));
       root.setSpacing(20.0);
       root.getChildren().addAll(lbl, btn);

       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
}
