import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage; 

public class test_arc  extends Application {
    private double maxLength;

    @Override
    public void start(Stage stage) throws Exception  {


        stage.setTitle("njtree");
	stage.setWidth(300);
	stage.setHeight(300);

        Group root = new Group();

	final Canvas canvas = new Canvas(260,260);
	
	GraphicsContext gc = canvas.getGraphicsContext2D();
	gc.strokeLine(120,120,150,120);
	gc.strokeOval(130-10,130-10,20,20);
	gc.strokeArc(150 , 40, 50, 50, 0, 90, ArcType.OPEN);
	gc.strokeLine(150,40,175, 40);
	gc.strokeLine(200,40,200,65);
	gc.strokeOval(150,40, 10,10);

	//	gc.strokeArc(120,120,20,20,0,90,ArcType.OPEN);
	// strokeOval( 横位置 , 縦位置 , 横幅 , 高さ );
	// gc.strokeArc(posX - RADIUSHALF, posY - RADIUSHALF, RADIUS, RADIUS, 45, 90, ArcType.OPEN);
	/*
public void strokeArc(double x,
                      double y,
                      double w,
                      double h,
                      double startAngle,
                      double arcExtent,
                      ArcType closure)

現在のストローク・ペイントを使用して円弧の外周を描画します。ArcTypeがnullまたは幅または高さが負の場合は、レンダリング・コマンドは無視されます。

このメソッドは、レンダリング属性表に示されている、グローバル共通およびストロークのすべての属性の影響を受けます。

パラメータ:
    x - 円弧のX座標。
    y - 円弧のY座標。
    w - 円弧の幅。
    h - 円弧の高さ。
    startAngle - 円弧の始角(度単位)。
    arcExtent - 円弧の角の大きさ(度単位)。
    closure - 閉じタイプ(丸め、弦型、開く)またはnull 
	*/
	// 円弧タイプ(ArcType.OPEN、ArcType.CHORDまたはArcType.ROUND)
	//	gc.strokeOval(125,40,50,80);
	/*
	gc.strokeArc(150 , 40, 50, 50, 0, 90, ArcType.OPEN);
	gc.strokeArc(145, 45, 50, 50, 0, 145, ArcType.OPEN);
	*/

	root.getChildren().add(canvas);

	stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

}
