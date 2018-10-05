public class Calc_Lambda {
    private double lambda;

    public Calc_Lambda(double ilambda, double[][] scoreMtx, double[] aaIndex, int Max, double eps)  {
        int k = 0;
	double pl = 0.0;
        lambda = ilambda; 

        while(Math.abs(pl - lambda) > eps && k < Max)  { 
	    System.out.println(k + ":" + pl);
	    pl = lambda;
	    double F = 0.0;
	    double FD = 0.0;
	    for (int i = 0 ; i < 20 ; ++i)  {
		for (int j = 0 ; j < 20 ; ++j)  {
		    F += aaIndex[i] * aaIndex[j] * Math.exp(lambda * scoreMtx[i][j]);
		    FD += aaIndex[i] * aaIndex[j] * Math.exp(lambda * scoreMtx[i][j]) * scoreMtx[i][j];
		}
	    }
	    F -= 1.0;
	    lambda = lambda - (F/FD);
	    k++;
        }

	if (lambda == 0.0 || k == Max)  {
	    System.out.println("Change initial value or Max iteration number");
	}
	else System.out.println("Lambda = " + lambda);
    }

    double getLambda()  {
	return lambda;
    }
}

