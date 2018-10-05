public class test  {

    public static void main(String[] args)  {
	importFasta ifseq = new importFasta(args[0]);
	System.out.println("No Seq = " + ifseq.size());

        for (int i = 0 ; i  < ifseq.size() ; ++i)  {
	    System.out.println(ifseq.getName(i));        
	    System.out.println(ifseq.getSeq(i));        
	}
    }
}
