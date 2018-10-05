import java.util.ArrayList;
import java.lang.Integer;

public class arrlist3  {
    static private ArrayList<Integer>[] testarr;

    public static void main(String ... args)  { 
	testarr = new ArrayList<Integer>[10](); 

	for (int i = 0 ; i < 10 ; ++i)  {
	    for (int j = 0 ; j < 5 ; ++j)  {
		testarr[i].add(5*i + j);
	    }
	}

	System.out.println("Size = " + testarr.length);
        for (int i = 0 ; i < testarr.length; ++i)  {
	    System.out.println(">> i = " + i);
	    for (int j = 0 ; j < testarr[i].size() ; ++j)  {
		 System.out.print(" j = " +  testarr[i].get(j).intValue()+ ", ");
	    }
	    System.out.println();
	}
    }
}
