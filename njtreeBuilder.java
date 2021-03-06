import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Integer;

public class njtreeBuilder  {
    private int[][] DescendantNodes;
    private int[]   AncestralNodes;
    private double [] BranchLength2AncestralNode;
    private double [] AverageDistance;
    private int [] SizeOfSubcluster;
    private int[][] ClusterMembers0;
    private int[] ClusterMembers0Size;
    private int[][] ClusterMembers1;
    private int[] ClusterMembers1Size;
    private double[] mxDistanceFromLeaf;
    private int leafNumber;
    private int mxid;
    private int c01;
    private int[] nodeName;
    private ArrayList<Integer> cls;
    
    njtreeBuilder(DistM distM, int pRoot)  {
	int msize = distM.getMSize();
	leafNumber = msize;
	DistM cldistM = (DistM) distM.clone();

	ClusterMembers0 = new int[2*leafNumber-1][leafNumber];
	ClusterMembers1 = new int[2*leafNumber-1][leafNumber];
	ClusterMembers0Size = new int[2*leafNumber-1];
	ClusterMembers1Size = new int[2*leafNumber-1];

	double[][] distmtx = distM.getDistMtx();
        DescendantNodes = new int[2*leafNumber - 1][2];
	AncestralNodes = new int[2*leafNumber - 1];
	BranchLength2AncestralNode = new double[2*leafNumber - 1];
	AverageDistance = new double[2*leafNumber - 1];
	SizeOfSubcluster = new int[2*leafNumber - 1];
	mxDistanceFromLeaf = new double[2*leafNumber - 1];
        nodeName = new int [2*leafNumber - 1];
	for (int i = 0 ; i < 2*leafNumber - 1 ; ++i)  {
	    nodeName[i] = i;
	}

	double   minDist = 200.0;
        int      minPair1 = 0;
        int      minPair2 = 0;
	double   branchLength1 = 0.0;
	double   branchLength2 = 0.0;
        double[] minInfo = new double[3];
        int[]    name = new int[leafNumber];
        for (int i = 0 ; i < leafNumber ; ++i)  { 
	    name[i] = i+1;
	    DescendantNodes[i][0] = -1;
	    DescendantNodes[i][1] = -1;
	    SizeOfSubcluster[i] = 1;
	    AverageDistance[i] = 0.0;
	}
	int      nodeNumber = msize+1;

	while (msize >= 3)  {

	/* 1. Detenction of pair with minimal total branch length */
	    for (int i = 0 ; i < msize - 1 ; ++i)  {
		for (int j = i + 1 ; j < msize ; ++j)  {
		    minInfo = Calc_TotalBranchLength(i, j, msize, distmtx);
		    if (minInfo[0] < minDist)  {
			minPair1 = i;
			minPair2 = j;
			minDist = minInfo[0];
			branchLength1 = minInfo[1];
			branchLength2 = minInfo[2];
		    }
		}
	    }

            DescendantNodes[nodeNumber-1][0] = name[minPair1];
            DescendantNodes[nodeNumber-1][1] = name[minPair2];
	    AncestralNodes[name[minPair1]-1] = nodeNumber;
	    AncestralNodes[name[minPair2]-1] = nodeNumber;
	    SizeOfSubcluster[nodeNumber-1] = SizeOfSubcluster[name[minPair1]-1] + SizeOfSubcluster[name[minPair2]-1];
	    BranchLength2AncestralNode[name[minPair1]-1] = branchLength1 - AverageDistance[name[minPair1]-1];
	    BranchLength2AncestralNode[name[minPair2]-1] = branchLength2 - AverageDistance[name[minPair2]-1];
            AverageDistance[nodeNumber-1] = (branchLength1+branchLength2)/2.0;


	    if (msize == 3)  {

		int lastNode = 0;
		for (int i = 0 ; i < msize ; ++i)  {
		    if (i != minPair1 && i != minPair2) {
			lastNode = i;
			break;
		    }
		}
		//		System.out.println(nodeNumber);
		DescendantNodes[nodeNumber][0] = nodeNumber;
		DescendantNodes[nodeNumber][1] = name[lastNode];
		AncestralNodes[nodeNumber-1] = nodeNumber+1;
		AncestralNodes[name[lastNode]-1] = nodeNumber+1;
		SizeOfSubcluster[nodeNumber] = SizeOfSubcluster[nodeNumber-1] + SizeOfSubcluster[name[lastNode]-1];
		BranchLength2AncestralNode[nodeNumber-1] = 0.0;
		BranchLength2AncestralNode[name[lastNode]-1] = (distmtx[lastNode][minPair1]+distmtx[lastNode][minPair2] - distmtx[minPair1][minPair2])/2.0 - AverageDistance[name[lastNode]-1];
		AverageDistance[nodeNumber] = (BranchLength2AncestralNode[name[lastNode]-1] + AverageDistance[nodeNumber-1] + AverageDistance[name[lastNode]]-1)/2.0;
	         
		//	System.out.println("nodeNumber = " + nodeNumber);

		/*
	for (int i = 0 ;i < nodeNumber+1 ; ++i)  {
	    System.out.println(">> i = " + i);
	    System.out.println("   Descendant = " + DescendantNodes[i][0] + " " + DescendantNodes[i][1]);
	    System.out.println("   Ancestor  = " + AncestralNodes[i]);
	    System.out.println("   BranchLength2AncestralNode = " + BranchLength2AncestralNode[i]);
	    System.out.println("   Size of Subcluster = " + SizeOfSubcluster[i]);
	    System.out.println("   AverageDistance = " + AverageDistance[i]);
	}
		*/
	    }

        /* 2. reconstruction of distance matrix */
            if (msize != 3)  {
		double[][] dummymtx = new double[msize-1][msize-1];
		int [] dummyname = new int[msize-1];
                int k = 0;
                for (int i = 0 ; i < msize ; ++i)  {
                    if (i == minPair1 || i == minPair2)  continue;
                    else   dummyname[k] = name[i];
		    k++;
		}
                dummyname[msize-2] = nodeNumber;

		k = 0;
		for (int i = 0 ; i < msize - 1 ; ++i)  {
		    if (i == minPair1 || i == minPair2)  continue;
		    else {
			int l = k+1;
			for (int j = i+1 ; j < msize ; ++j)  {
			    if (j == minPair1 || j == minPair2)  continue;
			    else  {
				dummymtx[k][l] = distmtx[i][j];
				dummymtx[l][k] = dummymtx[k][l];
			    }
			    l++;
			} 
		    } 
		    k++;
		}

		int kk = 0;
                for (int i = 0 ; i < msize ; ++i)  {
		    if (i == minPair1 || i == minPair2)   continue;
		    else  {
			dummymtx[msize-2][kk] = (distmtx[i][minPair1] + distmtx[i][minPair2])/2.0;
			dummymtx[kk][msize-2] = dummymtx[msize-2][kk];
			//			dummyname[kk] = name[i];
			kk++;
		    }
		}
		
		for (int i = 0 ; i < msize - 2 ; ++i)  {
		    for (int j = i + 1 ; j < msize - 1 ; ++j)  {
			distmtx[i][j] = dummymtx[i][j];
			distmtx[j][i] = distmtx[i][j];
		    }
		}

		for (int i = 0 ; i < msize - 1 ; ++i)  {
		    name[i] = dummyname[i];
		}
		
	    }
	    msize--;
            nodeNumber++;
        }

	/* 3. ClusterMembers0, 1の作成 */
	makeClusterMembers(2*leafNumber - 2);

        /* 4. putative root の探索 */

        double mxv = 0.0;
	double bG = 0.0;
	double wG0 = 0.0;
	double wG1 = 0.0;
	double wGo = 0.0;
	int [] otherMem;

        for (int i = leafNumber ; i < leafNumber*2 - 1 ; ++i) {
	    if (ClusterMembers0Size[i]+ClusterMembers1Size[i] == leafNumber)  {
		if (pRoot == 0) {
		    wG0 = withinGroup(cldistM, ClusterMembers0Size[i], ClusterMembers0[i]);
		    wG1 = withinGroup(cldistM, ClusterMembers1Size[i], ClusterMembers1[i]);
		}
		bG = betweenGroup(cldistM, ClusterMembers0Size[i], ClusterMembers0[i], ClusterMembers1Size[i], ClusterMembers1[i]);
		if (pRoot == 0) System.out.println("i = " + i + ", wG0 = " + wG0 + ", wG1 = " + wG1 + ", bG = " + bG + ", ev = " + bG/(wG0 + wG1));
		else System.out.println("i = " + i + ", bG = " + bG);

		if (pRoot == 0) {
		    if (mxv < bG/(wG0 + wG1)) {
			mxv = bG/(wG0+ wG1);
			mxid = i;
			c01=-1;
		    }    
		}
		else  {
		    if (mxv < bG)  {
			mxv = bG;
			mxid = i;
			c01 = -1;
		    }
		} 
	    }
	    else {
		/* 0のクラスタとその他 での比較　*/
		otherMem = otherMembers(leafNumber, ClusterMembers0Size[i], ClusterMembers0[i]);
		bG = betweenGroup(cldistM, ClusterMembers0Size[i], ClusterMembers0[i], leafNumber - ClusterMembers0Size[i], otherMem);
                if (pRoot == 0) {
		    wG0 = withinGroup(cldistM, ClusterMembers0Size[i], ClusterMembers0[i]);
		    wGo = withinGroup(cldistM, leafNumber - ClusterMembers0Size[i], otherMem);
		}


		if (pRoot == 0) System.out.println("i = " + i + ", wG0 = " + wG0 + ", wGo = " + wGo + ", bG = " + bG + ", ev = " + bG/(wG0 + wGo));		
		else  System.out.println("i = " + i + ", bG = " + bG);

		if (pRoot == 0) {
		    if (mxv < bG/(wG0 + wGo)) {
			mxv = bG/(wG0+ wGo);
			mxid = i;
			c01=0;    
		    }
		}
		else {
		    if (mxv < bG) {
			mxv = bG;
			mxid = i;
			c01=0;    
		    }
		}

		/* 1のクラスタとその他 での比較  */
		otherMem = otherMembers(leafNumber, ClusterMembers1Size[i], ClusterMembers1[i]);
		bG = betweenGroup(cldistM, ClusterMembers1Size[i], ClusterMembers1[i], leafNumber - ClusterMembers1Size[i], otherMem);
		if (pRoot == 0)    {
		    wG1 = withinGroup(cldistM, ClusterMembers1Size[i], ClusterMembers1[i]);
		    wGo = withinGroup(cldistM, leafNumber - ClusterMembers1Size[i], otherMem);
		}


		if (pRoot == 0) System.out.println("i = " + i + ", wG1 = " + wG1 + ", wGo = " + wGo + ", bG = " + bG + ", ev = " + bG/(wG1 + wGo));
		else System.out.println("i= " + i + ", bG = " + bG);

		if (pRoot == 0) {
		    if (mxv < bG/(wGo + wG1)) {
			mxv = bG/(wGo + wG1);
			mxid = i;
			c01=1;    
		    }		
		}
		else {
		    if (mxv < bG) {
			mxv = bG;
			mxid = i;
			c01=1;    
		    }

		}
	    }
	}

	System.out.println(">> mxid =" + mxid + ", c01 = " + c01 + ", mxv = " + mxv);
	if (c01 == 0) {
	    System.out.println("  Cluster Size = " + ClusterMembers0Size[mxid]);
	    for (int i = 0 ; i < ClusterMembers0Size[mxid] ; ++i)  {
		System.out.print(ClusterMembers0[mxid][i]+" ");
	    }
	    System.out.println();
	}
	else {
	    System.out.println("  Cluster Size = " + ClusterMembers1Size[mxid]);
	    for (int i = 0 ; i < ClusterMembers1Size[mxid] ; ++i)  {
		System.out.print(ClusterMembers1[mxid][i]+" ");
	    }
	    System.out.println();
	}

	/* 
        mxid = 9;
        System.out.println(">> root info");
	System.out.println("  0 = " + DescendantNodes[2*leafNumber-2][0] + ", 1 = " + DescendantNodes[2*leafNumber-2][1] + ", mxid = " + mxid);
	 */

	reRoot(mxid);

	makeClusterMembers(2*leafNumber - 2);

	for (int i = 0 ; i < 2*leafNumber - 1 ; ++i)  {
	    System.out.println(">> node = " + i);
            System.out.println(">>>>> 0");
	    for (int j = 0 ; j < ClusterMembers0Size[i] ; ++j) {
		System.out.print("  " + ClusterMembers0[i][j] + " ");	    
	    }
	    System.out.println();
            System.out.println(">>>>> 1");
	    for (int j = 0 ; j < ClusterMembers1Size[i] ; ++j) {
		System.out.print("  " + ClusterMembers1[i][j] + " ");
	    }
	    System.out.println();
	}
	
	makemxDistanceFromLeaf(2*leafNumber - 2);
	/*
	ArrayList<Integer> clusterList = ClusteringFromASpecifiedNode(11, 5);
	for (int i = 0 ; i < clusterList.size() ; ++i)  {
	    System.out.println("#***# = " + clusterList.get(i).intValue());
	}
	*/
    }

    public class DescendantNode {
	private int  memberID;
	private double distFromLeaf;

	DescendantNode(int id, double dist) {
	    this.memberID = id;
	    this.distFromLeaf = dist;
	}

	int getID() {
	    return  memberID;
	}
	double getDist() {
	    return  distFromLeaf;
	}
    }

    public class getDescendants {
	ArrayList<DescendantNode> dNodes = new ArrayList<DescendantNode>();
	/*
	ArrayList<Integer> Members = new ArrayList<Integer>();
	ArrayList<Double> distanceFromLeaf = new ArrayList<Double>();
	*/
	getDescendants(int idx) {
	    TreeSeek(idx);
	}

	void TreeSeek(int idx) {
	    /*
	    Members.add(idx);
	    distanceFromLeaf.add(mxDistanceFromLeaf[idx]);
	    */
	    dNodes.add(new DescendantNode(idx, mxDistanceFromLeaf[idx]));
	    if (DescendantNodes[idx][0] == -1 && DescendantNodes[idx][1] == -1)  return;
	    else {  
		TreeSeek(DescendantNodes[idx][0]-1);
		TreeSeek(DescendantNodes[idx][1]-1);
	    }
	}	
        ArrayList<DescendantNode> getDec() {
	    return  dNodes;
	}
        /*
        ArrayList<Double> getDist() {
	    return  distanceFromLeaf;
	}
	*/
    }

    /*
    public  class  DescendantComparator implements Comparator<DescendantNode>  {
	@Override
	public  int  compare(DescendantNode d1, DescendantNode d2)  {
	    return d1.getDist() > d2.getDist() ? -1 : 1;
	}
    }
    */

    ArrayList<Integer>   ClusteringFromASpecifiedNode(int startNode, int maxNumberOfClusters)  {
	ArrayList<Integer> clusterList = new ArrayList<Integer>();
        int[] clusterID1 = new int[leafNumber*2-1];
        int[] clusterID2 = new int[leafNumber*2-1];
       
	/* 1. startNodeの子孫節とそのmxDistanceFromLeafを配列にまとめる */
	int SizeOfMembers = 2*(ClusterMembers0Size[startNode]+ClusterMembers1Size[startNode])-1;

	//	System.out.println("#**# SizeOfMembers = " + SizeOfMembers);

        getDescendants descendantSet = new getDescendants(startNode);

        /*  */
	//        System.out.println("###>>" + descendantSet.getDec().size());
	/*
	for (int i = 0 ; i < descendantSet.getDec().size() ; ++i){
	    System.out.println("i =" + i + ", " + descendantSet.getDec().get(i).getID() + ", " + descendantSet.getDec().get(i).getDist());
	}	
	*/
	/*  */
	/* 2. ArrayListをソートする */

	//	Collections.sort(descendantSet.getDec(), new DescendantComparator());

	//	DescendantListQuickSort arqsort = new DescendantListQuickSort(descendantSet.getDec());

	DescendantListBubbleSort arbsort = new DescendantListBubbleSort(descendantSet.getDec());

	/*  
        System.out.println("S##>>" + descendantSet.getDec().size());
	for (int i = 0 ; i < descendantSet.getDec().size() ; ++i){
	    System.out.println("i =" + i + ", " + descendantSet.getDec().get(i).getID() + ", " + descendantSet.getDec().get(i).getDist());
	}
	//	System.exit(1);
	  */

	/* 3. クラスタを見つける */
	if (maxNumberOfClusters == 2)  {
	    clusterList.add(DescendantNodes[startNode][0]-1);
	    clusterList.add(DescendantNodes[startNode][1]-1);
	}  
        else {
            int k = 0;
	    int n = 0;
	    for (int i = 1 ; i < descendantSet.getDec().size()-1 ; ++i)  {
	    
		double branchLen = descendantSet.getDec().get(i).getDist();	    
		if (DescendantNodes[descendantSet.getDec().get(i).getID()][0] == -1)  {
		    clusterID1[k++] = descendantSet.getDec().get(i).getID();
		}
	        int l = 0;
		for (int j = i+1 ; j < descendantSet.getDec().size() ; ++j) {
		     int idx = descendantSet.getDec().get(j).getID();
		     int didx = AncestralNodes[idx]-1;
		     if (mxDistanceFromLeaf[didx] >= branchLen)  clusterID2[l++] = idx;
		}	
		if ((l+k) <= maxNumberOfClusters || ((l+k) > maxNumberOfClusters && n == 0)) {
		    clusterList.clear();
		    for (int m = 0 ; m < k ; ++m)  {
			clusterList.add(clusterID1[m]);
		    }
		    for (int m = 0 ; m < l ; ++m)  {
			clusterList.add(clusterID2[m]);
		    }
		    n++;
		}
		else break;
	    }    
	}

	return clusterList;
    }

    void  nodeSortByDistanceFromLeaf(int left, int right)   {
	if (left >= right)  return;
        double p = mxDistanceFromLeaf[(left+right)/2];
	int l = left, r = right, tmp1;
	double  tmp2;
	while(l <= r) {
	    while(mxDistanceFromLeaf[l] < p)  { l++; }
	    while(mxDistanceFromLeaf[r] > p)  { r--; }
	    if (l <= r) {
                tmp1 = nodeName[l];
		tmp2 = mxDistanceFromLeaf[l];
		mxDistanceFromLeaf[l] = mxDistanceFromLeaf[r];
		nodeName[l] = nodeName[r];
		mxDistanceFromLeaf[r] = tmp2;
		nodeName[r] = tmp1;
		l++;
		r--;
	    }
	}
	nodeSortByDistanceFromLeaf(left, r);
	nodeSortByDistanceFromLeaf(l, right);
    }
    void  makemxDistanceFromLeaf(int nodeidx)  {
        if (nodeidx < leafNumber)  {
	    mxDistanceFromLeaf[nodeidx] = 0.0;   
	    return;
	}
        else {
	    makemxDistanceFromLeaf(DescendantNodes[nodeidx][0]-1);
	    makemxDistanceFromLeaf(DescendantNodes[nodeidx][1]-1);
	    mxDistanceFromLeaf[nodeidx] = Math.max(BranchLength2AncestralNode[DescendantNodes[nodeidx][0]-1] + mxDistanceFromLeaf[DescendantNodes[nodeidx][0]-1], BranchLength2AncestralNode[DescendantNodes[nodeidx][1]-1] + mxDistanceFromLeaf[DescendantNodes[nodeidx][1]-1]);
	    System.out.println("mAvd[" + nodeidx + "] = " + mxDistanceFromLeaf[nodeidx]);
	    System.out.println(" n0 = " + DescendantNodes[nodeidx][0]);
	    System.out.println(" n1 = " + DescendantNodes[nodeidx][1]);
	    System.out.println(" B0 = " + BranchLength2AncestralNode[DescendantNodes[nodeidx][0]-1]);
	    System.out.println(" B1 = " + BranchLength2AncestralNode[DescendantNodes[nodeidx][1]-1]);
	    System.out.println(" A0 = " + mxDistanceFromLeaf[DescendantNodes[nodeidx][0]-1]);
	    System.out.println(" A1 = " + mxDistanceFromLeaf[DescendantNodes[nodeidx][1]-1]);

	    return;
	}
    }

    void  makeAverageDistance(int nodeidx)  {
        if (nodeidx < leafNumber)  {
	    AverageDistance[nodeidx] = 0.0;   
	    return;
	}
        else {
	    makeAverageDistance(DescendantNodes[nodeidx][0]-1);
	    makeAverageDistance(DescendantNodes[nodeidx][1]-1);
	    AverageDistance[nodeidx] = (BranchLength2AncestralNode[DescendantNodes[nodeidx][0]-1] + BranchLength2AncestralNode[DescendantNodes[nodeidx][1]-1] + AverageDistance[DescendantNodes[nodeidx][0]-1]+AverageDistance[DescendantNodes[nodeidx][1]-1])/2.0;
	    /*
	    System.out.println("mAvd[" + nodeidx + "] = " + AverageDistance[nodeidx]);
	    System.out.println(" n0 = " + DescendantNodes[nodeidx][0]);
	    System.out.println(" n1 = " + DescendantNodes[nodeidx][1]);
	    System.out.println(" B0 = " + BranchLength2AncestralNode[DescendantNodes[nodeidx][0]-1]);
	    System.out.println(" B1 = " + BranchLength2AncestralNode[DescendantNodes[nodeidx][1]-1]);
	    System.out.println(" A0 = " + AverageDistance[DescendantNodes[nodeidx][0]-1]);
	    System.out.println(" A1 = " + AverageDistance[DescendantNodes[nodeidx][1]-1]);
	    */
	    return;
	}
    }

    void reRoot(int mxid) {
        int rnode0;
	int rnode1;
        int xnode;

        if ((mxid+1) != DescendantNodes[2*leafNumber-2][0] && (mxid + 1) != DescendantNodes[2*leafNumber-2][1] && mxid != (2*leafNumber -2))  {  
	/* 系統樹の再構築 */
	/* 1. 現在のルート情報の記憶 */
	    rnode0 = DescendantNodes[2*leafNumber-2][0];
	    rnode1 = DescendantNodes[2*leafNumber-2][1];
	    System.out.println(">> rnode0 = " + rnode0 + ", rnode1 = " + rnode1);

	/* 2. ルート情報の再構成 */
	    int[][] dDescendantNodes = new int[2*leafNumber-1][2];
	    int[]   dAncestralNodes = new int[2*leafNumber-1];
	    double[]  dBranchLength2AncestralNode = new double[2*leafNumber-1];
	    for (int i = 0 ; i < 2*leafNumber-1 ; ++i)  {
		dDescendantNodes[i][0] = DescendantNodes[i][0]; 
		dDescendantNodes[i][1] = DescendantNodes[i][1];
		dAncestralNodes[i] = AncestralNodes[i];
		dBranchLength2AncestralNode[i] = BranchLength2AncestralNode[i];
	    }

	    dDescendantNodes[2*leafNumber-2][0] = mxid + 1;
	    int pnode = AncestralNodes[mxid];
	    dDescendantNodes[2*leafNumber-2][1] = pnode;
	    dAncestralNodes[mxid] = 2*leafNumber-1;
	    dAncestralNodes[pnode-1] = 2*leafNumber-1;
	    double pbranchLength1 = BranchLength2AncestralNode[mxid];
	    dBranchLength2AncestralNode[mxid] = 0.0;
	    dBranchLength2AncestralNode[pnode-1] = pbranchLength1;
	    double pbranchLength2 = 0.0;
	    int cnode = mxid + 1;
	    int idx;

	/* 3. 全体の再構成 */

	    while (cnode != rnode0 && cnode != rnode1)  {
		System.out.println("cnode = " + cnode);
		pbranchLength2 = BranchLength2AncestralNode[pnode-1];
		dBranchLength2AncestralNode[pnode-1] = pbranchLength1;
		pbranchLength1 = pbranchLength2;
		idx = AncestralNodes[pnode-1];
		for (int i = 0 ; i < 2 ; ++i)  {
		    if (DescendantNodes[pnode-1][i] == cnode) {
			dDescendantNodes[pnode-1][i] = idx;
			break;
		    }
		}
		if (cnode != (mxid+1)) dAncestralNodes[pnode-1] = cnode;
		else  dAncestralNodes[pnode-1] = 2*leafNumber - 1;
		cnode = pnode;
		pnode = idx;

	    }

	    if (cnode == rnode0) {
		System.out.println("rnode0");
		dAncestralNodes[rnode1 - 1] = rnode0;
		for (int i = 0 ; i < 2 ; ++i)  {
		    if (DescendantNodes[rnode0-1][i] != dDescendantNodes[rnode0-1][i]) {
			dDescendantNodes[rnode0-1][i] = rnode1;
			break;
		    }  
		}
		dBranchLength2AncestralNode[rnode1-1] = BranchLength2AncestralNode[rnode1-1];
	    }
            else if (cnode == rnode1)  {
		System.out.println("rnode1");
		dAncestralNodes[rnode0-1] = rnode1;
		dBranchLength2AncestralNode[rnode0-1] = BranchLength2AncestralNode[rnode1-1];
		for (int i = 0 ; i < 2 ; ++i) {
		    if (dDescendantNodes[rnode1-1][i] != DescendantNodes[rnode1-1][i]) {
			dDescendantNodes[rnode1-1][i] = rnode0;
		    }
		}
		//		dDescendantNodes[rnode0-1][0] = rnode1;
	    }
            else {
		System.out.println("Something is Wrong in Reconstruction!!");
	    }

	    for (int i = 0 ; i < leafNumber*2 - 1 ; ++i)  {
		System.out.println(">> i = " + i);  
		System.out.println("   dAncestror = " + dAncestralNodes[i]);
		System.out.println("   Ancestror = " + AncestralNodes[i]);
		System.out.println("   dBranchLength = " + dBranchLength2AncestralNode[i]);
		System.out.println("   BranchLength = " + BranchLength2AncestralNode[i]);
		System.out.println("   dDescendants = " + dDescendantNodes[i][0] + ", " + dDescendantNodes[i][1]);
		System.out.println("   Descendants = " + DescendantNodes[i][0] + ", " + DescendantNodes[i][1]);
		/*  */
		DescendantNodes[i][0] = dDescendantNodes[i][0];
		DescendantNodes[i][1] = dDescendantNodes[i][1];
		AncestralNodes[i] = dAncestralNodes[i];
		BranchLength2AncestralNode[i] = dBranchLength2AncestralNode[i];
		/*   */
	    }

	}

    }

    int[][]  Get_DescendantNodes()  {
	return  DescendantNodes;
    }

    double[]  Calc_TotalBranchLength(int a, int b, int msize, double[][] distmtx)  {
	double[] minInfo = new double[3];

        minInfo[0] = distmtx[a][b]/2.0;
        for (int i = 0 ; i < msize ; ++i)  {
            if (i == a || i == b)  continue;
	    else  { 
                minInfo[1] += distmtx[a][i]/(msize - 2.0);
		minInfo[2] += distmtx[b][i]/(msize - 2.0);
                minInfo[0] += distmtx[a][i]/2.0/(msize - 2.0);
	        minInfo[0] += distmtx[b][i]/2.0/(msize - 2.0);
	    }
	}
        for (int i = 0 ; i < msize-1 ; ++i)  {
	    if (i == a || i == b)   continue;
            else  {
		for (int j = i + 1 ; j < msize ; ++j)  {
		    if (j == a || j == b)  continue;
		    else minInfo[0] += distmtx[i][j]/(msize - 2.0);
		}
	    }
	}

        double dummyL1 = minInfo[1];
        double dummyL2 = minInfo[2];
        minInfo[1] = (distmtx[a][b] + dummyL1 - dummyL2)/2.0;
        minInfo[2] = (distmtx[a][b] + dummyL2 - dummyL1)/2.0;

	return minInfo;
    }

    int  getleafNumber()  {
	return(leafNumber);
    }  

    int[][] getDescendantNodes()  {
	return(DescendantNodes);
    }

    int [] getAncestralNodes() {
	return(AncestralNodes);
    }
   
    double[]  getBranchLength2AN()  {
	return (BranchLength2AncestralNode);
    }

    double[]  getAverageDistnce()  {
	return(AverageDistance);
    }

    int[] getSizeOfCluster()  {
	return(SizeOfSubcluster);
    }

    void makeClusterMembers(int nodeid)  {

	int x = DescendantNodes[nodeid][0] - 1; 
	int y = DescendantNodes[nodeid][1] - 1;
	System.out.println(">> " + nodeid + ", x = " + x + ", y = " + y);
	if (x == -2 && y == -2)  {
	    ClusterMembers0[nodeid][0]=nodeid;
	    ClusterMembers1[nodeid][0]=-1;
	    ClusterMembers0Size[nodeid] = 1;
	    ClusterMembers1Size[nodeid] = 1;
	    return;
	}
	else {
	    makeClusterMembers(x);
	    makeClusterMembers(y);
	    int j = 0;
	    for (int i = 0 ; i < ClusterMembers0Size[x]; ++i)  ClusterMembers0[nodeid][j++] = ClusterMembers0[x][i];
	    for (int i = 0 ; i < ClusterMembers1Size[x]; ++i)  {
		if (ClusterMembers1[x][i] != -1) ClusterMembers0[nodeid][j++] = ClusterMembers1[x][i];
	    }
	    ClusterMembers0Size[nodeid] = j;

	    j = 0;
	    for (int i = 0 ; i < ClusterMembers0Size[y]; ++i)  ClusterMembers1[nodeid][j++] = ClusterMembers0[y][i];
	    for (int i = 0 ; i < ClusterMembers1Size[y]; ++i)  {
		if (ClusterMembers1[y][i] != -1) ClusterMembers1[nodeid][j++] = ClusterMembers1[y][i];
	    }
	    ClusterMembers1Size[nodeid] = j;				
	    return;
	}
    }

    int[][] getClusterMembers(int id)  {
	if (id == 0)   return ClusterMembers0;   
	else if (id == 1)  return ClusterMembers1;
	else    { 
	    System.out.println("Wrong argument!!");
	    return null;
	}
    }

    int[]  getClusterMembersSize(int id)  {
	if (id == 0)  return  ClusterMembers0Size;
	else if (id == 1)  return  ClusterMembers1Size;
	else   {
	    System.out.println("Wrong argument");
	    return null;
	}
    }

    double withinGroup(DistM distM, int ClusterSize, int[] Cluster)  {
	double wGaverageDistance = 0.0;
	double[][] distmtx = distM.getDistMtx();

	/*
	System.out.println(">> ln = " + leafNumber);
	for (int i = 0 ; i < leafNumber ; ++i)  {
	    for (int j = 0 ; j < leafNumber ; ++j)  { 
		System.out.print(distmtx[i][j]+" ");
	    }
	    System.out.println();
	}
	*/

        int  num = 0;

	if (ClusterSize != 1)  {
	    for (int i = 0 ; i < ClusterSize-1 ; ++i)  {
		for (int j = i+1 ; j < ClusterSize ; ++j)  {
		    //		    System.out.println("cl[i] = "+Cluster[i]+", cl[j] = "+Cluster[j]+", dist = "+ distmtx[Cluster[i]][Cluster[j]]);
		    wGaverageDistance += distmtx[Cluster[i]][Cluster[j]];
		    ++num;
		}
	    }

	    //	    wGaverageDistance /= num;
	}
	//	else wGaverageDistance = BranchLength2AncestralNode[Cluster[0]];
	else wGaverageDistance = 0.0;

	return wGaverageDistance;
    }

    double betweenGroup(DistM distM, int clusterSize1, int[] Cluster1, int clusterSize2, int[] Cluster2) {
	double bGaverageDistance = 0.0;
	double[][] distmtx = distM.getDistMtx();

	int num = 0;

        for (int i = 0 ; i < clusterSize1 ; ++i)  {
	    for (int j = 0 ; j < clusterSize2 ; ++j)  {
		bGaverageDistance += distmtx[Cluster1[i]][Cluster2[j]];
		++num;
	    }
	}

	//	bGaverageDistance /= num;

	return bGaverageDistance;
    }

    int[] otherMembers(int total, int clusterSize, int[] Cluster) {
        int[]  otherMem = new int [leafNumber-clusterSize];

        int chk;
        int idx = 0;
        for (int i = 0 ; i < leafNumber ; ++i) {
	    chk = 0;
            for (int j = 0 ; j < clusterSize ; ++j)  {
		if (i == Cluster[j])  chk++;
	    }
	    if (chk == 0)  otherMem[idx++] = i;
	}

	return otherMem;
    }

    int[]  getputativeRoot() {
	int pRoot[] = new int[2];

	pRoot[0] = mxid;
	pRoot[1] = c01; 

	return pRoot;
    }

    void getMembers(int idx, ArrayList<Integer> memberList)  {
	if (DescendantNodes[idx][0] == -1 && DescendantNodes[idx][1] == -1)  {
	    return;
	}
	else {
	    memberList.add(DescendantNodes[idx][0]-1);
	    memberList.add(DescendantNodes[idx][1]-1);
	    getMembers(DescendantNodes[idx][0]-1, memberList);
	    getMembers(DescendantNodes[idx][1]-1, memberList);
	}
    }

    /*
    class DescendantListQuickSort  {
	private ArrayList<DescendantNode> dList;

	DescendantListQuickSort(ArrayList<DescendantNode> descendantList)  {
	    this.dList = descendantList;
	    for (int i = 0 ; i < dList.size() ; ++i)  {
		System.out.println(">> i = " + i + ", " + dList.get(i).getID()+", "+ dList.get(i).getDist());
	    }
	    System.out.println(dList.size());
	    //	    System.exit(1);	
	    quick_sort(0, dList.size()-1);
	}
    
	void quick_sort(int left, int right)  {
	    double p = dList.get((left+right)/2).getDist();
	    System.out.println(left+", "+right+", "+ p + "<<");
	    int l = left, r = right;
	    System.out.println(l + " " + r);
	    DescendantNode tmp;
	    while(l <= r)  {
		while(dList.get(l).getDist() < p) { l++; }
		while(dList.get(r).getDist() > p) { r--; }
		System.out.println("w " + l + ", " + r);
		if (l <= r)  {
		    tmp = dList.get(l);
		    dList.set(l, dList.get(r));
		    dList.set(r, tmp);
		    l++;
		    r--;
		} 		
	    }
	    quick_sort(left, r);
	    quick_sort(l, right);
	}
    }
    */

    public class DescendantListBubbleSort { 
	DescendantListBubbleSort(ArrayList<DescendantNode> dList) {
	    //	    System.out.println("<< " + dList.size());
	    for (int i = dList.size()-1 ; i > 0 ; i--)  {
		for (int j = 0 ; j < i ; ++j)  {
		    //    System.out.println("i = " + i +", j = " + j);
		    if (dList.get(j).getDist() <  dList.get(j+1).getDist()) {
			DescendantNode tmp = dList.get(j);
			dList.set(j, dList.get(j+1));
			dList.set(j+1, tmp);
		    }
		    else if (dList.get(j).getDist() == dList.get(j+1).getDist()) {
			if (dList.get(j).getID() < dList.get(j+1).getID()) {
			    DescendantNode tmp = dList.get(j);
			    dList.set(j, dList.get(j+1));
			    dList.set(j+1, tmp);
			}
		    }
		}
	    }
	}
    }   
}

