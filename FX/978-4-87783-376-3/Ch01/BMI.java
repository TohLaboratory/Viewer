// BMI.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BMI extends Application {

    TextField textFieldH = new TextField();
    TextField textFieldW = new TextField();
    Label lblBMI = new Label("BMI");

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("BMI");
        stage.setWidth(200);
        stage.setHeight(155);

        // 身長のラベルとテキストフィールド
        Label lblH = new Label("　身長(cm)：");
        textFieldH.setPrefWidth(80);

        HBox boxH = new HBox();
        boxH.getChildren().addAll(lblH, textFieldH);

        // 体重のラベルとテキストフィールド
        Label lblW = new Label("　体重(kg)：");
        textFieldW.setPrefWidth(80);

        HBox boxW = new HBox();
        boxW.getChildren().addAll(lblW, textFieldW);

        // [計算]ボタン
        Button btnGo = new Button("計算");
        btnGo.setOnMouseClicked(event -> updateValue(stage));

        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(5, 5, 5, 5));
        root.setSpacing(5.0);
        root.getChildren().addAll(boxH, boxW, btnGo, lblBMI);

        stage.setScene(new Scene(root));
        stage.show();
    }

    // BMI値を計算する
    void updateValue(Stage stage)
    {
        String sh = textFieldH.getText();
        String sw = textFieldW.getText();
        double h = Double.parseDouble( sh.trim() );
        double w = Double.parseDouble( sw.trim() );
        double bmi = 10000.0 * w / (h * h);
        lblBMI.setText(String.format("BMI=%5.2f", bmi));
    }
}
