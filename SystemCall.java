import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class SystemCall {

    public static void main(String[] args) {
        String Command = new String("ls -al"); // 起動コマンドを指定する
        Runtime runtime = Runtime.getRuntime(); // ランタイムオブジェクトを取得する
	try {
	    Process p =runtime.exec(Command); // 指定したコマンドを実行する
	    //InputStream is = p.getInputStream();
	    BufferedReader reader = new BufferedReader( new InputStreamReader(p.getInputStream()));
	    String line = null;
	    while(null != (line = reader.readLine())){
		System.out.println(line);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}




