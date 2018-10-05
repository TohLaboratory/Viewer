import java.util.ArrayList;
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

public class test_njtree8v5  extends Application {
    private double maxLength;
    private double wSize = 500.0;

    @Override
    public void start(Stage stage) throws Exception  {

	//	double[][] mtx = {{0.0,3.3,4.3,6.8,5.4},{3.3,0.0,2.0,6.1,4.7},{4.3,2.0,0.0,7.1,5.7},{6.8,6.1,7.1,0.0,3.6},{5.4,4.7,5.7,3.6,0.0}};
	//	double[][] mtx = {{0.0,0.4,0.8,1.0,1.4,1.5},{0.4,0.0,1.0,1.2,1.6,1.7},{0.8,1.0,0.0,0.4,1.6,1.7},{1.0,1.2,0.4,0.0,1.8,1.9},{1.4,1.6,1.6,1.8,0.0,1.7},{1.5,1.7,1.7,1.9,1.7,0.0}};
	//	double[][] mtx = {{0.0,0.4,0.6,0.8,0.6,0.6},{0.4,0.0,0.8,1.0,0.8,0.8},{0.6,0.8,0.0,0.4,0.4,0.6},{0.8,1.0,0.4,0.0,0.6,0.8},{0.6,0.8,0.4,0.6,0.0,0.6},{0.6,0.8,0.6,0.8,0.6,0.0}};
        double[][] mtx = {{0.0,0.3,0.8,0.9,1.0,1.2,0.8},{0.3,0.0,0.9,1.0,1.1,1.3,0.9},{0.8,0.9,0.0,0.9,1.0,1.2,0.8},{0.9,1.0,0.9,0.0,0.7,1.1,0.7},{1.0,1.1,1.0,0.7,0.0,1.2,0.8},{1.2,1.3,1.2,1.1,1.2,0.0,0.6},{0.8,0.9,0.8,0.7,0.8,0.6,0.0}};
     
	DistM dm = new DistM();
	//dm.setDistMtx(mtx, 5);
        dm.setDistMtx(mtx, 7);

	/*
        for (int i = 1 ; i < 5 ; ++i)  {
	    for (int j = 0 ; j < i ; ++j)  {
		System.out.print(mtx[i][j]+"\t");
	    }
	    System.out.println();
	}
	*/
	System.out.println("BEFORE");
	
	for (int i = 0 ; i < 7 ; ++i)  {
	    for (int j = 0 ; j < 7 ; ++j)  {
		/*
	for (int i = 0 ; i < 5 ; ++i)  {
	    for (int j = 0 ; j < 5 ; ++j)  {
		*/
		System.out.print(dm.getDistMtx()[i][j]+" ");
	    }
	    System.out.println();
	}

	njtreeBuilder tb = new njtreeBuilder(dm, 1);
	System.out.println("##0");

	/*
	System.out.println("AFTER");

	for (int i = 0 ; i < 7 ; ++i)  {
	    for (int j = 0 ; j < 7 ; ++j)  {
		System.out.print(dm.getDistMtx()[i][j]+" ");
	    }
	    System.out.println();
	}
	*/

	int[][] tbClusterMembers0 = tb.getClusterMembers(0);
	int[][] tbClusterMembers1 = tb.getClusterMembers(1);
        int[]   tbClusterMembers0Size = tb.getClusterMembersSize(0);
        int[]   tbClusterMembers1Size = tb.getClusterMembersSize(1);

	/*
	ArrayList<Integer> clusterList = tb.ClusteringFromASpecifiedNode(11, 5);
	for (int i = 0 ; i < clusterList.size() ; ++i)  {
	    System.out.println("Cchk = " + i + ", id = " + clusterList.get(i).intValue());
	}
	*/

	for (int i = 0 ; i < 13 ; ++i)  {
	    //for (int i = 0 ; i < 9 ; ++i)  {
	    System.out.println(">> nodeid = " + i);
	    System.out.println("  0 :" + tbClusterMembers0Size[i]);
	    for (int j = 0 ; j < tbClusterMembers0Size[i] ; ++j)  {
		System.out.print(tbClusterMembers0[i][j] + " ");
	    }
	    System.out.println();
	    System.out.println("  1 :" + tbClusterMembers1Size[i]);
	    for (int j = 0 ; j < tbClusterMembers1Size[i] ; ++j)  {
		System.out.print(tbClusterMembers1[i][j] + " ");
	    }
	    System.out.println();
	}  
	/*
        int iSize = tb.getiSize();
	System.out.println("iSize = " + iSize);
	*/

	System.out.println("##1");
	TreeDrawInfo tdi = new TreeDrawInfo(tb, 12, 3);
	System.out.println("##2");
	maxLength = tdi.getMaxLength()*2.0;
	double bRatio = wSize*0.6/maxLength;

        stage.setTitle("njtree");
	stage.setWidth(wSize);
	stage.setHeight(wSize);

        Group root = new Group();

	final Canvas canvas = new Canvas(wSize,wSize);
	
	GraphicsContext gc = canvas.getGraphicsContext2D();

	gc.setLineWidth(1.0);
	//	gc.setStroke(Color.RED);
	/*    */
	for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
	    int nodeid = tb.getAncestralNodes()[i]; 
	    if ( nodeid == 0)  continue;
	    else   {
		//		System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 = " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i)); 
		switch(tdi.getnodeColor(i))  {
		   case 'W':  gc.setStroke(Color.WHITE);
		              break;
		   case 'B':  gc.setStroke(Color.BLUE);
		              break;
		   case 'Y':  gc.setStroke(Color.YELLOW);
		              break;
		   case 'R':  gc.setStroke(Color.RED);
		              break;
		   default :  gc.setStroke(Color.RED);
		}
		gc.strokeLine(tdi.getX1(i)*bRatio+wSize*0.4,tdi.getY1(i)*bRatio+wSize*0.4,tdi.getX2(i)*bRatio+wSize*0.4,tdi.getY2(i)*bRatio+wSize*0.4);
		if (tb.getDescendantNodes()[i][0] != -1)  {
		    double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
		    double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
		    gc.strokeArc(wSize*0.4-tdi.getLength(i)*bRatio,wSize*0.4-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
		    gc.strokeOval(wSize*0.4-2.5,wSize*0.4-2.5,5,5);
		}
		else {
		    gc.setFill(Color.WHITE); /*   CHK */
		    gc.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.4,tdi.getY1(i)*bRatio+wSize*0.4);
		}
            }	    
	}
	/*    */
	/*
	gc.setLineWidth(2.0);
	gc.setStroke(Color.RED);
	gc.strokeLine(10,10,230,10);
	*?
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

	stage.setScene(new Scene(root, 300, 300, Color.BLACK));
        stage.show();

	int[] pRoot = tb.getputativeRoot();
	System.out.println("pRoot = " + pRoot[0] + ", " + pRoot[1]);
    }

}
