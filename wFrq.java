public class wFrq  {
    //    private int [][][] aatype;
    private double[][] Nc;
    private double[][][] Psfq;
    private char[] gSite;
    private String aa1="ARNDCQEGHILKMFPSTWYV";

    wFrq(readSeq ifseq, SeqWeight swg, importCluster clinf, double thr1, int thr2)  {

        Psfq = new double[clinf.getClusterSize()][ifseq.getSeqLen(0)][21];
	Nc = new double[clinf.getClusterSize()][ifseq.getSeqLen(0)];
	//        aatype = new int[clinf.getClusterSize()][ifseq.getSeqLen(0)][20];
        gSite = new char[ifseq.getSeqLen(0)];

	/*
	System.out.println("Cluster Size = " + clinf.getClusterSize());
        for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    System.out.println("Size of Cluster " + i + " = " + clinf.getNumberOfMember().get(i).intValue());
	    for (int j = 0 ; j < clinf.getNumberOfMember().get(i).intValue() ; ++j)  {
		System.out.println(">>" + clinf.getCluster().get(i).get(j).intValue());
		System.out.println(ifseq.getSqAL().get(clinf.getCluster().get(i).get(j).intValue()));
		for (int k = 0 ; k < ifseq.getSeqLen(0) ; ++k) {
		    System.out.println(ifseq.getSqAL().get(clinf.getCluster().get(i).get(j).intValue()).charAt(k) + ":" + AAtoI(ifseq.getSqAL().get(clinf.getCluster().get(i).get(j).intValue()).charAt(k)));
		}
	    }
	}
	*/

        for (int i = 0; i < ifseq.getSeqLen(0) ; ++i)  gSite[i] = ' ';

	for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    int ClusterSize = clinf.getNumberOfMember().get(i).intValue();
	    for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
		int    gapchk = 0;
		for (int k = 0 ; k < ClusterSize ; ++k)  {
		    int idm = clinf.getCluster().get(i).get(k).intValue();
                    int ati = AAtoI(ifseq.getSqAL().get(idm).charAt(j));
		    if (ati < 20)  {
                        if (Psfq[i][j][ati] == 0.0) Nc[i][j] += 1.0; 
			Psfq[i][j][ati] += swg.getSeqWeight(i, k);
		    }
		    else {
			gapchk += 1;
		    }
		}

		if (ClusterSize < thr2)  gSite[j] = 'Y';
		if (((double)gapchk/(double)ClusterSize) >= thr1)  gSite[j] = 'X';
	    }
	}

        for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
            for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
		if (gSite[j] != 'X' || gSite[j] != 'Y')  {
		    double total = 0.0;
		    for (int k = 0 ; k < 20 ; ++k) {
			total += Psfq[i][j][k];
		    }
		    for (int k = 0 ; k < 20 ; ++k) Psfq[i][j][k] /= total;
		}
	    }
	}

	/*	
	for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    System.out.println(">> cluster = " + i);
	    for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
		System.out.println(gSite[j]);
		System.out.println("** site = " + j);
		for (int k = 0 ; k < 20 ; ++k)  {
		    System.out.println(aa1.charAt(k) + " : " + Psfq[i][j][k]);
		    System.out.println(aa1.charAt(k) + " : " + aatype[i][j][k]);
		}
	    }
	}
	*/
    }

    int AAtoI(char aa)  {
	switch(aa)  {
	  case 'A': return 0;  
	  case 'R': return 1;
	  case 'N': return 2;
	  case 'D': return 3;
	  case 'C': return 4;
	  case 'Q': return 5;
	  case 'E': return 6;
	  case 'G': return 7;
	  case 'H': return 8;
	  case 'I': return 9;
	  case 'L': return 10;
	  case 'K': return 11;
	  case 'M': return 12;
	  case 'F': return 13;
	  case 'P': return 14;
	  case 'S': return 15;
	  case 'T': return 16;
	  case 'W': return 17;
	  case 'Y': return 18;
	  case 'V': return 19;
	  default:  return 20;
	}
    }
    
    double  getPsfq(int i, int j, int k)  {
	return (Psfq[i][j][k]);
    }
    
    double[][][] getPsfqAll()  {
	return Psfq;
    }

    double[][]  getNc() {
	return Nc;
    }

    char []  getgSite() {
	return gSite;
    }
}
