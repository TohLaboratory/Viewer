import java.io.*;
import java.util.ArrayList;

public class readSeq {
    private importClustal ics;
    private importFasta   ifs;
    private ArrayList<String> Name;
    private ArrayList<String> Seq;
    private int seqSize;

    public readSeq(String filename, int idx)  {
	if (idx == 0)  {
            ics = new importClustal(filename);
	    Name = ics.getNmAL();
            Seq = ics.getSqAL();
	    seqSize = ics.getSeqSize();
        }
        else if (idx == 1)  {
            ifs = new importFasta(filename);
	    Name = ifs.getNmAL();
            Seq = ifs.getSqAL();
	    seqSize = ifs.getSeqSize();
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
