import java.io.*;
import java.util.ArrayList;

public class test3  {
    public static void  main(String[] args)  { 
	importCluster cl = new importCluster("aqpcl.txt");
	int x = cl.getClusterSize();
	System.out.println("Number of Clusters = " + x);
	ArrayList<Integer> memnum = cl.getNumberOfMember();
	ArrayList<ArrayList<Integer>> clinfo = cl.getCluster();
	for (int i = 0 ; i < x ; ++i)  {
	    System.out.println("cl id no = " + i + "; " + memnum.get(i)) ;           	   
	    System.out.println(cl.getClusterName(i));
	    ArrayList<Integer> y = clinfo.get(i);
	    for (int j = 0 ; j < memnum.get(i)  ; ++j)  {
		System.out.print(y.get(j) + " ");
	    }             
	    System.out.println("//");
	}
    }
}
