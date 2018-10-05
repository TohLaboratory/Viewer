/*  http://dotnsf.blog.jp/archives/1022900387.html  */
import java.awt.Desktop;
import java.net.URI;

public class testBrowser {
    public static void main(String ... args)  {
	String uriString = "http://yahoo.co.jp";
        Desktop desktop = Desktop.getDesktop();
  
        try {
	    URI uri = new URI(uriString);
	    desktop.browse(uri);
	}
	catch (Exception e)  {
	    e.printStackTrace();
	}
    }
}
