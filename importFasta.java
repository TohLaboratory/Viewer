import java.io.*;
import java.util.ArrayList;

public class importFasta   {
    private  ArrayList<String> Seq = new ArrayList<String>();
    private  ArrayList<String> Name = new ArrayList<String>();
    private  int seqSize = 0;

    public importFasta(String fileName)  {
        try  {
            String str;
            String str2[];
            String seqstr = "";
	    FileReader in = new FileReader(new File(fileName));
	    BufferedReader b = new BufferedReader(in);
            while((str = b.readLine()) != null) {
                if (seqSize == 0 && (str.length() == 0 || str.charAt(0) != '>')) continue;
		if (str.length() != 0 && str.charAt(0) == '>')  {
                        Name.add(str);
                        if (seqSize != 0)   {
			    Seq.add(seqstr);
                            seqstr = "";; 
			}
                        seqSize++;
		}   
                else  {
                        if (str.length() == 0)  continue;
			str2 = str.split(" ",0);
                        for (int j = 0 ; j < str2.length ; ++j) {
                            if (" ".equals(str2[j])) continue;
			    seqstr=seqstr.concat(str2[j]);
			}
		}
	    }
	    Seq.add(seqstr);
	    in.close();
	}
	catch (FileNotFoundException e) {
	    System.out.println(e);
	}
	catch (IOException e) {
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
