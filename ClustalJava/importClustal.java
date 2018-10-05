import java.io.*;
import java.util.ArrayList; 

public class importClustal {
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Seq = new ArrayList<String>();
    private int seqSize = 0; 
   
    importClustal(String fileName)  {
	String[] str2;

	System.out.println(fileName);

	try {
	    String str;
            int    clusPr = 0;
	    FileReader in = new FileReader(new File(fileName));
            ExtendedBufferedReader b = new ExtendedBufferedReader(in);
            for (int i = 0 ; i < 2 ; ++i)  str = b.readLine();

            while((str = b.readLine()) != null)  {
		if (str.charAt(0) == ' ')  clusPr++;
		if (str.charAt(0) == '\n' || str.charAt(0) == '\r')  continue;
                else if (clusPr == 0) seqSize++;
            }
            in.close();
	    
	    FileReader in2 = new FileReader(new File(fileName));
	    ExtendedBufferedReader b2 = new ExtendedBufferedReader(in2);

	    String[] dseq = new String[seqSize];
	    String[] dname = new String[seqSize];

            for (int i = 0 ; i < 2 ; ++i)  str = b2.readLine();
            for (int i = 0 ; i < clusPr ; ++i)  {
		for (int j = 0 ; j < seqSize + 2 ; ++j)  {
		    str = b2.readLine();
                    if (j == 0 || (j == seqSize + 1)) continue; 
	            else  {
			str2 = str.split(" ", 0);
			for (int k = 0 ; k < str2.length ; ++k)  {
			    if (k == 0 && i == 0) dname[j-1] = str2[k];
                            if (k != 0 && str2[k].length() != 0 && str2[k].charAt(0) != ' ' && str2[k].charAt(0) != '\n' && str2[k].charAt(0) != '\r')  {
				int x = str2[k].length()-1;
				if (dseq[j-1] == null) dseq[j-1] = str2[k].substring(0,x);
				else  dseq[j-1] = dseq[j-1] + str2[k].substring(0,x);
			    }
			}
		    }  		
		} 
	    }
            in2.close();
	    
	    for (int i = 0 ; i < seqSize ; ++i)  {
		Seq.add(dseq[i]);
		Name.add(dname[i]);
	    }
	    
        }
        catch (Exception e)  {
	    System.out.println("importClustal:Input Error");
	}
    }

    public String getSeq(int i)  {
        return  Seq.get(i);
    }
    public int getSeqLen(int i)  {
	return  Seq.get(i).length();
    }
    public int getSeqSize() {
        return seqSize;
    }
    public String getName(int i)  {
        return  Name.get(i);
    }
    public int size()  {
        return  Name.size();
    }
}
