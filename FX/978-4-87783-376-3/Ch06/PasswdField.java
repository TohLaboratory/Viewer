// PasswdField.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PasswdField extends Application {

    Label lblMsg1 = new Label("パスワードを");
    Label lblMsg2 = new Label("入力してください。");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PasswdField");
        stage.setWidth(280);
        stage.setHeight(180);

        TextField idField = new TextField();
        PasswordField passwdField = new PasswordField();

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> onClose(passwdField.getText()));
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().add(fileMenu);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        GridPane grid = new GridPane();
        Label lblID = new Label("メールアドレス:");
        Label lblPass = new Label("パスワード　　:");
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.add(lblMsg1, 0, 0);
        grid.add(lblMsg2, 1, 0);
        grid.add(lblID,  0,  1);
        grid.add(idField,  1,  1);
        grid.add(lblPass,  0,  2);
        grid.add(passwdField,  1,  2);
        root.setCenter(grid);
        Button btnClose = new Button("閉じる");
        btnClose.setOnAction(event -> onClose(passwdField.getText()));
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().add(btnClose);
        root.setBottom(bottom);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void onClose(String passwd){

        if (passwd.equals("password"))
            System.exit(0);
        else {
            lblMsg1.setText("パスワードが");
            lblMsg2.setText("違います。");
        }

    }
}
