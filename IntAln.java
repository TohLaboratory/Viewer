public class IntAln {
    private String AA = "ARNDCQEGHILKMFPSTWYV";
    private int[][] intAln;
 
    IntAln(readSeq ifseq)  {
	intAln = new int[ifseq.getSeqSize()][ifseq.getSeqLen(0)];

	for (int i = 0 ; i < ifseq.getSeqSize() ; ++i)  {
	    String Seq = ifseq.getSeq(i);
	    for (int j = 0 ; j < ifseq.getSeqLen(i) ; ++j) {
		int l = -1;
		for (int k = 0 ; k < 20 ; ++k)  {
		    if (Seq.charAt(j) == AA.charAt(k)) {
			intAln[i][j] = k;
			l++;
			break;
		    } 
		}
		if (l == -1) intAln[i][j] = -1;
	    }
	}  	
    }
   
    int getAA(int i, int j)   {
	return intAln[i][j];
    }
} 
