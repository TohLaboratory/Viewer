public class test_njtree  {

    public static void main(String ... args)  {

        double[][] mtx = {{0.0,0.6,1.4,1.3,1.0},{0.6,0.0,1.8,1.7,1.4},{1.4,1.8,0.0,0.5,0.6},{1.3,1.7,0.5,0.0,0.5},{1.0,1.4,0.6,0.5,0.0}};

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

	njtreeBuilder tb = new njtreeBuilder(dm,0);
        int iSize = tb.getiSize();
	System.out.println("iSize = " + iSize);
	
    }

}
