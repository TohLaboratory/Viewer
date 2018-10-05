// HTMLEdit.java

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class HTMLEdit extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("HTMLEdit");
        stage.setWidth(600);
        stage.setHeight(360);

        HTMLEditor html = new HTMLEditor();
        TextArea text = new TextArea();

        String src = "<html>\n<body>\n<p>‚±‚ñ‚É‚¿‚Í</p>\n</body>\n<html/>";
        html.setHtmlText(src);
        html.setOnKeyPressed(event -> text.setText(html.getHtmlText()));
        html.setOnMousePressed(event -> text.setText(html.getHtmlText()));
        html.setPrefHeight(200);

        text.setText( html.getHtmlText() );
        text.setPrefHeight(160);
        text.setEditable(false);

        VBox root = new VBox();
        root.getChildren().addAll(html, text);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
