public class IDListQuickSort  {
    private static ArrayList<nodeID_Angle> idList;

    IDListQuickSort(ArrayList<nodeID_Angle> idl)  {
	idList = idl;
        quick_sort(0, idList.size()-1);
    }
    
    static void quick_sort(int left, int right)  {
	double p = idList.get((left+right)/2).get_Angle();
        int l = left, r = right;
	nodeID_Angle tmp;
	while(l <= r)  {
	    while(idList.get(l).get_Angle() < p) { l++; }
	    while(idList.get(r).get_Angle() > p) { r--; }
            if (l <= r)  {
		tmp = idList.get(l);
		idList.set(l, idList.get(r));
	        idList.set(r, tmp);
	    } 
	}
	quick_sort(left, r);
	quick_sort(l, right);
    }
}
