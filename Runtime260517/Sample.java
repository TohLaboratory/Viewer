import java.io.IOException;
import java.io.InputStream;

public class Sample {
    public static void main(String[] args) {
	try {
	    Runtime rt = Runtime.getRuntime();
	    Process p = rt.exec("ls -al");
            InputStream is = p.getInputStream();
            System.out.println(is);
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
    }
}