import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;

public class LengthChecker {

    public static void main(String ... args) {
	readSeq ifseq = new readSeq(args[0], 1);
        int basicLength = Integer.parseInt(args[1]);
	int margin = Integer.parseInt(args[2]);

        for (int i= 0 ; i < ifseq.getSeqSize() ; ++i) {
	    int len = ifseq.getSeqLen(i);
	    if (len <= basicLength+margin && len >= basicLength-margin)  {
		System.out.println(ifseq.getName(i));
		for (int j = 0 ; j < len ; j += 60) {
		    int End = j + 60;
		    if (End >= len)  End = len;
		    for (int k = j ; k < End ; ++k) {
			System.out.print(ifseq.getSeq(i).charAt(k));
		    }
		    System.out.println();
		}
	    }
	}
	
    }

}
