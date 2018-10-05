// LstView.java

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.Observablevalue;
import javafx.collections.FXCollections;
import javafx.collections.ObservavleList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LstView extends Application {

   @Override
   public void start(Stage stage)   {
       stage.setTitle("LstView");
       stage.setWidth(300);
       stage.setHeight(150);
       
       HBox root = new HBox();
       Label lbl = new Label();

       ObservableList<String> names = FXCollections.observableArrayList(
	  "Blue", "Green", "Yellow", "Cyan", "Red", "White", "Black");
       ListView<String> listView = new ListView<String>(names);	
       listView.setPrefWidth(80);
       listView.setPrefHeight(120);
       listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       
   }

}
