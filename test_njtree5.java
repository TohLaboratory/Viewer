public class test_njtree5  {

    public static void main(String ... args)  {

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
        treeDrawInfo tdi = new treeDrawInfo(tb);
	
    }

}
