import java.lang.reflect.Method;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class ChangeListener02 extends Application {
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene( new Group() );
    stage = StageBuilder.create()
      .scene( scene ).width(300).height(200).title("ChangeListener02").build();
    //
    ChangeListener<Object> checkProperty = new CheckProperty();
    //
    // プロパティ取得メソッドを列挙する
    Method methods[] = stage.getClass().getMethods();
    int cnt = methods.length;
    for( int i=0 ; i<cnt ; i++ ){
      Method method = methods[i];
      Class returnType = method.getReturnType();
      if( ReadOnlyProperty.class.isAssignableFrom( returnType ) ){
        try{
          ObservableValue property = (ObservableValue)method.invoke( stage, new Object[0] );
          property.addListener( checkProperty );
        }
        catch( Exception e ){ System.out.println( e +" "+ method ); }
      }
    }
    //
    stage.show();
  }
  //
  class CheckProperty implements ChangeListener<Object> {
    public void changed( ObservableValue<?> observable, Object oldValue, Object newValue ) {
      ReadOnlyProperty prop = (ReadOnlyProperty)observable;
      String propName = prop.getName();
      System.out.println( propName +" : 変更前="+ oldValue +" 変更後="+ newValue );
    }
  }
  //
  public static void main(String[] args) {
    launch(args);
  }
}
