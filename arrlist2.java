import java.util.ArrayList;
import java.lang.Integer;

public class arrlist2  {
    static private ArrayList<ArrayList<Integer>> testarr = new ArrayList<ArrayList<Integer>>();;

    public static void main(String ... args)  { 
	ArrayList<Integer> test; 

	for (int i = 0 ; i < 10 ; ++i)  {
	    test = new ArrayList<Integer>();
	    for (int j = 0 ; j < 5 ; ++j)  {
		test.add(5*i + j);
	    }
	    testarr.add(test);
	}

	System.out.println("Size = " + testarr.size());
        for (int i = 0 ; i < testarr.size(); ++i)  {
	    System.out.println(">> i = " + i);
	    for (int j = 0 ; j < testarr.get(i).size() ; ++j)  {
		 System.out.print(" j = " +  testarr.get(i).get(j).intValue()+ ", ");
	    }
	    System.out.println();
	}
    }
}
