import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class EventType01 extends Application {
  ComboBox<String> classField = new ComboBox<String>();
  ListView<String> listView = new ListView<String>();
  //
  public void start(Stage stage) throws Exception {
    //
    classField.getItems().addAll( "javafx.scene.input.MouseEvent","javafx.scene.input.DragEvent", 
                         "javafx.scene.input.KeyEvent", "javafx.stage.WindowEvent" );
    classField.setOnAction( listupProperty );
    BorderPane root = BorderPaneBuilder.create().top(classField).center(listView).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(420).height(480)
      .scene( scene ).title("EventType01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> listupProperty = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      ArrayList<String> eventTypes = new ArrayList<String>();
      String className = classField.getValue();
      try {
        Class<?> clas = Class.forName(className);
        Field[] fields = clas.getFields();
        int cnt = fields.length;
        for( int i=0 ; i<cnt ; i++ ){
          Field field = fields[i];
          String fieldName = field.getName();
          Class<?> type = field.getType();
          String typeName = type.getName();
          if( EventType.class.isAssignableFrom( type ) ){
            eventTypes.add( fieldName +" : "+ typeName );
          }
        }
      }
      catch(Exception e1) {
        System.out.println(e1);
      }
      Collections.sort( eventTypes );
      //
      listView.getItems().clear();
      listView.getItems().addAll( eventTypes );
    }
  };

  public static void main(String[] args) {
    launch(args);
  }
}
