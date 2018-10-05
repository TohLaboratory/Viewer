// q5_3.java

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class q5_3 extends Application {

    Label lblWv = new Label();
    Label lblHv = new Label();
    Slider sliderH = new Slider(100, 200, 1.0);
    Slider sliderW = new Slider(20, 120, 1.0);
    Label lblBMI = new Label("BMI");

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("q5_3");
        stage.setWidth(420);
        stage.setHeight(200);

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("File");
        MenuItem mnuExit = new MenuItem("Exit");
        mnuExit.setOnAction(event -> System.exit(0));
        fileMenu.getItems().addAll(mnuExit);
        menuBar.getMenus().addAll(fileMenu);

        // �g���̃��x���ƃX���C�_�[
        Label lblH = new Label("�@�g���F");
         sliderH.setTooltip(new Tooltip("�g����ݒ�"));
        sliderH.setValue(170);
        sliderH.setPrefWidth(280);
        sliderH.setOrientation(Orientation.HORIZONTAL);
        sliderH.setShowTickMarks(true);
        sliderH.setShowTickLabels(true);
        sliderH.setMajorTickUnit(50.0f);
        sliderH.setBlockIncrement(5.0f);
        lblHv.setText(sliderH.getValue() + "cm");
        sliderH.setOnMouseClicked(event -> updateValue());
        ContextMenu popupH = new ContextMenu();
        // �R���e�L�X�g���j���[����
        MenuItem pmnuToHPlus1 = new MenuItem("+1 cm");
        pmnuToHPlus1.setOnAction(event -> { sliderH.setValue(sliderH.getValue() + 1 ); updateValue(); });
        MenuItem pmnuToHPlus5 = new MenuItem("+5 cm");
        pmnuToHPlus5.setOnAction(event -> { sliderH.setValue(sliderH.getValue() + 5 ); updateValue(); });
        MenuItem pmnuToHMinus1 = new MenuItem("-1 cm");
        pmnuToHMinus1.setOnAction(event -> { sliderH.setValue(sliderH.getValue() - 1 ); updateValue(); });
        MenuItem pmnuToHMinus5 = new MenuItem("-5 cm");
        pmnuToHMinus5.setOnAction(event -> { sliderH.setValue(sliderH.getValue() - 5 ); updateValue(); });
        popupH.getItems().addAll(pmnuToHPlus1, pmnuToHPlus5, pmnuToHMinus1, pmnuToHMinus5);
        sliderH.setContextMenu(popupH);

        HBox boxH = new HBox();
        boxH.getChildren().addAll(lblH, sliderH, lblHv);

        // �̏d�̃��x���ƃX���C�_�[
        Label lblW = new Label("�@�̏d�F");
        sliderW.setTooltip(new Tooltip("�̏d��ݒ�"));
        sliderW.setValue(60);
        sliderW.setPrefWidth(280);
        sliderW.setOrientation(Orientation.HORIZONTAL);
        sliderW.setShowTickMarks(true);
        sliderW.setShowTickLabels(true);
        sliderW.setMajorTickUnit(50.0f);
        sliderW.setBlockIncrement(5.0f);
        lblWv.setText(sliderW.getValue() + "kg");
        sliderW.setOnMouseClicked(event -> updateValue());
        ContextMenu popupW = new ContextMenu();
        // �R���e�L�X�g���j���[����
        MenuItem pmnuToWPlus1 = new MenuItem("+1 kg");
        pmnuToWPlus1.setOnAction(event -> { sliderW.setValue(sliderW.getValue() + 1 ); updateValue(); });
        MenuItem pmnuToWPlus5 = new MenuItem("+5 kg");
        pmnuToWPlus5.setOnAction(event -> { sliderW.setValue(sliderW.getValue() + 5 ); updateValue(); });
        MenuItem pmnuToWMinus1 = new MenuItem("-1 kg");
        pmnuToWMinus1.setOnAction(event -> { sliderW.setValue(sliderW.getValue() - 1 ); updateValue(); });
        MenuItem pmnuToWMinus5 = new MenuItem("-5 kg");
        pmnuToWMinus5.setOnAction(event -> { sliderW.setValue(sliderW.getValue() - 5 ); updateValue(); });
        popupW.getItems().addAll(pmnuToWPlus1, pmnuToWPlus5, pmnuToWMinus1, pmnuToWMinus5);
        sliderW.setContextMenu(popupW);

        HBox boxW = new HBox();
        boxW.getChildren().addAll(lblW, sliderW, lblWv);

        VBox center = new VBox();
        center.setAlignment(Pos.TOP_CENTER);
        center.setPadding(new Insets(1, 1, 1, 1));
        center.setSpacing(20.0);
        center.getChildren().addAll(boxH, boxW, lblBMI);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(center);

        updateValue();

        stage.setScene(new Scene(root));
        stage.show();
    }

    // BMI�l���v�Z����
    void updateValue(){
        double h = sliderH.getValue();
        double w = sliderW.getValue();
        lblHv.setText(String.format("%5.1f", h) + "cm");
        lblWv.setText(String.format("%5.1f", w) + "kg");
        double bmi = 10000.0 * w / (h * h);
        lblBMI.setText(String.format("BMI=%5.2f", bmi));
      }

}
