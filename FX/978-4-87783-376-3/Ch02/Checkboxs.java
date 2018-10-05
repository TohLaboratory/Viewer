// Checkboxs.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Checkboxs extends Application {

    CheckBox checkBox1 = new CheckBox("丼もの");
    CheckBox checkBox2 = new CheckBox("麺類");
    CheckBox checkBox3 = new CheckBox("ランチ");
    Label label = new Label();

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Checkboxs");
        stage.setWidth(230);
        stage.setHeight(150);

        checkBox1.setOnAction( event -> setLabelText());
        checkBox2.setOnAction( event -> setLabelText());
        checkBox3.setOnAction( event -> setLabelText());

        VBox root = new VBox();
        root.setSpacing(10);
        root.getChildren().addAll(checkBox1, checkBox2, checkBox3, label );

        stage.setScene(new Scene(root));
        stage.show();
    }
    
    void setLabelText()
    {
        String s = "";
        if (checkBox1.isSelected())
            s = checkBox1.getText();
        if (checkBox2.isSelected())
            s += " " + checkBox2.getText();
        if (checkBox3.isSelected())
            s += " " + checkBox3.getText();
        label.setText("オーダー：" + s);
    }
}
