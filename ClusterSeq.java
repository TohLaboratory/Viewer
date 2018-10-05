import java.lang.StringBuilder;
import java.util.ArrayList;

public class ClusterSeq {
    private  ArrayList<StringBuilder> cSeq = new ArrayList<StringBuilder>();
    private  int [][] mxIdx;
    private  double[][][] Freq;
    private  String aa = "LIMVSPTAGDENQRKHFYWC-";

    public ClusterSeq(readSeq ifseq, importCluster clinf, int m) {
	mxIdx = new int[clinf.getClusterSize()][ifseq.getSeqLen(0)];
	Freq = new double[clinf.getClusterSize()][21][ifseq.getSeqLen(0)];
        for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    for (int j = 0 ; j < 21 ; ++j)  {
		for (int k = 0 ; k < ifseq.getSeqLen(0) ; ++k)  {
		    Freq[i][j][k] = 0.0;
		}
	    }
	}
        switch(m)  {
	   case 0:  noWeight(ifseq, clinf);
	            break;
	   default: break;
	}
    }

    public void noWeight(readSeq ifseq, importCluster clinf) {
	double total;

	//        System.out.println(clinf.getClusterSize());
	for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	        ArrayList<Integer> clf = clinf.getCluster().get(i);
		//		System.out.println(clf);
		for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
		    total = 0;
		    for (int k = 0 ; k < clinf.getNumberOfMember().get(i); ++k) {
			Integer iclf = clf.get(k);
                        int icidx = iclf.intValue();
			System.out.println(">> " + i + "; " +  k + " = " + icidx); /* CHK */
			switch (ifseq.getSeq(icidx).charAt(j)) {
			case 'L': Freq[i][0][j] += 1.0;
			          total++;
			          break;
			case 'l': Freq[i][0][j] += 1.0;
			          total++;
			          break;
			case 'I': Freq[i][1][j] += 1.0;
			          total++;
			          break;
			case 'i': Freq[i][1][j] += 1.0;
			          total++;
			          break;
			case 'M': Freq[i][2][j] += 1.0;
			          total++;
			          break;
			case 'm': Freq[i][2][j] += 1.0;
			          total++;
			          break;
			case 'V': Freq[i][3][j] += 1.0;
			          total++;
			          break;
			case 'v': Freq[i][3][j] += 1.0;
			          total++;
			          break;
			case 'S': Freq[i][4][j] += 1.0;
			          total++;
			          break;
			case 's': Freq[i][4][j] += 1.0;
			          total++;
			          break;
			case 'P': Freq[i][5][j] += 1.0;
			          total++;
			          break;
			case 'p': Freq[i][5][j] += 1.0;
			          total++;
			          break;
			case 'T': Freq[i][6][j] += 1.0;
			          total++;
			          break;
			case 't': Freq[i][6][j] += 1.0;
			          total++;
			          break;
			case 'A': Freq[i][7][j] += 1.0;
			          total++;
			          break;
			case 'a': Freq[i][7][j] += 1.0;
			          total++;
			          break;
			case 'G': Freq[i][8][j] += 1.0;
			          total++;
			          break;
			case 'g': Freq[i][8][j] += 1.0;
			          total++;
			          break;
			case 'D': Freq[i][9][j] += 1.0;
			          total++;
			          break;
			case 'd': Freq[i][9][j] += 1.0;
			          total++;
			          break;
			case 'E': Freq[i][10][j] += 1.0;
			          total++;
			          break;
			case 'e': Freq[i][10][j] += 1.0;
			          total++;
			          break;
			case 'N': Freq[i][11][j] += 1.0;
			          total++;
			          break;
			case 'n': Freq[i][11][j] += 1.0;
			          total++;
			          break;
			case 'Q': Freq[i][12][j] += 1.0;
			          total++;
			          break;
			case 'q': Freq[i][12][j] += 1.0;
			          total++;
			          break;
			case 'R': Freq[i][13][j] += 1.0;
			          total++;
			          break;
			case 'r': Freq[i][13][j] += 1.0;
			          total++;
			          break;
			case 'K': Freq[i][14][j] += 1.0;
			          total++;
			          break;
			case 'k': Freq[i][14][j] += 1.0;
			          total++;
			          break;
			case 'H': Freq[i][15][j] += 1.0;
			          total++;
			          break;
			case 'h': Freq[i][15][j] += 1.0;
			          total++;
			          break;
			case 'F': Freq[i][16][j] += 1.0;
			          total++;
			          break;
			case 'f': Freq[i][16][j] += 1.0;
			          total++;
			          break;
			case 'Y': Freq[i][17][j] += 1.0;
			          total++;
			          break;
			case 'y': Freq[i][17][j] += 1.0;
			          total++;
			          break;
			case 'W': Freq[i][18][j] += 1.0;
			          total++;
			          break;
			case 'w': Freq[i][18][j] += 1.0;
			          total++;
			          break;
			case 'C': Freq[i][19][j] += 1.0;
			          total++;
			          break;
			case 'c': Freq[i][19][j] += 1.0;
			          total++;
			          break;
			case '-': Freq[i][20][j] += 1.0;
			          total++;
			          break;
			}
		    }
		    for (int l = 0 ; l < 21 ; ++l)  {
			Freq[i][l][j] /= total;
		    }
		}		       
	}

	for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    StringBuilder stb = new StringBuilder();
	    for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
                double mx = 0.0;
		int idx = 22;
		for (int k = 0 ; k < 21 ; ++k)  {
		    if (Freq[i][k][j] > mx)  {
			mx = Freq[i][k][j];
			idx = k;
		    }
		}
		mxIdx[i][j] = idx;
		stb.append(aa.charAt(idx));
	    }
	    //	    System.out.println(stb);
	    cSeq.add(stb);
	}
    }

    ArrayList<StringBuilder> getcSeq()  {
	return cSeq;
    }

    double[][][] getFreq()  {
	return Freq;
    }
    int [][] getmxIdx()  {
	return mxIdx;
    }
    
}
