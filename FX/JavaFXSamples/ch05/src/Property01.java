import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class Property01 extends Application {
  public void start(Stage stage) throws Exception {
    ArrayList<String> readonlyPropMethods = new ArrayList<String>();
    ArrayList<String> writablePropMethods = new ArrayList<String>();
    // プロパティ取得メソッドを列挙する
    Method methods[] = stage.getClass().getMethods();
    int cnt = methods.length;
    for( int i=0 ; i<cnt ; i++ ){
      Method method = methods[i];
      String propMethodName = method.getName();
      Class returnType = method.getReturnType();
      String typeName = returnType.getName();
      if( ReadOnlyProperty.class.isAssignableFrom( returnType ) ){
        try{
          ReadOnlyProperty property = (ReadOnlyProperty)method.invoke( stage, new Object[0] );
          if( property instanceof Property ){
            writablePropMethods.add( propMethodName+ " : "+ typeName );
          }
          else{
            readonlyPropMethods.add( propMethodName+ " : "+ typeName );
          }
        }
        catch( Exception e ){ System.out.println( e +" "+ method ); }
      }
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
    ListView<String> listView = new ListView<String>();
    listView.getItems().addAll( nameList );
    BorderPane root = BorderPaneBuilder.create().center(listView).build();
    //
    Scene scene = new Scene(root);
    stage = StageBuilder.create().width(420).height(640)
      .scene( scene ).title("Property01").build();
    stage.show();
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
