import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;
import java.lang.ProcessBuilder;

class Sample3 {

    public static void main(String[] args)  {
	ProcessBuilder pb = new ProcessBuilder("ping", "-c", "3", "127.0.0.1");

	try {
	    Process p = pb.start();
	    // ping が完了するのを待つ
	    p.waitFor();

	    // 実行結果を取得するストリームの種別を出力
	    System.out.println(pb.redirectInput());

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
		    // ping結果の出力
		    for(String line = br.readLine(); line != null; line = br.readLine()) {
			System.out.println(line);
		    }
		}
	} catch (IOException | InterruptedException e) {
	    // 例外ハンドリング処理
	}
    }
}
