import java.util.ArrayList;

public class CnsVar {
    private ArrayList<ArrayList<Double>> CV = new ArrayList<ArrayList<Double>>();

    CnsVar(double[][] ScoreMtx, SeqWeight swg, importCluster clinf, readSeq ifseq, double max)  {

	for (int i = 0 ; i < clinf.getClusterSize() ; ++i) {

	    ArrayList<Double> cvProf = new ArrayList<Double>();
	    System.out.println("Length =" + ifseq.getSeqLen(0));

	    for (int j = 0 ; j < ifseq.getSeqLen(0); ++j)  {
		double total = 0.0;
		double score = 0.0;
		if (clinf.getNumberOfMember().get(i).intValue() != 1)  { 
		    for (int k = 0 ; k < clinf.getNumberOfMember().get(i)-1 ; ++k)  {
			int x = clinf.getCluster().get(i).get(k).intValue();
			int xx = aatoI(ifseq.getSeq(x).charAt(j));
			for (int l = k + 1 ; l < clinf.getNumberOfMember().get(i) ; ++l)  {
			    int y = clinf.getCluster().get(i).get(l).intValue();
			    int yy = aatoI(ifseq.getSeq(y).charAt(j));

			    if (xx == -1 && yy == -1) continue;
			    else if ((xx == -1 && yy != -1) || (xx != -1 && yy == -1)) {
				total += swg.getSeqWeight(i, k) * swg.getSeqWeight(i, l);
				score -= swg.getSeqWeight(i, k) * swg.getSeqWeight(i, l);
			    }
			    else {
				total += swg.getSeqWeight(i, k) * swg.getSeqWeight(i, l);
				score += ScoreMtx[xx][yy] * swg.getSeqWeight(i, k) * swg.getSeqWeight(i, l)/Math.sqrt(ScoreMtx[xx][xx]*ScoreMtx[yy][yy]);
			    }
			}
		    }
		}
		if (total != 0.0) cvProf.add(Double.valueOf(score/total));
		else              cvProf.add(Double.valueOf(0.0));
	    }
	    CV.add(cvProf);
	}
    }

    ArrayList<ArrayList<Double>> getCnsvar() {
	return CV;
    }

    int aatoI(char aa)  {
	String AA = "ARNDCQEGHILKMFPSTWYV";

	int aai = -1;
	for (int i = 0 ; i < 20 ; ++i)  {
	    if (AA.charAt(i) == aa)  {
		aai = i;
		break;
	    }
	}

	return aai;
    }
}
