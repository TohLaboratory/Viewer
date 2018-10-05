import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test  {
    public static void main(String ... args) {

	GOParse gop = new GOParse(args[0]);

	ArrayList<ArrayList<String>> mf = gop.getmolFunction();
	ArrayList<ArrayList<String>> cc = gop.getcelComponent();
	ArrayList<ArrayList<String>> bp = gop.getbioProcess();

        ArrayList<String> nonuniqueMF = new ArrayList<String>();
	ArrayList<String> nonuniqueCC = new ArrayList<String>();

        for (int i = 0 ; i < mf.size() ; ++i) {
	    for (int j = 0 ; j < mf.get(i).size() ; ++j)  {
		nonuniqueMF.add(mf.get(i).get(j));
	    }
	    for (int j = 0 ; j < cc.get(i).size() ; ++j)  {
		nonuniqueCC.add(cc.get(i).get(j));
	    }
	}

        List<String> mflist = nonuniqueMF;
	List<String> cclist = nonuniqueCC;

	Set<String> setmf = new HashSet<String>();
	setmf.addAll(mflist);
	Set<String> setcc = new HashSet<String>();
	setcc.addAll(cclist);

	List<String> uniqueMF = new ArrayList<String>();
	uniqueMF.addAll(setmf);
	List<String> uniqueCC = new ArrayList<String>();
	uniqueCC.addAll(setcc);

	System.out.println("Unique Molecular Function");
	for (int i = 0 ; i < uniqueMF.size() ; ++i)  {
	    System.out.println("   " + uniqueMF.get(i)); 
	}

	System.out.println("Unique Cellular Component");
	for (int i = 0 ; i < uniqueCC.size() ; ++i)  {
	    System.out.println("   " + uniqueCC.get(i)); 
	}

	int[][] mfArray = new int[mf.size()][uniqueMF.size()];
	for (int i = 0 ;i < mf.size() ; ++i) {
	    for (int j = 0 ; j < uniqueMF.size() ; ++j)  {
		mfArray[i][j] = 0;
	    }
	}
	for (int i = 0 ; i < mf.size() ; ++i)   {
	    for (int j = 0 ; j < mf.get(i).size() ; ++j)  {
		for (int k = 0 ; k < uniqueMF.size() ; ++k)  {
		    if (uniqueMF.get(k).equals(mf.get(i).get(j))) {
			mfArray[i][k] = 1;
		    }
		}
	    }
	}

	int[][] ccArray = new int[cc.size()][uniqueCC.size()];
	for (int i = 0 ;i < cc.size() ; ++i) {
	    for (int j = 0 ; j < uniqueCC.size() ; ++j)  {
		ccArray[i][j] = 0;
	    }
	}
	for (int i = 0 ; i < cc.size() ; ++i)   {
	    for (int j = 0 ; j < cc.get(i).size() ; ++j)  {
		for (int k = 0 ; k < uniqueCC.size() ; ++k)  {
		    if (uniqueCC.get(k).equals(cc.get(i).get(j))) {
			ccArray[i][k] = 1;
		    }
		}
	    }
	}

	for (int i = 0 ; i < mf.size() ; ++i)  {
	    for (int j = 0 ; j < uniqueMF.size() ; ++j)  {
		System.out.print(mfArray[i][j] + ", ");
	    }
	    System.out.println("");
	} 
	System.out.println("//");
	for (int i = 0 ; i < cc.size() ; ++i)  {
	    for (int j = 0 ; j < uniqueCC.size() ; ++j)  {
		System.out.print(ccArray[i][j] + ", ");
	    }
	    System.out.println("");
	} 
	System.out.println("//");

    }

}
