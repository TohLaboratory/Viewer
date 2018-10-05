// q2_2.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q2_2 extends Application {

	CheckBox checkBox[] = new CheckBox[6];
	String name[] = {"ハンバーガー", "チーズバーガー", "フィレオフィッシュ",
			"フライドポテト", "コーヒー", "コーラ"};
    Label label = new Label();

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q2_2");
        stage.setWidth(360);
        stage.setHeight(200);

        for (int i=0; i<6; i++) {
        	checkBox[i] = new CheckBox(name[i]);
            checkBox[i].setOnAction( event -> setLabelText());
        }

        VBox root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(checkBox);
        root.getChildren().add(label );

        stage.setScene(new Scene(root));
        stage.show();
    }

    void setLabelText()
    {
        String s = "";
        for (int i=0; i<6; i++) {
            if (checkBox[i].isSelected())
            	if (s.length() <1)
                    s = checkBox[i].getText();
            	else
                    s = s + "," + checkBox[i].getText();
        }
        label.setText("オーダー：" + s);
    }
}
