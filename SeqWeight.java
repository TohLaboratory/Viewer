import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SeqWeight  {
    private ArrayList<ArrayList<Double>> seqWeight = new ArrayList<ArrayList<Double>>();
    private ArrayList<Double> swa;

    SeqWeight(importCluster clinf)  {	
        int  clSize = clinf.getClusterSize();
	ArrayList<Integer> numberOfMember = clinf.getNumberOfMember();

	for (int i = 0 ; i < clSize ; ++i)  {
	    swa = new ArrayList<Double>();
	    for (int j = 0 ; j < numberOfMember.get(i).intValue() ; ++j) swa.add(Double.valueOf(1.0/(double)numberOfMember.get(i).intValue()));
	    seqWeight.add(swa);	    
	}
    }

    SeqWeight(DistM distM, importCluster clinf)  {
        int  clSize = clinf.getClusterSize();
	if (distM == null)  {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("AlertType.INFORMATION");
	    alert.setHeaderText("No Available Distance Matrix!!");
	    alert.setContentText("A distance aatrix is required for this operation");
	    alert.showAndWait();	    
	}
	else  {
	    ArrayList<Integer> numberOfMember = clinf.getNumberOfMember();
	    ArrayList<ArrayList<Integer>> clusterInfo = clinf.getCluster();

	    for (int i = 0 ; i < clSize ; ++i)  {

		swa = new ArrayList<Double>();
		for (int j = 0 ; j < numberOfMember.get(i).intValue() ; ++j) swa.add(Double.valueOf(0.0d));
	    
		for (int j = 0 ; j < numberOfMember.get(i).intValue() ; ++j)  {
		    int x = clusterInfo.get(i).get(j).intValue();
		    for (int k = 0 ; k < numberOfMember.get(i).intValue() ; ++k)  {
			int y = clusterInfo.get(i).get(k).intValue();
			swa.set(j, Double.valueOf(swa.get(j).doubleValue()+distM.getDistance(x, y)));
		    }
		}

		double totalWeight = 0.0;
		for (int j = 0 ; j < numberOfMember.get(i).intValue() ; ++j) totalWeight += swa.get(j).doubleValue();
		for (int j = 0 ; j < numberOfMember.get(i).intValue() ; ++j) swa.set(j, swa.get(j).doubleValue()/totalWeight);
		seqWeight.add(swa);
		for (int j = 0 ; j < numberOfMember.get(i).intValue() ; ++j) System.out.println(i + ":" + j + ":" + getSeqWeight(i, j));
	    }
	}

    }

    double getSeqWeight(int idCluster, int id)  {
	return  seqWeight.get(idCluster).get(id).doubleValue();
    }
}
