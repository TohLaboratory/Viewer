import java.io.*;

public class scoreIdx  {
    private double[][] sidx = new double[20][20];
    private int chk = 0;

    public scoreIdx(String id)  {
        try {
	    FileReader in = new FileReader("Data/aaindex2");
	    BufferedReader b = new BufferedReader(in);
	    String str;

	    while((str = b.readLine()) != null)  {
		if (str.charAt(0) == 'H' && str.split(" ", 0)[1].equals(id))  {
		    chk = 0;
		    while((str = b.readLine()) != null)  {
			if (str.charAt(0) == 'M')  { 
			    chk = 1;
			    continue;
			}
			if (chk >= 1)  {
			    if (str.equals("//")) break;
			    String[] str2 = str.split(" ", 0);
                            int m = 0;
			    for (int n = 0 ; n < str2.length ; ++n)  {
                                if (str2[n].equals("") || str2[n].charAt(0) == ' ')   continue;
			        else  {
				    sidx[chk-1][m] = Double.parseDouble(str2[n]);
				    sidx[m][chk-1] = sidx[chk-1][m];
				    m++;
				}  
			    }
                            chk++;
			}
			else continue;
		    }
		    break;
		}
	    }
	}
	catch (IOException e)  {
	    System.out.println(e);
	}
    }

    double[][] getIdx()  {
	return sidx;
    }

    double getIdxScore(int i, int j)  {
	return sidx[i][j];
    }

    int  getIdxChk()  {
	return chk;
    }
}
