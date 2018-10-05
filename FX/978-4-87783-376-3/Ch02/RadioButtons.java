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

        RadioButton radioBtn1 = new RadioButton("������");
        radioBtn1.setToggleGroup(group);
        radioBtn1.setOnAction( event -> label.setText("�����̂��I������܂����B"));

        RadioButton radioBtn2 = new RadioButton("�˗�");
        radioBtn2.setToggleGroup(group);
        radioBtn2.setOnAction( event -> label.setText("�˗ނ��I������܂����B"));

        RadioButton radioBtn3 = new RadioButton("�����`");
        radioBtn3.setToggleGroup(group);
        radioBtn3.setOnAction( event -> label.setText("�����`���I������܂����B"));

        VBox root = new VBox();
        root.setSpacing(10);
        root.getChildren().addAll(radioBtn1, radioBtn2, radioBtn3, label);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
