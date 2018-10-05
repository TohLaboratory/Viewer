import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.SplitPane; 
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class ViewerS extends Application  {

  private final Desktop desktop = Desktop.getDesktop(); 
  private importFasta  ifseq;
  private importCluster clsinfo;
  private int slen, ssize, cHeight = 1120, cWidth = 730, clsize;
  Canvas canvas1, canvas2;   // 例文ではfinal
  GraphicsContext gc1, gc2;

  @Override
  public void start(Stage stage) throws Exception {
      stage.setTitle("Viewer");
      stage.setWidth(1120);
      stage.setHeight(880);

      final FileChooser fileChooser = new FileChooser();
      VBox root = new VBox();
      
      MenuBar menuBar = new MenuBar();
      Menu fileMenu = new Menu("File");

      MenuItem mnuImSequFS = new MenuItem("Import Seq File (unaligned FASTA)");
      MenuItem mnuImSeqaFS = new MenuItem("Import Seq File (aligned FASTA)");
      MenuItem mnuImSeqCl = new MenuItem("Import Seq File (Clustal)");
      MenuItem mnuImTree = new MenuItem("Import Tree File");
      MenuItem mnuImClus = new MenuItem("Import Cluster File");
      MenuItem mnuExit = new MenuItem("Exit");

      mnuImClus.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
	      if (file != null)  {
		  String filename = file.getName();
                  clsinfo = new importCluster(filename);		  
		  clsize = clsinfo.getClusterSize();
		  if (ifseq != null)  {
		      if ((slen * 25 + 10) > cWidth) cWidth = slen * 25+ 10;
		      if (((clsize + 10) * 25 + 50) > cHeight) cHeight = (clsize + 10) * 25 + 50;
		      canvas1 = new Canvas(cWidth, cHeight);
		      gc1 = canvas1.getGraphicsContext2D();
		  }
		  canvas2 = new Canvas(cHeight, 400);
		  gc2 = canvas2.getGraphicsContext2D();
	      }
	  });
      mnuImSeqCl.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
	      if (file != null)  {
		  String filename = file.getName();
		  ifseq = new importFasta(filename);
	      }
	  });
      mnuImSeqaFS.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
	      if (file != null)  {
		  String filename = file.getName();
		  ifseq = new importFasta(filename);
		  slen = ifseq.getSeqLen(0);
                  ssize = ifseq.getSeqSize();
		  if (clsinfo != null)  {
		      if ((slen * 25 + 10) > cWidth) cWidth = slen * 25+ 10;
		      if (((clsize + 10) * 25 + 50) > cHeight) cHeight = (clsize + 10) * 25 + 50;
		      canvas1 = new Canvas(cWidth, cHeight);
		      gc1 = canvas1.getGraphicsContext2D();
		      canvas2 = new Canvas(cHeight, 400);
		      gc2 = canvas2.getGraphicsContext2D();
		  }
		  //		  System.out.println("Size = " + ssize + " ; Length = " + slen);
		  /*
		  for (int j = 0 ; j < ifseq.size() ; ++j)  {
		      System.out.println(ifseq.getName(j));
		      System.out.println(ifseq.getSeq(j));
		  }
		  */
	      } 
	  });

      mnuImSequFS.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
              ProcessBuilder pb = new ProcessBuilder("mafft","--auto", file.getName());
	      File alnfasta = new File("dummy.txt");
	      // 標準エラーを標準出力にマージ
	      pb.redirectErrorStream(true);
	      // 出力のリダイレクト先にファイルを指定
	      pb.redirectOutput(alnfasta);
	      try {
		  Process p = pb.start();
		  // 処理の終了を待つ
		  p.waitFor();		  
                  // 実行結果を取得するストリームの種別を出力
	          //System.out.println(pb.redirectInput());     
		  ifseq = new importFasta("dummy.txt");
		  /*  */
		  for (int j = 0 ; j < ifseq.size() ; ++j)  {
		      System.out.println(ifseq.getName(j));
		      System.out.println(ifseq.getSeq(j));
		  } 
		  slen = ifseq.getSeqLen(0);
                  ssize = ifseq.getSeqSize();
		  if (clsinfo != null)  {
		      if ((slen * 25 + 10) > cWidth) cWidth = slen * 25+ 10;
		      if (((clsize + 10) * 25 + 50) > cHeight) cHeight = (clsize + 10) * 25 + 50;
		      canvas1 = new Canvas(cWidth, cHeight);
		      gc1 = canvas1.getGraphicsContext2D();
		      canvas2 = new Canvas(cHeight, 400);
		      gc2 = canvas2.getGraphicsContext2D();
		  }
		  //		  System.out.println("Size = " + ssize + " ; Length = " + slen);                  
		  /*  */ 
              }
	      catch (IOException | InterruptedException e) {
		  e.printStackTrace();
	      }
	  });

      mnuExit.setOnAction(event -> System.exit(0));

      fileMenu.getItems().addAll(mnuImSequFS, mnuImSeqaFS, mnuImSeqCl, mnuImTree, mnuImClus, mnuExit);
      menuBar.getMenus().addAll(fileMenu);
      
      root.getChildren().addAll(menuBar);


      SplitPane splitpane = new SplitPane();
      /*  
    TextArea txt1 = new TextArea("左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。左のテキストです。\n");
      TextArea txt2 = new TextArea("右のテキストです。右のテキストです。右のテキストです。右のテキストです。右のテキストです。右のテキストです。右のテキストです。右のテキストです。");
*/
      splitpane.setPrefHeight(730);
      //      splitpane.getItems().addAll(txt1, txt2);
      splitpane.getItems().addAll(gc1, gc2);
      splitpane.setDividerPositions(0.3f);

      TextArea txtArea = new TextArea();
      txtArea.setPrefHeight(150);

      root.getChildren().addAll(splitpane, txtArea);
     
      stage.setScene(new Scene(root));
      stage.show();
  }

  private void openFile(File file)  {
      EventQueue.invokeLater(() -> { 
	      try {
		  desktop.open(file);
              }
	      catch (IOException ex)  {
		  Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
	      }
	  });
  }
}

