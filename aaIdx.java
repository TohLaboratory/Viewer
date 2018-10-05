import java.io.*;

public class aaIdx  {
    private double[] aaidx = new double[20];
    private int chk = 0;

    public aaIdx(String id)  {
        try {
            int  m = 0;
	    FileReader in = new FileReader("Data/aaindex1");
	    BufferedReader b = new BufferedReader(in);
	    String str;

	    while((str = b.readLine()) != null)  {
		if (str.charAt(0) == 'H' && str.split(" ", 0)[1].equals(id))  {
		    chk = 0;
		    while((str = b.readLine()) != null)  {
			if (str.charAt(0) == 'I')  { 
			    chk = 1;
			    continue;
			}
			if (chk == 1)  {
			    if (str.equals("//")) break;
			    String[] str2 = str.split(" ", 0);
			    for (int n = 0 ; n < str2.length ; ++n)  {
                                if (str2[n].equals("") || str2[n].charAt(0) == ' ')   continue;
			        else  {
				    aaidx[m++] = Double.parseDouble(str2[n]);
				}  
			    }
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

    double[] getIdx()  {
	return aaidx;
    }

    int  getIdxChk()  {
	return chk;
    }
}
