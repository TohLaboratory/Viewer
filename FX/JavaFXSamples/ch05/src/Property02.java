import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Property02 extends Application {
  TextField classField = new TextField();
  ListView<String> listView = new ListView<String>();
  //
  public void start(Stage stage) throws Exception {
    //
    classField.setOnAction( listupProperty );
    BorderPane root = BorderPaneBuilder.create().top(classField).center(listView).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(520).height(850)
      .scene( scene ).title("Property02").build();
    stage.show();
  }
  //
  EventHandler<ActionEvent> listupProperty = new EventHandler<ActionEvent>() {
    public void handle( ActionEvent e ){
      ArrayList<String> readonlyPropMethods = new ArrayList<String>();
      ArrayList<String> writablePropMethods = new ArrayList<String>();
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
          String propMethodName = method.getName();
          Class<?> returnType = method.getReturnType();
          String typeName = returnType.getName();
          if( ReadOnlyProperty.class.isAssignableFrom( returnType ) ){
            if( Property.class.isAssignableFrom( returnType ) ){
              writablePropMethods.add( propMethodName+ " : "+ typeName );
            }
            else{
              readonlyPropMethods.add( propMethodName+ " : "+ typeName );
            }
          }
        }
      }
      catch(Exception e1) {
        System.out.println(e1);
      }
      Collections.sort( readonlyPropMethods );
      Collections.sort( writablePropMethods );
      //
      String line1 = "---- 読み取り専用プロパティ";
      String line2 = "---- 書き換え可能プロパティ";
      ArrayList<String> nameList = new ArrayList<>();
      nameList.add( line1 );
      nameList.addAll( readonlyPropMethods );
      nameList.add( line2 );
      nameList.addAll( writablePropMethods );
      //
      listView.getItems().clear();
      listView.getItems().addAll( nameList );
    }
  };
  //
  public static void main(String[] args) {
    launch(args);
  }
}
