import java.io.*;
import java.util.ArrayList;

public class importCluster {
    private int sizeOfCluster;
    private ArrayList<Integer> numberOfMember = new ArrayList<Integer> (); 
    private ArrayList<String> nameOfCluster = new ArrayList<String> ();
    private ArrayList<Integer> idOfClusters = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> clusterInfo = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> ali;

    /**
     *
     * [importCluster] is a class to read a file including the information of clustering
     *
     *@ sizeOfCluster : number of the clusters
     *@ numberOfMember : number of the members in each cluster
     *@ clusterInfo : members of each cluster (indicated by the numbers of the order in the alignment file 
     *
     */

    public importCluster() {
    }

    public importCluster(String fileName)  {
	try {
	    String str;
	    FileReader in = new FileReader(fileName);
	    BufferedReader b = new BufferedReader(in);

	    while((str = b.readLine()) != null)  {
		str = str.trim();
		nameOfCluster.add(str);
		str = b.readLine();
                str = str.trim();
		int mnum = Integer.parseInt(str);
		sizeOfCluster++;

		int i = 0;
		ali = new ArrayList<Integer>(); 
                while (i < mnum)  {
		    str = b.readLine();
		    str = str.trim();
                    String [] str2 = str.split(" ");
                    for (int j = 0; j < str2.length ; ++j)  {
			if (str2[j].isEmpty() || str2[j].charAt(0) == ' ') continue;
			else {
			    ali.add(Integer.parseInt(str2[j])-1);
			    i++;
			}		
		    }
		}
                numberOfMember.add(mnum);
		clusterInfo.add(ali);
	    }	    
	}
	catch (IOException e) {
	    System.out.println(e);
	}
    }

    int getClusterSize()  {
	return sizeOfCluster;
    }
    ArrayList<Integer> getNumberOfMember()  {
	return  numberOfMember;
    }
    ArrayList<ArrayList<Integer>> getCluster()  {
	return clusterInfo;
    }
    String getClusterName(int i)  {
	return nameOfCluster.get(i);
    }
    int  getClusterID(int i)  {
	return  idOfClusters.get(i).intValue();
    }

    void  setsizeOfCluster(int num)  {
	sizeOfCluster = num;
    } 

    void setidOfclusters(ArrayList<Integer> idList) {
        idOfClusters = idList;
    }
    
    ArrayList<Integer> getidOfClusters() {
	return  idOfClusters;
    }

    void setclusterInfo(ArrayList<Integer> members) {
	clusterInfo.add(members);
    }

    void setnumberOfMember(int nm) {
	numberOfMember.add(nm);
    }

    void setNameOfCluster(ArrayList<String> namelist) {
	nameOfCluster.clear();
	for (int i = 0 ; i < namelist.size() ; ++i)  {
	    nameOfCluster.add(namelist.get(i));
	}
    }    
}
