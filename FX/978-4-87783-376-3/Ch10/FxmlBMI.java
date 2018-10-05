// FxmlBMI.java

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FxmlBMI extends Application {
	@Override    public void start(Stage stage) throws Exception {
		stage.setTitle("FxmlBMI");
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxmlBMI.fxml"));
		AnchorPane root = fxml.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private TextField txtHeight;
	@FXML
	private TextField txtWeight;
	@FXML
	private Label txtBMI;
	@FXML  // OKボタンがクリックされた
	public void onCalcClicked(ActionEvent event) {
		double h = Double.parseDouble(txtHeight.getText()) * 0.01;
		double w = Double.parseDouble(txtWeight.getText());
		String result =  String.format("BMI=%5.2f", (w / ( h * h )));
		txtBMI.setText(result);
	}
}

