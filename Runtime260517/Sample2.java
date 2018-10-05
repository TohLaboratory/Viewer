import java.io.IOException;
import java.io.InputStream;

public class Sample2 {
    public static void main(String[] args) {
	try  {
	    String[] Command = {
		"sh",
		"-c",
                "grep test test.dat > test2.dat"
	    };

	    Process p = Runtime.getRuntime().exec(Command);
	    try {
		p.waitFor();
	    }
	    catch (InterruptedException e)  {
		e.printStackTrace();
	    }
	}
        catch (IOException ex) {
	    ex.printStackTrace();
	}
    }
}

