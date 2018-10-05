public class test_njtree3  {

    public static void main(String ... args)  {

        double[][] mtx = {{0.0,0.4,0.8,1.0,1.4,1.5},{0.4,0.0,1.0,1.2,1.6,1.7},{0.8,1.0,1.0,0.4,1.6,1.7},{1.0,1.2,0.4,0.0,1.8,1.9},{1.4,1.6,1.6,1.8,0.0,1.7},{1.5,1.7,1.7,1.9,1.7,0.0}};

	DistM dm = new DistM();
        dm.setDistMtx(mtx, 6);

	/*
        for (int i = 1 ; i < 5 ; ++i)  {
	    for (int j = 0 ; j < i ; ++j)  {
		System.out.print(mtx[i][j]+"\t");
	    }
	    System.out.println();
	}
	*/

	njtreeBuilder tb = new njtreeBuilder(dm);
	
    }

}
