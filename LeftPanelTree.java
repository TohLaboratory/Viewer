import java.io.*;
import java.util.ArrayList;

public class LeftPanelTree   {
    private int[] pRoot;
    private double[] xcd;
    private double[] ycd;
    private ArrayList<Integer> idOfClusters;
    private int clusterSize;
    private double[] angles;
    private ArrayList<list4Sort> l4s = new ArrayList<list4Sort>();
    private int[][] DescendantNodes;
    private double[] BranchLength2AncestralNode;
    private int leafNumber;
    private int[] newOrder;

    LeftPanelTree(njtreeBuilder tb, importCluster clinf, TreeDrawInfo tdi) {
	pRoot = tb.getputativeRoot();
        leafNumber = tb.getleafNumber();

	xcd = new double[2*leafNumber-1];  // nodeのX座標
	ycd = new double[2*leafNumber-1];  // nodeのY座標

	idOfClusters = clinf.getidOfClusters();
	clusterSize = clinf.getClusterSize();
        angles = new double[clusterSize];

        DescendantNodes = tb.getDescendantNodes();
	BranchLength2AncestralNode = tb.getBranchLength2AN();

	for (int i = 0 ; i < clusterSize ; ++i)  {
	    l4s.add(new list4Sort(idOfClusters.get(i).intValue(), tdi.getAngle(idOfClusters.get(i).intValue()), i));
	}

	/*  */
	for (int i = 0 ; i < clusterSize ; ++i)  {
	    System.out.println(l4s.get(i).get_originalOrder() + " : " + l4s.get(i).get_Value() + " : " +  l4s.get(i).get_idx());
	}
	System.out.println("DDDD");
	/*  */
	/*  
	for (int i = clusterSize-1 ; i >= 0 ; --i)  {
	    l4s.add(new list4Sort(idOfClusters.get(i).intValue(), tdi.getAngle(idOfClusters.get(i).intValue())));
	}
          
	for (int i = 0 ; i < clusterSize ; ++i)  {
	    System.out.println(">> " + l4s.get(i).get_idx() + "; " + l4s.get(i).get_Value());
	}
        System.out.println("//");
	  */

	BubbleSort_l4s bl4s = new BubbleSort_l4s(l4s);

	/*  2018/04/29  クラスタのノードの確認 
	System.out.println("XXXX");
	System.out.println("clusterSize = " +  clusterSize);
	for (int i = 0 ; i < clusterSize ; ++i)  {
	    System.out.println(">> " + l4s.get(i).get_idx() + "; " + l4s.get(i).get_Value());
	}
        System.out.println("XXXX");
        */

	/*  */
	for (int i = 0 ; i < clusterSize ; ++i)  {
	    System.out.println(l4s.get(i).get_originalOrder() + " : " + l4s.get(i).get_Value() + " : " +  l4s.get(i).get_idx());
	}
	System.out.println("EEEE");
	/*  */

	newOrder = new int[clusterSize];

	for (int i = 0 ; i < clusterSize ; ++i)  {
	    //  ycd[l4s.get(i).get_idx()] = 50 + i * 100;
	    ycd[l4s.get(i).get_idx()] = 50 + i * 50;
	    newOrder[i] = l4s.get(i).get_originalOrder();
      	}

	//	System.out.println(pRoot[0]);

	YcoordinatesGenerator(2*tb.getleafNumber()-2);

	XcoordinatesGenerator(2*tb.getleafNumber()-2, 0.0);

        XcoordinatesAdjustor(tb);
	/*
        System.out.println(xcd[178]);
        System.out.println(xcd[194]);

	System.out.println("CHECK LeftPanelTree" + tdi.getNoOfClusters());
	System.exit(0);
	*/
    }

    void  XcoordinatesAdjustor(njtreeBuilder tb)  {
	double max = 0.0;
        for (int i = 0 ; i < 2*leafNumber-1 ; ++i)  {
	    if (max < xcd[i]) max = xcd[i];
	}
        for (int i = 0 ; i < 2*leafNumber-1 ; ++i)  {
	    xcd[i] = xcd[i]*200.0/max + 50.0;
	    /*  */
            for (int j = 0 ; j < clusterSize ; ++j)  {
		if (l4s.get(j).get_idx() == i)  {
		    System.out.println(" x " + i + "; " + xcd[i]);
		}
	    }
	    /*   */
	}
	
    }

    void  XcoordinatesGenerator(int  id, double x)  {
	xcd[id] = x + BranchLength2AncestralNode[id]*30.0;
	System.out.println(">> id = " + id + " : " + xcd[id]); 
	int k = -2;
        for (int i = 0 ; i < clusterSize ; ++i)  {
	    if (id == l4s.get(i).get_idx())  {
		k = 1;
		break;
	    }
	}	    
	//	if (k > 0 || id < leafNumber)  return;
	if (k > 0)  return;
	else  {
		int id1 = DescendantNodes[id][0]-1;
		int id2 = DescendantNodes[id][1]-1;
		XcoordinatesGenerator(id1, xcd[id]);
		XcoordinatesGenerator(id2, xcd[id]); 
	}
    } 

    double  YcoordinatesGenerator(int  id)  {
	int k = -2;
	for (int i = 0 ; i < clusterSize ; ++i)  {
	    if (id == l4s.get(i).get_idx())  {
		//	        System.out.println("   " + id + "; " + ycd[id]);
		k = 1;
                break;
	    }
	}
	//        if (k > 0 || id < leafNumber)  return  ycd[id];
        if (k > 0)  return  ycd[id];
        else  {
	    System.out.println(" id CHK = " + id);
	    int x = DescendantNodes[id][0]-1;
	    int y = DescendantNodes[id][1]-1;

            ycd[id] = (YcoordinatesGenerator(x) + YcoordinatesGenerator(y))/2.0;
	    /*  */	    System.out.println(" y  " + id + "; " + ycd[id]);
            return  ycd[id];
        }
    }

    class list4Sort {
        private int     idx;
        private double  value;
	private int originalOrder;

	list4Sort(int id, double vl, int ori)  {
	    idx = id;
	    value = vl;
	    originalOrder = ori;
	}

	double  get_Value()  {
	    return value;
	}
        
        int  get_idx()  {
	    return  idx;
	}

        int  get_originalOrder()  {
	    return originalOrder;
	}
    }

    class BubbleSort_l4s {
        BubbleSort_l4s(ArrayList<list4Sort> idl)  {
            for (int i = idl.size()-1 ; i > 0 ; i--)  {
                for (int j = 0 ; j < i ; ++j)  {
                    if (idl.get(j).get_Value() < idl.get(j+1).get_Value())  {
                        list4Sort tmp = idl.get(j);
                        idl.set(j, idl.get(j+1));
                        idl.set(j+1, tmp);
                    }
                }
            }
        }	
    }

    double[]  get_Xcoordinates() {
	return  xcd;
    }

    double[]  get_Ycoordinates() {
	return  ycd;
    }

    int  get_leafNumber()  {
	return leafNumber;
    }

    int[][]  get_DescendantNodes()  {
	return  DescendantNodes;
    }

    int  get_clusterSize()  {
	return   clusterSize;
    }

    ArrayList<Integer> get_idOfClusters()  {
	return  idOfClusters;
    } 

    int[] get_newOrder() {
	return newOrder;
    }
}
