public class DescendantListQuickSort  {
    private static ArrayList<DescendantNode> dList;

    DescendantListQuickSort(ArrayList<DescendantNode> descendantList)  {
	dList = descendantList;
        quick_sort(0, dList.size()-1);
    }
    
    static void quick_sort(int left, int right)  {
	double p = dList.get((left+right)/2).getDist();
        int l = left, r = right;
	DescendantNode tmp;
	while(l <= r)  {
	    while(dList.get(l).getDist() < p) { l++; }
	    while(dList.get(r).getDist() > p) { r--; }
            if (l <= r)  {
		tmp = dList.get(l);
		dList.set(l, dList.get(r));
	        dList.set(r, tmp);
	    } 
	}
	quick_sort(left, r);
	quick_sort(l, right);
    }
}
