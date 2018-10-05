import java.io.IOException;


public class SystemCall {

    public static void main(String[] args) {
        String[] Command = { "java", "-jar", "~/Desktop/dist/psurfer.jar", "search.json", "outout.json"}; // 起動コマンドを指定する
        Runtime runtime = Runtime.getRuntime(); // ランタイムオブジェクトを取得する
	try {
	    runtime.exec(Command); // 指定したコマンドを実行する
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
