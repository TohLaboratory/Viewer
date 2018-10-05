import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test2  {
    public static void main(String ... args) {

	GOParse gop = new GOParse(args[0]);

	int[][] mfArray = gop.getmfArray();
	int[][] ccArray = gop.getccArray();
	int[][] bpArray = gop.getbpArray();
	int objs = gop.getNumberOfObject();
	int umfs = gop.getuniquemolecularFunctionSize();
	int uccs = gop.getuniquecellularComponentSize();
	int ubps = gop.getuniquebiologicalProcessSize();

	for (int i = 0 ; i < objs ; ++i)  {
	    for (int j = 0 ; j < umfs ; ++j)  {
		System.out.print(mfArray[i][j] + ", ");
	    }
	    System.out.println("");
	} 
	System.out.println("//");
	for (int i = 0 ; i < objs ; ++i)  {
	    for (int j = 0 ; j < uccs ; ++j)  {
		System.out.print(ccArray[i][j] + ", ");
	    }
	    System.out.println("");
	} 
	System.out.println("//");
	for (int i = 0 ; i < objs ; ++i)  {
	    for (int j = 0 ; j < ubps ; ++j)  {
		System.out.print(bpArray[i][j] + ", ");
	    }
	    System.out.println("");
	} 
	System.out.println("//");

    }

}
