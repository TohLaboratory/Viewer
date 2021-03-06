import java.awt.Desktop;
import java.awt.EventQueue;
import java.net.URI;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.Prologue;

public class Viewer extends Application  {
  private final Desktop desktop = Desktop.getDesktop(); 
  private readSeq ifseq;
  private importCluster clinf;
  private Canvas canvasL;
  private Canvas canvasR;
  private GraphicsContext gcL;
  private GraphicsContext gcR;
  private BorderPane left = new BorderPane();
  private BorderPane right = new BorderPane();
  private DistM distM;
  private SeqWeight swg;
  private char c1[], c2[];
  private ClusterSeq cls;
  private int wgm;
  private aaIdx aid;
  private scoreIdx sid;
  private double[][] scoreMtx;
  private double[] aaIndex; 
  private int Ck = -1;
  private String uriString = "file:///Users/toh/Desktop/Viewer/molmil-gh-pages/index.html";
  private int maxItr;
  private TextArea ta;
  private String queryString = "";
  private wFrq weighted_Freq;
  //  private pFrq position_SpecificFreq;
  private double ilambda;
  private double beta;
  private double thr1;
  private double eps;
  private int thr2;
  private Calc_Lambda cLambda;
  private Calc_psFrq psaaComp; 
  private int chk = 0;
  private double idxTotal = 0.0;
  private njtreeBuilder njTree;
  
  @Override
  public void start(Stage stage) throws Exception {
      stage.setTitle("Viewer");
      stage.setWidth(1120);
      stage.setHeight(880);

      left.setOnMouseClicked(event -> rePaint(event));

      final FileChooser fileChooser = new FileChooser();
      VBox root = new VBox();
      
      MenuBar menuBar = new MenuBar();
      Menu fileMenu = new Menu("File");
      Menu seqAnal = new Menu("Seq Analysis");
      Menu strAnal = new Menu("Str Analysis");
      Menu webAnal = new Menu("Web");

      MenuItem mnuSqDist = new MenuItem("Sequence Distance");
      MenuItem mnuTaxWgt = new MenuItem("Taxonomic Bias");
      MenuItem mnuAAFreq = new MenuItem("Position Specific AA Freq");
      MenuItem mnuNJtree = new MenuItem("Neighbor-Joining Tree");
      MenuItem mnuCnsVar = new MenuItem("Conservation/Variation");
      MenuItem mnuFnDiff = new MenuItem("Functional Difference");

      MenuItem mnuImSequFS = new MenuItem("Import Seq File (unaligned FASTA)");
      MenuItem mnuImSeqaFS = new MenuItem("Import Seq File (aligned FASTA)");
      MenuItem mnuImSeqCl = new MenuItem("Import Seq File (Clustal)");
      MenuItem mnuImStr = new MenuItem("Import List of Structure files");
      MenuItem mnuImTree = new MenuItem("Import Tree File");
      MenuItem mnuImClus = new MenuItem("Import Cluster File");
      MenuItem mnuExSeqaFS = new MenuItem("Export Seq File (aligned FASTA)");
      MenuItem mnuExit = new MenuItem("Exit");

      MenuItem mnuMolmil = new MenuItem("Launch Molmil");

      MenuItem mnuSparql = new MenuItem("SPARQL");
      MenuItem mnuScraping = new MenuItem("Scraping");
   
      /* >>> Web Menu ************************************************************************************** */
      /* *** mnuSparql : Launcher of Sparql Search Window                                        *********** */
      /* *************************************************************************************************** */

      mnuSparql.setOnAction(event -> {
              // 新しいウィンドウを生成
              Stage mnuStage = new Stage();
	      mnuStage.setWidth(600);
	      mnuStage.setHeight(450);
              // モーダルウィンドウに設定
              mnuStage.initModality(Modality.APPLICATION_MODAL);
              // オーナーを設定
              mnuStage.initOwner(stage);

	      Label label1 = new Label("endpoint");
              TextField ep = new TextField();      
	      HBox hbx1 = new HBox();
	      hbx1.getChildren().addAll(new Label("   "), label1, new Label("        "), ep);

	      Button btn0 = new Button("Select Input File");
	      btn0.setOnAction(event1 -> {
		      File file = fileChooser.showOpenDialog(stage);
		      queryString ="";
		      String str;
		      if (file != null)  {
			  try {
			      String filename = file.getName();
			      FileReader in = new FileReader(filename);
			      BufferedReader b = new BufferedReader(in);
			      while((str = b.readLine()) != null)  {
				  queryString = queryString + str;
			      } 
			  }
			  catch(IOException e) {
			      e.printStackTrace();
			  }
		      }
		  });

	      Button btn2 = new Button("Select Output File");
	      btn2.setOnAction(even1 -> {
		      File file = fileChooser.showSaveDialog(stage);
		  });

	      Button btn1 = new Button(" OK ");
	      btn1.setOnAction(event1 -> {
		      String service = ep.getText();
		      if (queryString.equals("")) {
			      queryString = ta.getText();
		      }

		      Query query = QueryFactory.create(queryString);
		      QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
		      ResultSet results = qe.execSelect();
		      ResultSetFormatter.out(System.out, results, query);
		      qe.close();
		      mnuStage.close();
		  });
	      HBox hbx2 = new HBox();
	      hbx2.getChildren().addAll(new Label("   "), btn1);

              HBox hbx3 = new HBox();
	      hbx3.getChildren().addAll(new Label("   "), btn0);

	      HBox hbx4 = new HBox();
	      hbx4.getChildren().addAll(new Label("    "), btn2);

	      VBox vbox1 = new VBox();
	      vbox1.getChildren().addAll(new Label("    "), hbx1, new Label("    "), hbx3, new Label("    "), hbx4, new Label("    "), hbx2);
	      mnuStage.setScene(new Scene(vbox1));

	      mnuStage.show();
	      mnuStage.setX(mnuStage.getX() + 100);
	      mnuStage.setY(mnuStage.getY() + 100);
	  });

      /* <<< Web Menu ************************************************************************************** */

      /* >>> Str Analysis Menu ***************************************************************************** */
      /* *** mnuMolmil : Launcher of Molmil                                                      *********** */
      /* *************************************************************************************************** */

      mnuMolmil.setOnAction(event -> {

	      try {
		  URI uri = new URI(uriString);
		  desktop.browse(uri);
	      }
	      catch (Exception e)  {
		  e.printStackTrace();
	      }
	  });

      /* <<< Str Analysis Menu ***************************************************************************** */

      /* >>> Seq Analysis Menu ***************************************************************************** */
      /* *** mnuAAFreq : Selector of options to calculate amino acid composition                  ********** */
      /* *** mnuSqDist : Selector of a method to calculate sequence distances                     ********** */
      /* *** mnuCnsVar : Selector of a method to calculate sequence conservation/variation        ********** */
      /* *** mnuTaxWgt : Selector of a method to calculate weights for taxonomic bias             ********** */
      /* *** mnuNJtree : Selector for options of tree construction by Neighbor-Joining method     ********** */ 
      /* *** *********************************************************************************************** */

      mnuNJtree.setOnAction(event -> {
              // 新しいウィンドウを生成
              Stage mnuStage = new Stage();
	      mnuStage.setWidth(640);
	      mnuStage.setHeight(550);
              // モーダルウィンドウに設定
              mnuStage.initModality(Modality.APPLICATION_MODAL);
              // オーナーを設定
              mnuStage.initOwner(stage);
	      //新しいウィンドウ内に配置するコンテンツを生成
	      Label label1 = new Label("Bootstrap Iteration");
              TextField textField1 = new TextField("100");
	      Button btn1 = new Button("help");
              HBox bsbox = new HBox();
	      bsbox.getChildren().addAll(new Label("   "), label1, new Label("                   "), textField1, new Label("      "), btn1);
              Label label2 = new Label("Lower Limit of Distance to Hide Close Relationship");
	      TextField textField2 = new TextField("0.05");
	      Button btn2 = new Button("help");
              HBox llbox = new HBox();
	      llbox.getChildren().addAll(new Label("   "), label2, new Label("                   "), textField2, new Label("      "), btn2);	      
	      Label label3 = new Label("Default Max Number of Clusters to Be Shown");
	      TextField textField3 = new TextField("10");
	      Button btn3 = new Button("help");
              HBox dfbox = new HBox();
	      dfbox.getChildren().addAll(new Label("   "), label3, new Label("                   "), textField3, new Label("      "), btn3);
	      VBox vbox1 = new VBox();
	      Button btnOK = new Button("OK");
	      btnOK.setOnAction(event1 -> {
		      if (distM == null)  {
		      	  Alert alert = new Alert(AlertType.INFORMATION);
		       	  alert.setTitle("AlertType.INFORMATION");
		       	  alert.setHeaderText("No Available Distances!!");
		       	  alert.setContentText("Calculate sequence distamces before this operation");
		       	  alert.showAndWait();			  
		      }
		      else  {
			  njtreeBuilder tb = new njtreeBuilder(distM);
			  int bootstrapIteration = Integer.parseInt(textField1.getText());
			  double limitSize4Display = Double.parseDouble(textField2.getText());
			  int defaultClusterSize = Integer.parseInt(textField3.getText());

			  TreeDrawInfo tdi = new TreeDrawInfo(tb);
			  maxLength = tdi.getMaxLength()*2.0;
			  double bRatio = wSize*0.6/maxLength;
			  
			  // 新しいウィンドウを生成
			  Stage njStage = new Stage();
			  njStage.setTitle("njtree");
			  njStage.setWidth(wSize);
			  njStage.setHeight(wSize);
			  // モーダルウィンドウに設定 
			  njStage.initModality(Modality.APPLICATION_MODAL);
			  // オーナーを設定 
			  njStage.initOwner(stage);

			  final Canvas canvas = new Canvas(wSize,wSize);

			  GraphicsContext gc = canvas.getGraphicsContext2D();

			  gc.setLineWidth(1.0);
			  gc.setStroke(Color.RED);

			  for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
			      int nodeid = tb.getAncestralNodes()[i];
			      if ( nodeid == 0)  continue;
			      else   {
				  gc.strokeLine(tdi.getX1(i)*bRatio+wSize*0.4,tdi.getY1(i)*bRatio+wSize*0.4,tdi.getX2(i)*bRatio+wSize*0.4,tdi.getY2(i)*bRatio+wSize*0.4);
				  if (tb.getDescendantNodes()[i][0] != -1)  {
				      double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
				      double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
				      gc.strokeArc(wSize*0.4-tdi.getLength(i)*bRatio,wSize*0.4-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				      gc.strokeOval(wSize*0.4-2.5,wSize*0.4-2.5,5,5);
				  }
				  else {
				      gc.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.4,tdi.getY1(i)*bRatio+wSize*0.4);
				  }
			      }
			      root.getChildren().add(canvas);

			      njStage.setScene(new Scene(root, 300, 300));
			      njSstage.show();
		      }
		      mnuStage.close();
		  });
	      HBox okbox = new HBox();
	      okbox.getChildren().addAll(new Label("   "), btnOK);
	      vbox1.getChildren().addAll(new Label("    "), bsbox, new Label("    "), llbox, new Label("    "), dfbox, new Label("    "), okbox);
	      mnuStage.setScene(new Scene(vbox1));
              mnuStage.show();
              mnuStage.setX(mnuStage.getX() + 100);
              mnuStage.setY(mnuStage.getY() + 100);	      	      
	  });

      mnuAAFreq.setOnAction(event -> {
              // 新しいウィンドウを生成
              Stage mnuStage = new Stage();
	      mnuStage.setWidth(600);
	      mnuStage.setHeight(550);
              // モーダルウィンドウに設定
              mnuStage.initModality(Modality.APPLICATION_MODAL);
              // オーナーを設定
              mnuStage.initOwner(stage);
	      //新しいウィンドウ内に配置するコンテンツを生成
	      Label label1 = new Label("Score Matrix");
	      TextField textField1 = new TextField("HENS920102");
	      Button btn1 = new Button("help");
	      HBox sbox = new HBox();
	      sbox.getChildren().addAll(new Label("   "), label1, new Label("                     "), textField1, new Label("      "), btn1);

	      Label label2 = new Label("AA index");
	      TextField textField2 = new TextField("ROBINSON91");
	      Button btn2 = new Button("help");
	      HBox abox = new HBox();
	      abox.getChildren().addAll(new Label("   "), label2, new Label("                           "), textField2, new Label("     "), btn2);

	      Label label3 = new Label("Initial value for lambda");
	      TextField textField3 = new TextField("0.3176");
	      Button btn3 = new Button("help");
	      HBox lbox = new HBox();
	      lbox.getChildren().addAll(new Label("   "), label3, new Label("     "), textField3, new Label("     "), btn3);
	      
	      Label label4 = new Label("value for beta");
	      TextField textField4 = new TextField("0.1");
	      Button btn4 = new Button("help");
	      HBox bbox = new HBox();
	      bbox.getChildren().addAll(new Label("   "), label4, new Label("                   "), textField4, new Label("     "), btn4);

	      Label label5 = new Label("   Background AA composition"); 
	      ToggleGroup group1 = new ToggleGroup();
              RadioButton rbtn0 = new RadioButton("AA comp selected on this panel");
	      //	      rbtn0.setSelected(true);
	      //	      rbtn0.requestFocus();
	      rbtn0.setOnAction(event1 -> {
		      Ck = 0;
		  });
	      rbtn0.setToggleGroup(group1);
              RadioButton rbtn1 = new RadioButton("AA comp estimated from a given alignment");
	      rbtn1.setOnAction(event1 -> {
		      Ck = 1;
		  });
	      rbtn1.setToggleGroup(group1);
	      HBox rhbox = new HBox();
	      rhbox.getChildren().addAll(new Label("   "), rbtn0, new Label("          "), rbtn1);
	      VBox rvbox = new VBox();
	      rvbox.getChildren().addAll(new Label("   "), label5, new Label("   "), rhbox); 

	      Label label7 = new Label("Threshold for gap ratio");
	      TextField textField7 = new TextField("0.5");
	      Button btn7 = new Button("help");
	      HBox tbox = new HBox();
	      tbox.getChildren().addAll(new Label("   "), label7, new Label("   "), textField7, new Label("     "), btn7);

	      Label label8 = new Label("Threshold for Newton-Raphson");
	      TextField textField8 = new TextField("0.001");
	      Button btn8 = new Button("help");
	      HBox nrbox = new HBox();
	      nrbox.getChildren().addAll(new Label("   "), label8, new Label("   "), textField8, new Label("     "), btn8);

	      Label label9 = new Label("Minimum Number of Seq for AA comp estimation");
	      TextField textField9 = new TextField("20");
	      Button btn9 = new Button("help");
	      HBox mbox = new HBox();
	      mbox.getChildren().addAll(new Label("   "), label9, new Label("   "), textField9, new Label("     "), btn9);

	      Label label10 = new Label("Max Number of Iteration for Calculation of Lambda");
	      TextField textField10 = new TextField("1000");
	      Button btn10 = new Button("help");
	      HBox itrbox = new HBox();
	      itrbox.getChildren().addAll(new Label("   "), label10, new Label("   "), textField10, new Label("    "), btn10);
	      Button btn5 = new Button(" OK ");
	      btn5.setOnAction(event1 -> {
		      if (Ck == -1)  {			  
                          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("AlertType.INFORMATION");
                          alert.setHeaderText("Background AA comp is not selected!!");
                          alert.setContentText("Background AA comp is set to the one selected on this panel");
			  alert.showAndWait();
			  Ck = 0;
		      }
		      String scoreid = textField1.getText();
		      sid = new scoreIdx(scoreid);
		      scoreMtx = sid.getIdx();
		      String aaid = textField2.getText();
		      if (Ck == 0)  {
			  aid = new aaIdx(aaid);
			  aaIndex = aid.getIdx();
			  for (int i = 0;  i < 20 ; ++i)  {
			      if (aaIndex[i] < 0.0)  {
				  chk = -1;
				  break;
			      }
			      else  {
				  idxTotal += aaIndex[i];
			      }
			  }
		      }
		      else {
			  /*** AA comp estimated from alignment ***/
		      }

		      if (chk == -1)  {
                          Alert alert = new Alert(AlertType.INFORMATION);
                          alert.setTitle("AlertType.INFORMATION");
                          alert.setHeaderText("Invalid aaindex!!");
                          alert.setContentText("Selected aaindex includes nagative value(s)!!");
			  alert.showAndWait();
		      }
		      else {
			  for (int i = 0 ; i < 20 ; ++i)  aaIndex[i] /= idxTotal;
			  double sChk = 0.0;
                          for (int i = 0 ; i < 20 ; ++i)  {
			      for (int j = i ; j < 20 ; ++j)  {
				  sChk += aaIndex[i]*aaIndex[j]*scoreMtx[i][j];
			      } 
                          }
			  if (sChk > 0.0)  {
			      Alert alert = new Alert(AlertType.INFORMATION);
			      alert.setTitle("AlertType.INFORMATION");
			      alert.setHeaderText("Inappropriate combination of aaindex and score matrix!!");
			      alert.setContentText("Sum of p[i]*p[j]*score[i,j] should be negative !!");
			      alert.showAndWait();			  } 
		      }
		      ilambda = Double.parseDouble(textField3.getText());
		      maxItr = Integer.parseInt(textField10.getText());
		      beta = Double.parseDouble(textField4.getText());
		      thr1 = Double.parseDouble(textField7.getText());
		      thr2 = Integer.parseInt(textField9.getText());
                      eps = Double.parseDouble(textField8.getText());
		      if (ifseq == null)  {
			  Alert alert = new Alert(AlertType.INFORMATION);
			  alert.setTitle("AlertType.INFORMATION");
			  alert.setHeaderText("No available alignment");
			  alert.setContentText("Choose alignment file before this operation!!");
			  alert.showAndWait();
		      }
                      else if ( clinf == null) {
			  Alert alert = new Alert(AlertType.INFORMATION);
			  alert.setTitle("AlertType.INFORMATION");
			  alert.setHeaderText("No Available Cluster Information!!");
			  alert.setContentText("Choose cluster file before this operation");
			  alert.showAndWait();
		      }
		      else {
			  weighted_Freq = new wFrq(ifseq, swg, clinf, thr1, thr2);  

			  cLambda = new Calc_Lambda(ilambda, scoreMtx, aaIndex, maxItr, eps);

			  psaaComp = new Calc_psFrq(cLambda.getLambda(), weighted_Freq.getPsfqAll(), scoreMtx, aaIndex, weighted_Freq.getNc(), weighted_Freq.getgSite(), clinf, ifseq.getSeqLen(0), beta);
			  /*
			  for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
			      System.out.println(">> Cluster = " + i);
			      for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
				  System.out.println(">> site = " + j); 
				  for (int k = 0 ; k < 20 ; ++k)  {
				      System.out.println("  " + n2AA(k) + " : " + weighted_Freq.getPsfq(i,j,k));
				  }
			      }
			  }
			  */
		      }
		      mnuStage.close();
		  });
	      Button btn6 = new Button("Reset");
	      btn6.setOnAction(event1 -> {
		      textField1.setText("HENS920102");
		      textField2.setText("ROBINSON91");
		      textField3.setText("0.3176");
		      textField4.setText("0.1");
		  });
	      HBox btbox = new HBox();
	      btbox.getChildren().addAll(new Label("   "), btn5, new Label("        "), btn6);

	      VBox vbox1 = new VBox();

	      vbox1.getChildren().addAll(new Label("   "), sbox, new Label("     "), abox, new Label("             "), lbox, new Label("     "), bbox, new Label("    "), nrbox, new Label("     "), itrbox, new Label("     "), tbox, new Label("     "), mbox, new Label("    "), rvbox, new Label("     "), btbox);
              mnuStage.setScene(new Scene(vbox1));

              mnuStage.show();
              mnuStage.setX(mnuStage.getX() + 100);
              mnuStage.setY(mnuStage.getY() + 100);
	      
	  });

      mnuSqDist.setOnAction(event -> {
              // 新しいウィンドウを生成
              Stage mnuStage = new Stage();
	      mnuStage.setWidth(300);
	      mnuStage.setHeight(320);
              // モーダルウィンドウに設定
              mnuStage.initModality(Modality.APPLICATION_MODAL);
              // オーナーを設定
              mnuStage.initOwner(stage);
	      //新しいウィンドウ内に配置するコンテンツを生成
	      //	      int wgm;
	      ToggleGroup group1 = new ToggleGroup();

              RadioButton rbtn0 = new RadioButton("p-distance");
	      Button btn0 = new Button("help");
	      rbtn0.setToggleGroup(group1);
	      rbtn0.setOnAction(event1 -> {
		    if (ifseq == null)  {
		      	  Alert alert = new Alert(AlertType.INFORMATION);
		       	  alert.setTitle("AlertType.INFORMATION");
		       	  alert.setHeaderText("No Available Alignment!!");
		       	  alert.setContentText("Choose alignment file before this operation");
		       	  alert.showAndWait();
		    }
	            else distM = new DistM(ifseq, 0);
			      //	      else wgm = 3;
	      });
              HBox hbox0 = new HBox();
	      hbox0.getChildren().addAll(new Label("   "), rbtn0, new Label("   "), btn0);

	      RadioButton rbtn1 = new RadioButton("Poisson");
	      Button btn1 = new Button("help");
	      rbtn1.setOnAction(event1 -> {
		      //		      wgm = 4;
		    if (ifseq == null)  {
			  Alert alert = new Alert(AlertType.INFORMATION);
			  alert.setTitle("AlertType.INFORMATION");
			  alert.setHeaderText("No Available Alignment!!");
			  alert.setContentText("Choose alignment file before this operation");
			  alert.showAndWait();
		    }
		    else distM = new DistM(ifseq, 1);
			      // else wgm = 4;
	       });
	      //	      rbtn1.setSelected(true);
	      //              rbtn1.requestFocus();
	      rbtn1.setToggleGroup(group1);
              HBox hbox1 = new HBox();
	      hbox1.getChildren().addAll(new Label("   "), rbtn1, new Label("   "), btn1);

	      RadioButton rbtn2 = new RadioButton("ML");
	      Button btn2 = new Button("help");
	      rbtn2.setToggleGroup(group1);
	      rbtn2.setOnAction(event1 -> {
		    wgm = 5;
		    if (ifseq == null)  {
			  Alert alert = new Alert(AlertType.INFORMATION);
			  alert.setTitle("AlertType.INFORMATION");
		      	  alert.setHeaderText("No Available Alignment!!");
		       	  alert.setContentText("Choose alignment file before this operation");
		       	  alert.showAndWait();
	            }
	       });
              HBox hbox2 = new HBox();
	      hbox2.getChildren().addAll(new Label("   "), rbtn2, new Label("   "), btn2);	      

	      RadioButton rbtn3 = new RadioButton("ML+Gamma");
	      Button btn3 = new Button("help");
	      rbtn3.setToggleGroup(group1);
	      rbtn3.setOnAction(event1 -> {
		    wgm = 6;
		    if (ifseq == null)  {
			  Alert alert = new Alert(AlertType.INFORMATION);
			  alert.setTitle("AlertType.INFORMATION");
		       	  alert.setHeaderText("No Available Alignment!!");
		       	  alert.setContentText("Choose an alignment file before this operation");
		       	  alert.showAndWait();
	       	    }
	       });
              HBox hbox3 = new HBox();
	      hbox3.getChildren().addAll(new Label("   "), rbtn3, new Label("   "), btn3);
              Button btn = new Button(" OK ");
	      btn.setOnAction(event1 -> {
		      //		      if (wgm == 3 || wgm == 4) distM = new DistM(ifseq, wgm);
		      mnuStage.close();
	       });
              HBox hbox4 = new HBox();
              hbox4.getChildren().addAll(new Label("  "), btn);

              VBox vbox1 = new VBox();	      
              vbox1.setSpacing(3);
	      vbox1.getChildren().addAll(new Label("   "), new Label("   Method for Distance Calculation"), new Label("  "), hbox0,hbox1,hbox2,hbox3, new Label("   "), hbox4);
              mnuStage.setScene(new Scene(vbox1));

              mnuStage.show();
              mnuStage.setX(mnuStage.getX() + 100);
              mnuStage.setY(mnuStage.getY() + 100);
	  });

      mnuCnsVar.setOnAction(event -> {
              // 新しいウィンドウを生成
              Stage mnuStage = new Stage();
	      mnuStage.setWidth(450);
	      mnuStage.setHeight(450);
              // モーダルウィンドウに設定
              mnuStage.initModality(Modality.APPLICATION_MODAL);
              // オーナーを設定
              mnuStage.initOwner(stage);
	      //新しいウィンドウ内に配置するコンテンツを生成
	      VBox vbox1 = new VBox();
	      ToggleGroup group1 = new ToggleGroup();

	      RadioButton rbtn0 = new RadioButton("Valder-Thornton");
	      rbtn0.setSelected(true);
	      rbtn0.requestFocus();
	      rbtn0.setToggleGroup(group1);
	      rbtn0.setOnAction(event1 -> {
		      if (swg == null)  {
		      }
		      /*
                      if (sMtx == null)  {
		      }
		      */
		  });
	      Button help0 = new Button("help");
              HBox hbox0 = new HBox();
	      hbox0.getChildren().addAll(new Label("   "), rbtn0, new Label("        "), help0);

	      RadioButton rbtn1 = new RadioButton("Sander-Schneider");
	      rbtn1.setToggleGroup(group1);
	      Button help1 = new Button("help");
              HBox hbox1 = new HBox();
	      hbox1.getChildren().addAll(new Label("   "), rbtn1, new Label("      "), help1);

	      RadioButton rbtn2 = new RadioButton("Pei-Grishin");
	      rbtn2.setToggleGroup(group1);
	      Button help2 = new Button("help");
              HBox hbox2 = new HBox();
	      hbox2.getChildren().addAll(new Label("   "), rbtn2, new Label("                 "), help2);

	      RadioButton rbtn3 = new RadioButton("Horner-Pesole");
	      rbtn3.setToggleGroup(group1);
	      Button help3 = new Button("help");
              HBox hbox3 = new HBox();
	      hbox3.getChildren().addAll(new Label("   "), rbtn3, new Label("            "), help3);

	      RadioButton rbtn4 = new RadioButton("Pipel-Lancet");
	      rbtn4.setToggleGroup(group1);
	      Button help4 = new Button("help");
              HBox hbox4 = new HBox();
	      hbox4.getChildren().addAll(new Label("   "), rbtn4, new Label("                "), help4);

	      RadioButton rbtn5 = new RadioButton("Wu-Kabat");
	      rbtn5.setToggleGroup(group1);
	      Button help5 = new Button("help");
              HBox hbox5 = new HBox();
	      hbox5.getChildren().addAll(new Label("   "), rbtn5, new Label("                    "), help5);

	      RadioButton rbtn6 = new RadioButton("Pei-Grishin2");
	      rbtn6.setToggleGroup(group1);
	      Button help6 = new Button("help");
              HBox hbox6 = new HBox();
	      hbox6.getChildren().addAll(new Label("   "), rbtn6, new Label("                "), help6);

	      RadioButton rbtn7 = new RadioButton("Sander-Schneider2");
	      rbtn7.setToggleGroup(group1);
	      Button help7 = new Button("help");
              HBox hbox7 = new HBox();
	      hbox7.getChildren().addAll(new Label("   "), rbtn7, new Label("     "), help7);

	      RadioButton rbtn8 = new RadioButton("Gerstein-Altman");
	      rbtn8.setToggleGroup(group1);
	      Button help8 = new Button("help");
              HBox hbox8 = new HBox();
	      hbox8.getChildren().addAll(new Label("   "), rbtn8, new Label("          "), help8);

	      RadioButton rbtn9 = new RadioButton("Mirny-Shaknovich");
	      rbtn9.setToggleGroup(group1);
	      Button help9 = new Button("help");
              HBox hbox9 = new HBox();
	      hbox9.getChildren().addAll(new Label("   "), rbtn9, new Label("       "), help9);

	      RadioButton rbtn10 = new RadioButton("Williamson");
	      rbtn10.setToggleGroup(group1);
	      Button help10 = new Button("help");
              HBox hbox10 = new HBox();
	      hbox10.getChildren().addAll(new Label("   "), rbtn10, new Label("                   "), help10);

              Button btn = new Button(" OK ");
	      btn.setOnAction(event1 -> {
		      mnuStage.close();
			  });
              HBox hbox11 = new HBox();
              hbox11.getChildren().addAll(new Label("  "), btn);

              vbox1.setSpacing(5);
	      vbox1.getChildren().addAll(new Label("  "), new Label("   Method to Calculate Conseervation or Variation of Each Alignment Site"), new Label("   "), hbox0,hbox1,hbox2,hbox3,hbox4,hbox5,hbox6,hbox7,hbox8,hbox9,hbox10, new Label("   "), hbox11);
              mnuStage.setScene(new Scene(vbox1));

              mnuStage.show();
              mnuStage.setX(mnuStage.getX() + 100);
              mnuStage.setY(mnuStage.getY() + 100);
	  });

      mnuTaxWgt.setOnAction(event -> {
              // 新しいウィンドウを生成
              Stage mnuStage = new Stage();
	      mnuStage.setWidth(300);
	      mnuStage.setHeight(320);
              // モーダルウィンドウに設定
              mnuStage.initModality(Modality.APPLICATION_MODAL);
              // オーナーを設定
              mnuStage.initOwner(stage);
	      //新しいウィンドウ内に配置するコンテンツを生成
	      //	      int wgm;
	      VBox vbox1 = new VBox();
	      VBox vbox2 = new VBox();
	      wgm = 3;
	      ToggleGroup group1 = new ToggleGroup();
	      ToggleGroup group2 = new ToggleGroup();

	      RadioButton rbtn0 = new RadioButton("No Weight");
	      Button btn0 = new Button("help");
	      rbtn0.setToggleGroup(group1);
	      rbtn0.setOnAction(event1 -> {
		  wgm = 0;
		  swg = new SeqWeight(clinf);
	      });

	      RadioButton rbtn1 = new RadioButton("Henikoff-Henikoff");
	      Button btn1 = new Button("help");
	      rbtn1.setToggleGroup(group1);
	      rbtn1.setOnAction(event1 -> {
		  wgm = 1;
	      });

	      RadioButton rbtn2 = new RadioButton("Vingron-Argos");
	      Button btn2 = new Button("help");
	      rbtn2.setToggleGroup(group1);
	      rbtn2.setOnAction(event1 -> {
		  System.out.println(">>CHK");
		  if (wgm < 3)  {
		       Alert alert = new Alert(AlertType.INFORMATION);
		       alert.setTitle("AlertType.INFORMATION");
		       alert.setHeaderText("Calculation method is not selected!!");
		       alert.setContentText("Select one of the calculation methods of distances");
		       alert.showAndWait();
	           }
		   if (ifseq == null || clinf == null)  {
		        Alert alert = new Alert(AlertType.INFORMATION);
		        alert.setTitle("AlertType.INFORMATION");
		  	alert.setHeaderText("No Enough Data!!");
		      	alert.setContentText("Alignment and cluster data are required before this operation.");
		        alert.showAndWait();
		   }
		   else {
			if (distM == null)  {
		            Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("AlertType.INFORMATION");
			    alert.setHeaderText("No Available Distance Data!!");
			    alert.setContentText("Sequence distance data is required before this operation.");
			    alert.showAndWait();
			 }			  
			 else  swg = new SeqWeight(distM, clinf);
		    }
	        });	      

	      //	      rbtn2.requestFocus();
	      //	      rbtn2.setToggleGroup(group1);

              Button btn = new Button(" OK ");
	      btn.setOnAction(event1 -> {		      
		      mnuStage.close();
			  });

	      HBox hbox0 = new HBox();
	      hbox0.getChildren().addAll(new Label("   "), rbtn0, new Label("    "), btn0);
	      HBox hbox1 = new HBox();
	      hbox1.getChildren().addAll(new Label("   "), rbtn1, new Label("    "), btn1);
	      HBox hbox2 = new HBox();
	      hbox2.getChildren().addAll(new Label("   "), rbtn2, new Label("    "), btn2);
	      HBox hbox3 = new HBox();
	      hbox3.getChildren().addAll(new Label("    "), btn);
	      vbox1.setSpacing(5);
              vbox1.getChildren().addAll(new Label("   "), new Label("   Calculation Method for Seq Weight"), new Label("   "), hbox0, hbox1, hbox2, new Label("   "), hbox3, new Label("   "));

	      mnuStage.setScene(new Scene(vbox1));
	      
              mnuStage.show();
	      mnuStage.setX(mnuStage.getX() + 100);
	      mnuStage.setY(mnuStage.getY() + 100);
	  });

      /* <<< Seq Analysis Menu ***************************************************************************** */

      /* >>> File Menu ************************************************************************************* */
      /* *** mnuExSeqaFS : Chooser of an output file of an alignment                             *********** */
      /* *** mnuImClus   : Chooser of an input file for cluster information                      *********** */
      /* *** mnuImSeqCl  : Chooser of an input file for an alignment with Clustal format         *********** */
      /* *** mnuImSeqaFS : Chooser of an input file for an alignment with FASTA format           *********** */
      /* *** mnuImSequFS : Chooser of an input file for an unaligned sequences with FASTA format *********** */
      /* *************************************************************************************************** */

      mnuExSeqaFS.setOnAction(event -> {
	      File file = fileChooser.showSaveDialog(stage);
	      if (ifseq != null)  {
		  try {
		      FileWriter filewriter = new FileWriter(file);
		      BufferedWriter bw = new BufferedWriter(filewriter);
		      PrintWriter pw = new PrintWriter(bw);
		      for (int i = 0 ; i < ifseq.getSeqSize() ; ++i)  {
			  pw.println(">"+ifseq.getName(i));			
			  for (int j = 0 ; j < ifseq.getSeqLen(i) ; j += 60) {
			      int End = j + 60;
			      if (End > ifseq.getSeqLen(i)) End = ifseq.getSeqLen(i);
			      pw.println(ifseq.getSeq(i).substring(j,End));
			  }
		      }
		      pw.close();
		  }
		  catch (IOException e) {
		      e.printStackTrace();
		  }
	      }
	  });

      mnuImClus.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);

	      if (file != null)  {
	          String filename = file.getName();
	          clinf = new importCluster(filename);
                  rePaintL();
	          left.setLeft(canvasL);
	      }
	      if (ifseq != null)  {
		  cls = new ClusterSeq(ifseq, clinf, 0);
	          rePaintR();
		  right.setLeft(canvasR);
	      }
	  });

      mnuImSeqCl.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);

	      if (file != null)  {
		  String filename = file.getName();
		  ifseq = new readSeq(filename, 0);
	      }
	      if (clinf != null)  {
		  cls = new ClusterSeq(ifseq, clinf, 0);
                  rePaintL();
	          left.setLeft(canvasL);
		  rePaintR();
		  right.setLeft(canvasR);
	      }
	  });

      mnuImSeqaFS.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
	      if (file != null)  {
		  String filename = file.getName();
		  ifseq = new readSeq(filename, 1);
	      } 
	      if (clinf != null)  {
		  cls = new ClusterSeq(ifseq, clinf, 0);
                  rePaintL();
	          left.setLeft(canvasL);
		  rePaintR();
		  right.setLeft(canvasR);
	      }
	  });

      mnuImSequFS.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
	      if (file != null)  {
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
		      ifseq = new readSeq("dummy.txt", 1);
	              if (clinf != null)  {
		          cls = new ClusterSeq(ifseq, clinf, 0);
                          rePaintL();
	                  left.setLeft(canvasL);
		          rePaintR();
		          right.setLeft(canvasR);
	              }
                  }
	          catch (IOException | InterruptedException e) {
		      e.printStackTrace();
	          }
	      }
	  });

      /* <<< File Menu ************************************************************************************* */

      mnuExit.setOnAction(event -> System.exit(0));

      fileMenu.getItems().addAll(mnuImSequFS, mnuImSeqaFS, mnuImSeqCl, mnuImStr, mnuImTree, mnuImClus, mnuExSeqaFS, mnuExit);
      seqAnal.getItems().addAll(mnuSqDist, mnuTaxWgt, mnuAAFreq, mnuNJtree, mnuCnsVar, mnuFnDiff);

      strAnal.getItems().addAll(mnuMolmil);

      webAnal.getItems().addAll(mnuSparql, mnuScraping);

      menuBar.getMenus().addAll(fileMenu, seqAnal, strAnal, webAnal);
      
      root.getChildren().addAll(menuBar);

      HBox hbx = new HBox();
      left.setPrefSize(380, 600);
      right.setPrefSize(740, 600);

      ScrollPane sp1 = new ScrollPane(left);
      ScrollPane sp2 = new ScrollPane(right);
      sp1.setPrefSize(380, 600);
      sp2.setPrefSize(740, 600);

      hbx.getChildren().addAll(sp1, sp2);

      VBox vbx = new VBox();
      ta = new TextArea();
      ta.setPrefSize(1120,300);

      ScrollPane sp3 = new ScrollPane();
      sp3.setPrefSize(1120, 280);
      sp3.setContent(ta);
      vbx.getChildren().addAll(hbx, sp3);
     
      root.getChildren().addAll(vbx);
     
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

    private void rePaint(MouseEvent event)  {
	double x, y, x2, y2;

	x = event.getX();
	y = event.getY();

        for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	    if (x >= 10 && x <= 30 && y >= 34+i*25 && y <= 54+i*25) {
		if (c1[i] == ' ' && c2[i] == ' ')  {
		    c1[i] = 'X';
		    gcL.setStroke(Color.BLUE);
		    gcL.setFill(Color.BLUE);
		    gcL.fillRect(10,34+i*25,20,20);
		    gcL.strokeRect(10,34+i*25,20,20);
		    break;
		}
		else if (c1[i] != ' ' && c2[i] == ' ')  {
		    c1[i]=' ';
		    gcL.setStroke(Color.BLUE);
		    gcL.setFill(Color.WHITE);
		    gcL.fillRect(10,34+i*25,20,20);
		    gcL.strokeRect(10,34+i*25,20,20);
		    break;
		}
	    }
	    if (x >= 35 && x <= 55 && y >= 34+i*25 && y <= 54+i*25) {
		if (c1[i] == ' ' && c2[i] == ' ')  {
		    c2[i] = 'X';
		    gcL.setStroke(Color.ORANGE);
		    gcL.setFill(Color.ORANGE);
		    gcL.fillRect(35,34+i*25,20,20);
		    gcL.strokeRect(35,34+i*25,20,20);
		    break;
		}
		else if (c1[i] == ' ' && c2[i] != ' ')  {
		    c2[i]=' ';
		    gcL.setStroke(Color.ORANGE);
		    gcL.setFill(Color.WHITE);
		    gcL.fillRect(35,34+i*25,20,20);
		    gcL.strokeRect(35,34+i*25,20,20);
		    break;
		}
	    }
	}
	if (x >= 10 && x <= 92 && y >= 38+clinf.getClusterSize()*25 && y <= 58+clinf.getClusterSize()*25)  {
	    for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
		if (c1[i] != ' ')  {
		    c1[i] = ' ';
		    gcL.setStroke(Color.BLUE);
		    gcL.setFill(Color.WHITE);
		    gcL.fillRect(10,34+i*25,20,20);
		    gcL.strokeRect(10,34+i*25,20,20);
		}
                if (c2[i] != ' ')  {
		    c2[i] = ' ';
		    gcL.setStroke(Color.ORANGE);
		    gcL.setFill(Color.WHITE);
		    gcL.fillRect(35,34+i*25,20,20);
		    gcL.strokeRect(35,34+i*25,20,20);
		}
	    }
	}
	if (x >= 10 && x <= 92 && y >= 63+clinf.getClusterSize()*25 && y <= 83+clinf.getClusterSize()*25)  {
	    for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
		if (c1[i] == ' ')  {
		    c1[i] = 'X';
		    gcL.setStroke(Color.BLUE);
		    gcL.setFill(Color.BLUE);
		    gcL.fillRect(10,34+i*25,20,20);
		    gcL.strokeRect(10,34+i*25,20,20);
		}
		if (c2[i] != ' ')  {
		    c2[i] = ' ';
		    gcL.setStroke(Color.ORANGE);
		    gcL.setFill(Color.WHITE);
		    gcL.fillRect(35,34+i*25,20,20);
		    gcL.strokeRect(35,34+i*25,20,20);
		}
	    }
	}
    }

    void rePaintL()  {
	 c1 = new char[clinf.getClusterSize()];
	 c2 = new char[clinf.getClusterSize()];
         for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
  	      c1[i] = ' ';
       	      c2[i] = ' ';
	  }

	  canvasL = new Canvas((2*25 + 300), clinf.getClusterSize()*25+100);
	  gcL = canvasL.getGraphicsContext2D();
	  for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	      gcL.setLineWidth(2.0);
   	      gcL.setStroke(Color.BLUE);
       	      gcL.strokeRect(10,34+i*25,20,20);

       	      gcL.setStroke(Color.ORANGE);
       	      gcL.strokeRect(35,34+i*25,20,20);

       	      gcL.setFont(new Font(20));
       	      gcL.fillText(clinf.getClusterName(i), 65, 50 + i*25);
       	  }
	  gcL.setLineWidth(1.0);
	  gcL.setStroke(Color.BLACK);
       	  gcL.strokeRect(10, 38+clinf.getClusterSize()*25,82,20);
          gcL.setFont(new Font(18));
	  gcL.fillText("clear all",12,54+clinf.getClusterSize()*25);
	  gcL.setLineWidth(1.0);
	  gcL.setStroke(Color.BLACK);
	  gcL.strokeRect(10, 63+clinf.getClusterSize()*25,82,20);
          gcL.setFont(new Font(18));
	  gcL.fillText("select all",12,79+clinf.getClusterSize()*25);
    }

    void rePaintR()  {
	 canvasR = new Canvas((ifseq.getSeqLen(0)*25+50), (ifseq.getSeqSize()*25+50));
         gcR = canvasR.getGraphicsContext2D();
         for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	      for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
		 double Rc = cls.getFreq()[i][cls.getmxIdx()[i][j]][j];
		 double Bc = 1.0 - cls.getFreq()[i][cls.getmxIdx()[i][j]][j];
		 Color col;
		 if (cls.getmxIdx()[i][j]==20)  col = new Color(0.0,0.0,0.0,1.0);
		 else col = new Color(Rc, 0.0, Bc, 1.0);
		 gcR.setFill(col);
		 gcR.setFont(new Font(20));
		 gcR.fillText(cls.getcSeq().get(i).substring(j, j+1), 10+j*25, 50+i*25);
	      }
	 }

    }

    /*
    char n2AA(int a)  {
	String aa1="ARNDCQEGHILKMFPSTWYV";
	return aa1.charAt(a);
    }
    */
}

