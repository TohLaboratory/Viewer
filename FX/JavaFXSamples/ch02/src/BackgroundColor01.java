import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;

public class BackgroundColor01 extends Application {
  public void start(Stage stage) throws Exception {
    //
    HBox hb1 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: blue" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb2 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: #0000ff" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb3 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: rgb(0, 0, 255)" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb4 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: rgb(0%, 0%, 100%)" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb5 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: rgba(0, 0, 255, 0.5)" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb6 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: rgba(0%, 0%, 100%, 0.5)" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb7 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: hsb(270, 100%, 100%)" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    HBox hb8 = HBoxBuilder.create().spacing( 5 )
      .style( "-fx-background-color: hsba(270, 100%, 100%, 0.5)" )
      .children( new Button("1"), new Button("2"), new Button("3") )
      .build();
    //
    VBox vb = VBoxBuilder.create().spacing( 15 )
      .children( hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8 )
      .prefWidth(240)
      .build();
    //
    Scene scene = new Scene( vb );
    stage = StageBuilder.create()
      .scene( scene ).title("BackgroundColor01").build();
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
