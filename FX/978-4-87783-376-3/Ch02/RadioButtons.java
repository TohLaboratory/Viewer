// RadioButtons.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtons extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("RadioButtons");
        stage.setWidth(240);
        stage.setHeight(150);

        Label label = new Label();
        ToggleGroup group = new ToggleGroup();

        RadioButton radioBtn1 = new RadioButton("丼もの");
        radioBtn1.setToggleGroup(group);
        radioBtn1.setOnAction( event -> label.setText("丼ものが選択されました。"));

        RadioButton radioBtn2 = new RadioButton("麺類");
        radioBtn2.setToggleGroup(group);
        radioBtn2.setOnAction( event -> label.setText("麺類が選択されました。"));

        RadioButton radioBtn3 = new RadioButton("ランチ");
        radioBtn3.setToggleGroup(group);
        radioBtn3.setOnAction( event -> label.setText("ランチが選択されました。"));

        VBox root = new VBox();
        root.setSpacing(10);
        root.getChildren().addAll(radioBtn1, radioBtn2, radioBtn3, label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
