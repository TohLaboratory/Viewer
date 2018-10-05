/*
 The code in this file is taken from the following URL.
 http://takaharukobayashi.blogspot.jp/2013/06/javabufferedbreaderreadline.html
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
 
public class ExtendedBufferedReader extends BufferedReader {
 
    public ExtendedBufferedReader(Reader in) {
        super(in);
    }
 
    public ExtendedBufferedReader(Reader in, int sz) {
        super(in, sz);
    }
 
    @Override
	public String readLine() throws IOException {
        int num = 0;
        StringBuffer strBfr = new StringBuffer();
        try {
            while ((num = this.read()) >= 0) {
                strBfr.append((char) num);
                switch ((char) num) {
                case '\r':
                case '\n':
                    return strBfr.toString();
                default:
                    break;
                }
            }
        } catch (IOException e) {
            throw e;
        }
 
        if (strBfr.length() == 0) {
            return null;
        } else {
            return strBfr.toString();
        }
    }
}
