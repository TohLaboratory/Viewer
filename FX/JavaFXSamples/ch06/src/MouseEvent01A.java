import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

public class MouseEvent01A extends Application {
  public void start(Stage stage) throws Exception {
    Ellipse elipse = new Ellipse( 350, 250, 320, 190 );
    Circle circle1 = new Circle( 220, 250, 120 );
    Circle circle2 = new Circle( 480, 250, 120 );
    Shape shape1 = Shape.subtract( elipse, circle1 );
    Shape shape2 = Shape.subtract( shape1, circle2 );
    shape2.setFill( Color.WHITE );
    //
    Button button = new Button( "非矩形ウインドウの中のボタン" );
    button.setLayoutX( 260 ); button.setLayoutY( 100 );
    button.addEventHandler( MouseEvent.ANY, mouseEventHandler );
    //
    Group root = new Group();
    root.getChildren().addAll( shape2, button );
    root.addEventHandler( MouseEvent.ANY, mouseEventHandler );
    //
    Scene scene = SceneBuilder.create()
      .fill( Color.TRANSPARENT ) // ウインドウの背景も透明にする
      .root( root ).build();
    //
    stage = StageBuilder.create()
      .style( StageStyle.TRANSPARENT ) // ウインドウを透明にする
      .scene( scene )
      .x(420).y(300)
      .build();
    stage.show();
  }
  //----------------------------------------------------------------------------
  EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>() {
    public void handle(MouseEvent e){
      Object source = e.getSource();
      EventTarget target = e.getTarget();
      EventType<? extends Event> type = e.getEventType();
      System.out.print( "\n"+ source +" "+ target +" "+ type +" "+ msgPoint(e) );
    }
  };
  //-----------------------------------------------------------
  String msgPoint( MouseEvent e ){
    double x = e.getX(); double y = e.getY();
    double x1 = e.getSceneX(); double y1 = e.getSceneY();
    double x2 = e.getScreenX(); double y2 = e.getScreenY();
    String msg = String.format("\n   相対座標(%f %f) シーン座標(%f %f) スクリーン座標(%f %f)", x,y,x1,y1,x2,y2 );
    return( msg ); 
  }
  //-------------------------------------------------------------------------------------
  public static void main(String[] args) {
    launch(args);
  }
}
