import java.io.*;
import java.util.ArrayList;

public class importClustal   {
    private ArrayList<String> Name = new ArrayList<String>();
    private ArrayList<String> Seq = new ArrayList<String>();
    private int seqSize = 0; 
   
    importClustal(String fileName)  {
	String[] str2;

	//	System.out.println(fileName);

	try {
	    String str;
            int    clusPr = 0;
	    FileReader in = new FileReader(new File(fileName));
            BufferedReader b = new BufferedReader(in);
            for (int i = 0 ; i < 2 ; ++i)  str = b.readLine();

            while((str = b.readLine()) != null)  {
		if (str.length() == 0)  continue;
		if (str.charAt(0) == ' ')  clusPr++;
                if (clusPr == 0) seqSize++;
            }
            in.close();
	    
	    FileReader in2 = new FileReader(new File(fileName));
	    BufferedReader b2 = new BufferedReader(in2);

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
                            if (k != 0 && str2[k].length() != 0 && str2[k].charAt(0) != ' ')  {
				if (dseq[j-1] == null) dseq[j-1] = str2[k];
				else  dseq[j-1] = dseq[j-1] + str2[k];
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
	catch (FileNotFoundException e) {
	    System.out.println(e);
	}
        catch (IOException e)  {
	    System.out.println(e);
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
    public ArrayList<String> getSqAL()  {
	return Seq;
    }
    public ArrayList<String> getNmAL()  {
	return Name;
    }
}
