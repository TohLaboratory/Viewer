import java.util.ArrayList;
import java.lang.Integer;

public class arrlist  {
   static private ArrayList<Integer> testarr = new ArrayList<Integer>();;
   static private ArrayList<Integer> testarr2 = new ArrayList<Integer>();;

    public static void main(String ... args)  { 
	for (int i = 0 ; i < 10 ; ++i)  {
	    testarr.add(new Integer(3*i));
	    testarr2.add(i);
	}
	System.out.println("Size = " + testarr.size());

        for (int i = 0 ; i < testarr2.size() ; ++i)  {
	    System.out.println(testarr2.get(i));
	}
	
    }
}
