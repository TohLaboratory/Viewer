public class test  {

    public static void main(String ... args)  {
	System.out.println(args[0]);
	//	System.exit(0);
        importClustal claln = new importClustal(args[0]);
	
	System.out.println("seqSize = " + claln.getSeqSize());
        System.out.println("seqLength = " + claln.getSeqLen(0));
        System.out.println("name0 = " + claln.getName(0));
	//        System.out.println("seq0 = " + claln.getSeq(0));
        System.out.println("name1 = " + claln.getName(1));
	//        System.out.println("seq1 = " + claln.getSeq(1));

	String[] seq = new String[claln.getSeqSize()];
	for (int i = 0 ; i < claln.getSeqSize() ; ++i)  {
	    seq[i] = claln.getSeq(i);
	}
        System.out.println();
        System.out.println();
        int i, j, k, End;
        for (i = 0 ; i < claln.getSeqLen(0) ; i += 60)  {
	    End = i + 60;
            if (End > claln.getSeqLen(0))  End = claln.getSeqLen(0);
	    for (j = 0 ; j < claln.getSeqSize() ; ++j)  {
		for (k = i ; k < End ; ++k)  System.out.print(seq[j].charAt(k));
		System.out.println();
	    }
	    System.out.println();
	}
    }
}
