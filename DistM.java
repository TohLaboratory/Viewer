import java.util.*;

public class DistM  implements Cloneable {
    private int Size;
    private int Length;
    private char[] InfSite;
    private int effLen = 0;
    private double[][]  distance;
    private int  MSize;

    DistM()  {
    }

    DistM(readSeq ifseq, int wgm)  {
	System.out.println("Distance : " + wgm);

        MSize = ifseq.getSeqSize();
        InfSite = new char[ifseq.getSeqLen(0)];
	for (int i = 0 ; i < ifseq.getSeqLen(0) ; ++i)  {
            int chk = 0;
            InfSite[i] = ' ';
	    for (int j = 0; j < ifseq.getSeqSize() ; ++j)  {
		if (ifseq.getSeq(j).charAt(i) == '-')  {
		    chk++;
                    break;
		}
	    }
            if (chk != 0)  InfSite[i] = 'X';
            else   effLen++;
	}

	System.out.println("effLen = " + effLen);

        distance = new double[ifseq.getSeqSize()][ifseq.getSeqSize()];
   
        for (int k = 0 ; k < ifseq.getSeqSize()-1 ; ++k)  {
	    distance[k][k] = 0.0;
	    for (int l = k + 1;  l < ifseq.getSeqSize() ; ++l)  {
		int dif = 0;
		    for (int m = 0 ; m < ifseq.getSeqLen(0) ; ++m)  {
                        if (InfSite[m] == 'X')  continue;
			if (ifseq.getSeq(k).charAt(m) != ifseq.getSeq(l).charAt(m))  dif++;
	     }
	     distance[k][l] = (double)dif/(double)effLen;
             if (wgm == 1)  distance[k][l] = -Math.log(1.0-distance[k][l]);
	        distance[l][k] = distance[k][l];
	     }
	 }
	System.out.println("Dist complete");

    }

    public Object clone() {
	try {
	    DistM dmobj = (DistM) super.clone();

	    dmobj.distance = new double[MSize][MSize];
	    for (int i = 0; i < MSize ; ++i)  {
		for (int j = 0 ; j < MSize ; ++j)  {
		    dmobj.distance[i][j] = distance[i][j];
		}
	    }
	    return dmobj;
	}
	catch(CloneNotSupportedException ex) {
	    throw new InternalError();
	}
    }

    char getGapChk(int i)  {
	return InfSite[i];  
    }
    
    double getDistance(int i, int j)  {
	return distance[i][j];
    }

    int getMSize()  {
	return MSize;
    }

    double[][] getDistMtx()  {
	return  distance;
    }

    void  setDistMtx(double[][] mtx, int size)  {
	distance = new double[size][size];
	for (int i = 0 ; i < size-1 ; ++i) {
	    for (int j = 0 ; j < size ; ++j)  {
		distance[i][j] = mtx[i][j];
		distance[j][i] = distance[i][j];
	    }
	}
	MSize = size;
    }
}
