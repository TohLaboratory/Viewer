import java.io.*;
import java.util.ArrayList;

public class test4  {

    public static void main(String ... args)  {
	String filename;
        int    fchk;
        filename = args[0];
        fchk = Integer.parseInt(args[1]);

	readSeq rs = new readSeq(filename, fchk);
	System.out.println("No Seq = " + rs.getSeqSize());
	System.out.println(rs.getName(3));
	System.out.println(rs.getName(4));
        String a1 = rs.getSeq(3);
	String a2 = rs.getSeq(4);

	System.out.println("length = " + a1.length());

        int end, j;
        for (int i = 0 ; i < a1.length() ; i += 50)  {
	    end = i + 50;
            if (end > a1.length()) end = a1.length();
	    for (j = i ; j < end ; ++j)  {
		System.out.print(a1.charAt(j));
	    }
	    System.out.println();
	    for (j = i ; j < end ; ++j)  {
		System.out.print(a2.charAt(j));
	    }
	    System.out.println();
	    System.out.println();
	}
    }
   
}
