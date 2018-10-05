public class test5  {

    public static void main(String[] args)  {
        double [] idx;

	aaIdx aid = new aaIdx(args[0]);

        System.out.println(aid.getIdxChk());
	idx = aid.getIdx();
	for (int i = 0 ; i < 20 ; ++i)  {
	    System.out.println(idx[i]);
	}
    }
}

