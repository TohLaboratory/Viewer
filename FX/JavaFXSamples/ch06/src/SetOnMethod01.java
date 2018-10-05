import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class SetOnMethod01 extends Application {
  TextField classField = new TextField();
  ListView<String> listView = new ListView<String>();
  //
  public void start(Stage stage) throws Exception {
    //
    classField.setOnAction( listupSetOnMethod );
    BorderPane root = BorderPaneBuilder.create().top(classField).center(listView).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(520).height(850)
      .scene( scene ).title("SetOnMethod01").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> listupSetOnMethod = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      ArrayList<String> setOnMethods = new ArrayList<String>();
      // プロパティ取得メソッドを列挙する
      String className = classField.getText();
      if( className.contains(".")==false ){
        className = "javafx.scene.control."+ className;
        classField.setText(className);
      }
      try {
        Class<?> clas = Class.forName(className);
        Method methods[] = clas.getMethods();
        int cnt = methods.length;
        for( int i=0 ; i<cnt ; i++ ){
          Method method = methods[i];
          Class<?>[] parameterTypes = method.getParameterTypes();
          if( parameterTypes.length !=1 ){
            continue;
          }
          Class<?> parameterType = parameterTypes[0];
          if( EventHandler.class.isAssignableFrom( parameterType ) ){
            String setOnMethodName = method.getName();
            String typeName = parameterType.getName();
            setOnMethods.add( setOnMethodName+ " : "+ typeName );
          }
        }
      }
      catch(Exception e1) {
        System.out.println(e1);
      }
      Collections.sort( setOnMethods );
      //
      listView.getItems().clear();
      listView.getItems().addAll( setOnMethods );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
