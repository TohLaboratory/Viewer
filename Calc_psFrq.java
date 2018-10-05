public class Calc_psFrq {
    private double[][][] Frq;

    public Calc_psFrq(double lambda, double[][][] Psfq, double[][] scoreMtx, double[] aaindex, double[][] Nc, char[] gSite, importCluster clinf, int Len, double beta)  {
	double[][] q = new double[20][20];
        int nSites;
        double alpha;

	Frq = new double[clinf.getClusterSize()][Len][20];

	for (int i = 0 ; i < 20 ; ++i)  {
	    for (int j = i ; j < 20 ; ++j)  {
		q[i][j] = aaindex[i] * aaindex[j] * Math.exp(lambda * scoreMtx[i][j]);
		if (i != j)  q[j][i] = q[i][j];
	    }
	}

	double[] g = new double[20];
	for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    double mnc = 0.0;
            nSites = 0;       
            for (int j = 0 ; j < Len ; ++j)  {
		if (gSite[j] != ' ')  continue;
		else  {
		    //		    System.out.println(i + ":" + j + ":" + Nc[i][j]);
		    mnc += Nc[i][j];
		    nSites++;
		}
	    } 
            alpha = mnc/nSites - 1.0;
	    //	    System.out.println(alpha + ":" + beta);
	    double dnm = alpha + beta;
	    for (int l = 0 ; l < Len ; ++l)  {
		if (gSite[l] != ' ')  {
		    for (int j = 0 ; j < 20 ; ++j)  {
			g[j] = -2000.0;
		    }
		}
		else {
		    double Total = 0.0;
		    for (int j = 0 ; j < 20 ; ++j)  {
			g[j] = 0.0;
			for (int k = 0 ; k < 20 ; ++k)  {
			    double dnm2 = aaindex[k];
			    if (dnm2 == 0.0)  dnm2 = 0.00001;
			    g[j] += q[j][k] * Psfq[i][l][k]/dnm2;
			}
			Total += g[j];
		    }
		    for (int j = 0 ; j < 20 ; ++j)  {
			g[j] /= Total;
		    }
		}

		for (int j = 0 ; j < 20 ; ++j)  {
		    if (g[j] == -2000.0)  {
			Frq[i][l][j] = -2000.0;
		    }
		    else {
			Frq[i][l][j] = (alpha * Psfq[i][l][j] + beta * g[j])/dnm;
		    }
		}
	    }
	}

	/*	
	for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    System.out.println(">>> i = "  + i);
	    for (int j = 0 ; j < Len ; ++j)  {
		System.out.println(">> j = " + j);
		for (int k = 0 ; k < 20 ; ++k) {
		    System.out.println(n2AA(k) + " = " + Frq[i][j][k] + ":" + Psfq[i][j][k]);
		}
	    }
	}
	*/
    }

    double[][][] getFrqAll() {
	return Frq;
    }
    /*
    char n2AA(int a)  {                                                          
        String aa1="ARNDCQEGHILKMFPSTWYV";                                       
        return aa1.charAt(a);                                                    
    } 
    */ 
}
