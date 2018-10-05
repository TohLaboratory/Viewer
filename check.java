import java.io.*;

public class check {
    public static void main(String ... args)  {
	try {
	    String str;
	    FileReader in = new FileReader(args[0]);
	    FileWriter out = new FileWriter(args[1]);
	    BufferedReader in_b = new BufferedReader(in);
	    BufferedWriter out_b = new BufferedWriter(out);
	    while((str = in_b.readLine()) != null) {
		String[] str2 = str.split("\\|");
		out_b.write(str2[1]);
		out_b.newLine();
	    }
	    out_b.close();
	    in_b.close();
	    out.close();
	    in.close();
	}
	catch(IOException e) {
	    System.out.println("IOError");
	}
    }
}
