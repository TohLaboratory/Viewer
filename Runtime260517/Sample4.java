import java.io.IOException;
import java.io.File;
import java.lang.Process;
import java.lang.ProcessBuilder;

class Sample4 {

    public static void main(String[] args)  {
	ProcessBuilder pb = new ProcessBuilder("ping", "-c", "3", "127.0.0.1");
	File log = new File("ping-log.txt");
	// 標準エラーを標準出力にマージする
	pb.redirectErrorStream(true);
	// 出力のリダイレクト先にファイルを指定 (上書き)
	pb.redirectOutput(log);
	// ファイルに追記する場合
	// pb.redirectOutput(Redirect.appendTo(log));

	try {
	    Process p = pb.start();
	    // ping が完了するのを待つ
	    p.waitFor();

	    // 実行結果を取得するストリームの種別を出力
	    System.out.println(pb.redirectInput());

	} catch (IOException | InterruptedException e) {
	    // 例外ハンドリング処理
	}
    }
}
