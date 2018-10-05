public class test7  {

    public static void main(String[] args)  {
        double [] idx1;
        double [][] idx2;

	aaIdx aid = new aaIdx(args[0]);

	scoreIdx sid = new scoreIdx(args[1]);

	idx1 = aid.getIdx();

	idx2 = sid.getIdx();

	double x = 0.0;
	for (int i = 0 ; i < 20 ; ++i)  {
	    for (int j = i ; j < 20 ; ++j)  {
                x += idx1[i]*idx1[j]*idx2[i][j];
	    }
	}

	System.out.println(x);
    }
}

