public class test6  {

    public static void main(String[] args)  {
        double [][] idx;

	scoreIdx sid = new scoreIdx(args[0]);

        System.out.println(sid.getIdxChk());
	idx = sid.getIdx();
	for (int i = 0 ; i < 20 ; ++i)  {
	    for (int j = 0 ; j < 20 ; ++j)  {
		System.out.println(idx[i][j]);
	    }
	}
    }
}

