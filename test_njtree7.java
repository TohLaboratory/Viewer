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

public class test_njtree7  extends Application {
    private double maxLength;
    private double wSize = 500.0;

    @Override
    public void start(Stage stage) throws Exception  {

        double[][] mtx = {{0.0,3.3,4.3,6.8,5.4},{3.3,0.0,2.0,6.1,4.7},{4.3,2.0,0.0,7.1,5.7},{6.8,6.1,7.1,0.0,3.6},{5.4,4.7,5.7,3.6,0.0}};

	DistM dm = new DistM();
        dm.setDistMtx(mtx, 5);

	/*
        for (int i = 1 ; i < 5 ; ++i)  {
	    for (int j = 0 ; j < i ; ++j)  {
		System.out.print(mtx[i][j]+"\t");
	    }
	    System.out.println();
	}
	*/

	njtreeBuilder tb = new njtreeBuilder(dm);
	/*
        int iSize = tb.getiSize();
	System.out.println("iSize = " + iSize);
	*/
        TreeDrawInfo tdi = new TreeDrawInfo(tb);

        maxLength = tdi.getMaxLength()*2.0;
	double bRatio = wSize*0.6/maxLength;

        stage.setTitle("njtree");
	stage.setWidth(wSize);
	stage.setHeight(wSize);

        Group root = new Group();

	final Canvas canvas = new Canvas(wSize,wSize);
	
	GraphicsContext gc = canvas.getGraphicsContext2D();

	gc.setLineWidth(1.0);
	gc.setStroke(Color.RED);

	for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
	    int nodeid = tb.getAncestralNodes()[i]; 
	    if ( nodeid == 0)  continue;
	    else   {
		//		System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 = " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i)); 
		gc.strokeLine(tdi.getX1(i)*bRatio+wSize*0.4,tdi.getY1(i)*bRatio+wSize*0.4,tdi.getX2(i)*bRatio+wSize*0.4,tdi.getY2(i)*bRatio+wSize*0.4);
		if (tb.getDescendantNodes()[i][0] != -1)  {
		    double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
		    double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
		    gc.strokeArc(wSize*0.4-tdi.getLength(i)*bRatio,wSize*0.4-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
		    gc.strokeOval(wSize*0.4-2.5,wSize*0.4-2.5,5,5);
		}
            }	    
	}

	/*
	gc.setLineWidth(2.0);
	gc.setStroke(Color.RED);
	gc.strokeLine(10,10,230,10);
	*/
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
