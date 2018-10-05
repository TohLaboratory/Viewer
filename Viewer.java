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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Process;
import java.lang.ProcessBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.CheckBox;
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
import javafx.scene.shape.ArcType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/*
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.Prologue;
*/

public class Viewer extends Application  {
  private final Desktop desktop = Desktop.getDesktop(); 
  private readSeq ifseq;
  private importCluster clinf;
    //  private importCluster clinf2;
  private Canvas canvasL;
  private Canvas canvasR;
  private Canvas canvasNJ;
  private Canvas canvasCV;
  private GraphicsContext gcL;
  private GraphicsContext gcR;
  private GraphicsContext gcNJ;
  private GraphicsContext gcCV;
  private BorderPane left = new BorderPane();
  private BorderPane right = new BorderPane();
  private DistM distM;
  private SeqWeight swg;
  private char c1[], c2[];
  private ClusterSeq cls;
  private ClusterSeq cls2;
  private TreeClusterSeq tcls;
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
  private double maxLength;
  private int thr2, CVmethod;
  private Calc_Lambda cLambda;
  private Calc_psFrq psaaComp; 
  private int chk = 0;
  private double idxTotal = 0.0;
  private njtreeBuilder tb;
  private TreeDrawInfo  tdi;
  private BorderPane njt;
  private BorderPane cvp; 
  private nodeState[] nState;
  private ArrayList<Integer> currentNodeId = new ArrayList<Integer>();
  private LeftPanelTree lpt;
  private Stage njStage;
  private Stage cvStage;
  private double wSize;
  private rePaintLTree rplt;
  private ArrayList<Integer> currentList;
  private int leftMouse = 0;
  private double bRatio;
  private Scene njScene;
  private Scene cvScene;
  private double[] goCircle;
  private GOParse gop;
  private double cx, cy;
  private double SpaceForconcentricCircles = 0.01;
  private int Reuse = 0;
  private CnsVar cnsvar;
  private int[] IdOfUniprotId;
  private double minChk;
  private double maxChk;
  private ArrayList<String> academicName;
  private double y1;
  private String uriString1 = "https://www.google.com/search?q=";
  private String uriString2 = "&ie=utf-8&oe=utf-8";
  private String uriString3;
  private Connection connection;
  private Statement statement;
  private ResultSet rs;

  @Override
  public void start(Stage stage) throws Exception {
      stage.setTitle("Viewer");
      stage.setWidth(1120);
      stage.setHeight(880);
      

      left.setOnMouseClicked(event -> rePaint(event));
 
      final FileChooser fileChooser = new FileChooser();
      /*
      fileChooser.getExtensionFilters().add(
	 new FileChooser.ExtensionFilter("all file types", "*.*")
      );
      */
      VBox root = new VBox();
      
      MenuBar menuBar = new MenuBar();
      Menu fileMenu = new Menu("File");
      Menu seqAnal = new Menu("Seq Analysis");
      Menu strAnal = new Menu("Str Analysis");
      Menu webAnal = new Menu("Psurfer");

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
      /*
      MenuItem mnuSparql = new MenuItem("SPARQL");
      MenuItem mnuScraping = new MenuItem("Scraping");
      */
      MenuItem mnuSetting = new MenuItem("Setting");
      MenuItem mnuMolFunc = new MenuItem("molecular function");
      MenuItem mnuCelComp = new MenuItem("cellular component");
      MenuItem mnuBioProc = new MenuItem("biological process");
      MenuItem mnuSourceO = new MenuItem("source organisms");
      MenuItem mnuActSite = new MenuItem("active sites");
   
      /* >>> pSurfer Menu ********************************************************************************* */
      /* *** mnuSetting : Use pSurfer or Reuse Previous Result                                  *********** */
      /* *** mnuMolFunc : Draw Concentric Circles for Molecular Function on Unrooted Tree Panel *********** */
      /* *** mnuCelComp : Darw Concentric Circles for Cellular Component on Unrooted Tree Panel *********** */
      /* *** mnuBioProc : Draw Concentric Circles for Biological Process on Unrooted Tree Panel *********** */
      /* *** mnuSourceO : Draw Concentric Circles for Source Organisms on Unrooted Tree Panel   *********** */
      /* *** mnuActSite : Plot Active Sites on Conservation Chart Panel                         *********** */
      /* ************************************************************************************************** */

      mnuSourceO.setOnAction(event -> {
	      if (ifseq == null)  {
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("AlertType.INFORMATION");
		 alert.setHeaderText("No Available Alignment!!");
		 alert.setContentText("Alignment data is required for this operation");
		 alert.showAndWait();			  
		  
	      }
	      else {
		  try{
		      HashMap<String, ArrayList<Integer>> genusColorCode = new HashMap<String, ArrayList<Integer>>();
		      HashMap<String, ArrayList<Integer>> familyColorCode = new HashMap<String, ArrayList<Integer>>();
		      HashMap<String, ArrayList<Integer>> orderColorCode = new HashMap<String, ArrayList<Integer>>();
		      HashMap<String, ArrayList<Integer>> classColorCode = new HashMap<String, ArrayList<Integer>>();
		      HashMap<String, ArrayList<Integer>> phylumColorCode = new HashMap<String, ArrayList<Integer>>();
		      HashMap<String, ArrayList<Integer>> kingdomColorCode = new HashMap<String, ArrayList<Integer>>();
		      HashMap<String, ArrayList<Integer>> superkingdomColorCode = new HashMap<String, ArrayList<Integer>>();

		      FileWriter out = new FileWriter("search.json");
		      BufferedWriter out_b = new BufferedWriter(out);
		      out_b.write("[{\"idclass\":\"http://purl.uniprot.org/core/Protein\",");
		      out_b.newLine();
		      out_b.write("\"ids\":[");
		      for (int i = 0 ; i < ifseq.getSeqSize() ; ++i) {
			  if (ifseq.getName(i).substring(1,3).equals("sp") || ifseq.getName(i).substring(1,3).equals("tr"))  {
			      String[] namearray1 = ifseq.getName(i).split("\\|");
			      if (namearray1[1].length() != 6)  continue;
			      //			      String   namex2 = namearray1[2].split(" ")[0];
			      out_b.write("\"http://purl.uniprot.org/uniprot/");
			      out_b.write(namearray1[1]);
			      if (i < ifseq.getSeqSize() - 1) {
				  out_b.write("\",");
				  out_b.newLine();
			      }
			  }
		      }
		      out_b.write("\"]");
		      out_b.newLine();
		      out_b.write("}]");

		      out_b.close();
		      out.close();

		      Runtime runtime = Runtime.getRuntime();
		      if (Reuse == 0)   {
			  try {
			      Process p = runtime.exec("./Sys");
			      //			      Process p = runtime.exec("java -jar psurfer -Xms1g -Xmx1g psurfer.jar search.json output.json");
			      p.waitFor();
			  }
			  catch(InterruptedException e) {
			      e.printStackTrace();
			  }
		      }
		      gop = new GOParse("output.json");

		      /* (sub)species のデータを入手 */
		      academicName = gop.getAcademicName(); 
		      String[][] Taxonomy = new String[academicName.size()][8];

		      List<String> genusList = new ArrayList<String>();
		      List<String> familyList = new ArrayList<String>();
		      List<String> orderList = new ArrayList<String>();
		      List<String> classList = new ArrayList<String>();
		      List<String> phylumList = new ArrayList<String>();
		      List<String> kingdomList = new ArrayList<String>();
		      List<String> superkingdomList = new ArrayList<String>();

		      List<String> genusUniqueList;
		      List<String> familyUniqueList;
		      List<String> orderUniqueList;
		      List<String> classUniqueList;
		      List<String> phylumUniqueList;
		      List<String> kingdomUniqueList;
		      List<String> superkingdomUniqueList;
		      /*
		      for (int i = 0 ; i < academicName.size() ; ++i)  {
			  Taxonomy[i][0] = academicName.get(i);
			  System.out.println(Taxonomy[i][0]);
		      }
		      */
		      try {
			  Class.forName("org.sqlite.JDBC");
		      }
		      catch(ClassNotFoundException e) {
			  e.printStackTrace();
		      }
		      catch(Exception e) {
			  e.printStackTrace();
		      }

		      Connection connection = null;
		      Statement statement = null;
		      try {
			  connection = DriverManager.getConnection("jdbc:sqlite:/Users/toh/Desktop/Viewer/taxonomy");
			  statement = connection.createStatement();
			  statement.setQueryTimeout(30);			    
			  String[] genus = new String[academicName.size()];
			  String[] family = new String[academicName.size()];
			  String[] order = new String[academicName.size()];
			  String[] gclass = new String[academicName.size()];
			  String[] phylum = new String[academicName.size()];
			  String[] kingdom = new String[academicName.size()];
			  String[] superkingdom = new String[academicName.size()];
			  
			  for (int i = 0 ; i < academicName.size() ; ++i) {
			      String str = academicName.get(i).split(" ")[0];
			      String sql = "select * from tol where genus = \"" + str + "\" limit 1; ";
			      //			      System.out.println(sql);
			      ResultSet rs = statement.executeQuery(sql);
			      ResultSetMetaData rsmd = rs.getMetaData();
			      Taxonomy[i][1] = rs.getString("genus");
			      genus[i] = Taxonomy[i][1];
			      Taxonomy[i][2] = rs.getString("family");
			      family[i] = Taxonomy[i][2];
			      Taxonomy[i][3] = rs.getString("order");
			      order[i] = Taxonomy[i][3];
			      Taxonomy[i][4] = rs.getString("class");
			      gclass[i] = Taxonomy[i][4];
			      Taxonomy[i][5] = rs.getString("phylum");
			      phylum[i] = Taxonomy[i][5];
			      Taxonomy[i][6] = rs.getString("kingdom");
			      kingdom[i] = Taxonomy[i][6];
			      Taxonomy[i][7] = rs.getString("superkingdom");
			      superkingdom[i] = Taxonomy[i][7];
			  }
			  genusList = new ArrayList<String>(Arrays.asList(genus));
			  genusUniqueList = new ArrayList<String>(new HashSet<>(genusList));
			  familyList = new ArrayList<String>(Arrays.asList(family));
		          familyUniqueList = new ArrayList<String>(new HashSet<>(familyList));
			  orderList = new ArrayList<String>(Arrays.asList(order));
			  orderUniqueList = new ArrayList<String>(new HashSet<>(orderList));
			  classList = new ArrayList<String>(Arrays.asList(gclass));
			  classUniqueList = new ArrayList<String>(new HashSet<>(classList));
			  phylumList = new ArrayList<String>(Arrays.asList(phylum));
			  phylumUniqueList = new ArrayList<String>(new HashSet<>(phylumList));
			  kingdomList = new ArrayList<String>(Arrays.asList(kingdom));
			  kingdomUniqueList = new ArrayList<String>(new HashSet<>(kingdomList));
			  superkingdomList = new ArrayList<String>(Arrays.asList(superkingdom));
			  superkingdomUniqueList = new ArrayList<String>(new HashSet<>(superkingdomList));

			  genusColorCode = getColorCode(1, genusUniqueList);
			  /*
			  System.out.println("genus : " + genus.length + ":" + genusUniqueList.size());
			  for (int i = 0 ; i < genusUniqueList.size() ; ++i)  {
			      System.out.println(genusUniqueList.get(i));
			      ArrayList<Integer> mm = genusColorCode.get(genusUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */
			  familyColorCode = getColorCode(2, familyUniqueList);
			  /*
			  System.out.println("family : " + family.length + ":" + familyUniqueList.size());
			  for (int i = 0 ; i < familyUniqueList.size() ; ++i)  {
			      System.out.println(familyUniqueList.get(i));
			      ArrayList<Integer> mm = familyColorCode.get(familyUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */
			  orderColorCode = getColorCode(3, orderUniqueList);
			  /*
			  System.out.println("order : " + order.length + ":" + orderUniqueList.size());
			  for (int i = 0 ; i < orderUniqueList.size() ; ++i)  {
			      System.out.println(orderUniqueList.get(i));
			      ArrayList<Integer> mm = orderColorCode.get(orderUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */
			  classColorCode = getColorCode(4, classUniqueList);
			  /*
			  System.out.println("class : " + gclass.length + ":" + classUniqueList.size());
			  for (int i = 0 ; i < classUniqueList.size() ; ++i)  {
			      System.out.println(classUniqueList.get(i));
			      ArrayList<Integer> mm = classColorCode.get(classUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */
			  phylumColorCode = getColorCode(5, phylumUniqueList);
			  /*
			  System.out.println("phylum : " + phylum.length + ":" + phylumUniqueList.size());
			  for (int i = 0 ; i < phylumUniqueList.size() ; ++i)  {
			      System.out.println(phylumUniqueList.get(i));
			      ArrayList<Integer> mm = phylumColorCode.get(phylumUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */
			  kingdomColorCode = getColorCode(6, kingdomUniqueList);
			  /*
			  System.out.println("kingdom : " + kingdom.length + ":" + kingdomUniqueList.size());
			  for (int i = 0 ; i < kingdomUniqueList.size() ; ++i)  {
			      System.out.println(kingdomUniqueList.get(i));
			      ArrayList<Integer> mm = kingdomColorCode.get(kingdomUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */
			  superkingdomColorCode = getColorCode(7, superkingdomUniqueList); 
			  /*
			  System.out.println("superkingdom : " + superkingdom.length + ":" + superkingdomUniqueList.size());
			  for (int i = 0 ; i < superkingdomUniqueList.size() ; ++i)  {
			      System.out.println(superkingdomUniqueList.get(i));
			      ArrayList<Integer> mm = superkingdomColorCode.get(superkingdomUniqueList.get(i));
			      System.out.println(mm.get(0) + " : " + mm.get(1) + " : " + mm.get(2));
			  }
			  */

		      }  catch (SQLException e) {
			  System.err.println(e.getMessage());
		      }
                      /*        無根系統樹の描きなおし        */

                      Group root2 = new Group();

                      njt = new BorderPane();
                      //                      njt.setPrefSize(700, 700);
                      njt.setPrefSize(wSize, wSize);

                      //                      ScrollPane njsp = new ScrollPane(njt);

                      canvasNJ = new Canvas(wSize,wSize);
                      //                      final Canvas canvas = new Canvas(wSize,wSize);

                      gcNJ = canvasNJ.getGraphicsContext2D();
                      //                      GraphicsContext gc = canvas.getGraphicsContext2D();

                      gcNJ.setLineWidth(1.0);
                      gcNJ.setStroke(Color.RED);

                      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
                          int nodeid = tb.getAncestralNodes()[i];
                          if ( nodeid == 0)  continue;
                          else   {
                              //                System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 =\
			      //			      " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i));
                              switch(tdi.getnodeColor(i))  {
			          case 'W': gcNJ.setStroke(Color.WHITE);
                                            break;
			          case 'B':  gcNJ.setStroke(Color.BLUE);
                                            break;
                                  case 'Y':  gcNJ.setStroke(Color.YELLOW);
                                            break;
                                  case 'R':  gcNJ.setStroke(Color.RED);
                                            break;
                                  default :  gcNJ.setStroke(Color.RED);
                              }
                              gcNJ.strokeLine(tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5,tdi.getX2(i)*bRatio+wSize*0.5,tdi.getY2(i)*bRatio+wSize*0.5);
                              if (tb.getDescendantNodes()[i][0] != -1)  {
                                  double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
                                  double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
                                  gcNJ.strokeArc(wSize*0.5-tdi.getLength(i)*bRatio,wSize*0.5-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
                                  //                              gcNJ.strokeOval(wSize*0.5-2.5,wSize*0.5-2.5,5,5);
                              }
                              else {
                                  gcNJ.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5);
                              }
                          }
                      }

                      /* GOが得られたUniProtIDに対応するleafのID(-1)を決定する */
                      ArrayList<String> uniprotid = gop.getuniProtId();
                      IdOfUniprotId = new int[uniprotid.size()];
                      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
                          IdOfUniprotId[i] = -1;
                      }
                      for(int i = 0 ; i < uniprotid.size() ; ++i)  {
                          for (int j = 0 ; j < ifseq.getSeqSize() ; ++j) {
                              if (ifseq.getName(j).substring(1,3).equals("sp") || ifseq.getName(j).substring(1,3).equals("tr"))  {
                                  String[] namearray1 = ifseq.getName(j).split("\\|");
                                  if (namearray1[1].length() != 6)  continue;
                                  else {
                                      if (uniprotid.get(i).equals(namearray1[1]))  {
                                          IdOfUniprotId[i] = j;
                                      }
                                  }
                              }
                          }
                      }

                      /* GO 同心円を描くためにtdi.getLengthの最大値を調べる */
                      double maxLen = 0.0;
                      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ;  --i)  {
                          if (maxLen < tdi.getLength(i)) maxLen = tdi.getLength(i);
                      }

		      int arcNum = 8;
                      goCircle= new double[arcNum];
                      double angleSize = 360.0/tb.getleafNumber();
                      maxLen += 0.02;
                      double rad = maxLen;
		      for (int i = 0 ; i < arcNum ; ++i)  {
			  if ((i % 5) == 0)       gcNJ.setStroke(Color.rgb(100,250,100));
			  //			  if ((i % 5) == 0)       gcNJ.setStroke(Color.RED);
                          else if ((i % 5) == 1)  gcNJ.setStroke(Color.YELLOW);
                          else if ((i % 5) == 2)  gcNJ.setStroke(Color.BLUE);
                          else if ((i % 5) == 3)  gcNJ.setStroke(Color.GREEN);
                          else if ((i % 5) == 4)  gcNJ.setStroke(Color.BLACK);

                          gcNJ.setLineWidth(0.1);
                          rad += SpaceForconcentricCircles;;
                          goCircle[i] = rad;
                          gcNJ.strokeOval(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio);
		      }

		      minChk = maxLen;
		      maxChk = rad;

                      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
                          if (IdOfUniprotId[j] != -1)  {
			  /*   分類群の情報をsqliteで取り出す */
			      double ag = tdi.getAngle(IdOfUniprotId[j]);
			      System.out.println(IdOfUniprotId[j] + " : " + ag); /* chk */
			      gcNJ.setLineWidth(2.0);
			      rad = maxLen;
			      ArrayList<Integer> mm;
			      for (int k = 0 ; k < arcNum ; ++k)  {
				  /* 色の設定 */
				  switch(k) {
				     case 0: mm =  genusColorCode.get(genusList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				     case 1: mm =  familyColorCode.get(familyList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				     case 2: mm =  orderColorCode.get(orderList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				     case 3: mm =  classColorCode.get(classList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				     case 4: mm =  phylumColorCode.get(phylumList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				     case 5: mm =  kingdomColorCode.get(kingdomList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				     case 6: mm =  superkingdomColorCode.get(superkingdomList.get(j));
					     gcNJ.setStroke(Color.rgb(mm.get(0).intValue(), mm.get(1).intValue(), mm.get(2).intValue()));
					     break;
				  }
				  /* 弧を描く */
				  rad += SpaceForconcentricCircles;
				  gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio,
					     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
			      }
			  }
		      }
		      

                      njt.setLeft(canvasNJ);

                      //                      root2.getChildren().add(njsp);
                      root2.getChildren().add(canvasNJ);

                      njScene = new Scene(root2, wSize, wSize);
                      //                      Scene njScene = new Scene(root2, wSize, wSize);

                      njScene.setOnMouseClicked(eventNJ -> getInfoSO(eventNJ)); /*  check */

                      njStage.setScene(njScene);
                      //                      njScene.getStylesheets().add(Viewer.class.getResource("Viewer.css").toExternalForm());

                      njStage.show();
                      njStage.setX(njStage.getX() + 100);
                      njStage.setY(njStage.getY() + 100);
 
		  }
		  catch(Exception e) {
		      e.printStackTrace();
		  }
	      }
	  });

      mnuActSite.setOnAction(event -> {
	       try{
	           FileWriter out = new FileWriter("search.json");
	           BufferedWriter out_b = new BufferedWriter(out);
	           out_b.write("[{\"idclass\":\"http://purl.uniprot.org/core/Protein\",");
		   out_b.newLine();
		   out_b.write("\"ids\":[");
		   for (int i = 0 ; i < ifseq.getSeqSize() ; ++i) {
		       if (ifseq.getName(i).substring(1,3).equals("sp") || ifseq.getName(i).substring(1,3).equals("tr"))  {
		           String[] namearray1 = ifseq.getName(i).split("\\|");
			   if (namearray1[1].length() != 6)  continue;
			      //			      String   namex2 = namearray1[2].split(" ")[0];
			   out_b.write("\"http://purl.uniprot.org/uniprot/");
			   out_b.write(namearray1[1]);
			   if (i < ifseq.getSeqSize() - 1) {
				  out_b.write("\",");
				  out_b.newLine();
			   }
		        }
		   }
		   out_b.write("\"]");
		   out_b.newLine();
		   out_b.write("}]");

		   out_b.close();
		   out.close();

		   Runtime runtime = Runtime.getRuntime();
		   if (Reuse == 0)   {
		       try {
		           Process p = runtime.exec("./Sys");
			      //			      Process p = runtime.exec("java -jar psurfer -Xms1g -Xmx1g psurfer.jar search.json output.json");
		           p.waitFor();
		       }
		       catch(InterruptedException e) {
			      e.printStackTrace();
		       }
		   }
		   gop = new GOParse("output.json");


		   Group root4 = new Group();
		   cvp = new BorderPane();
		   cvp.setPrefSize(900, 700);
		   canvasCV = new Canvas(900, 700);
		   gcCV = canvasCV.getGraphicsContext2D();
                   gcCV.setLineWidth(1.0);

		   double ys = 500.0/clinf.getClusterSize();
		   double xs = 800.0/ifseq.getSeqLen(0);
		   for (int i = 0 ; i < clinf.getClusterSize() ; ++i) {
		       int member = clinf.getCluster().get(i).get(0).intValue();
		       char nodecolor = tdi.getColorCode(member);
		       switch(nodecolor)  {
		            case 'B':  gcCV.setStroke(Color.BLUE); 
			               break;
		            case 'R':  gcCV.setStroke(Color.RED);
			               break;
		            case 'Y':  gcCV.setStroke(Color.YELLOW);
			               break;
		       }
		       gcCV.strokeLine(50.0, (i+1)*ys, 850.0, (i+1)*ys);
		       for (int j = 50 ; j < 850 ; j += 50) {
			   gcCV.strokeLine(j*xs, (i+1)*ys, j*xs, (i+1)*ys-10);
		       }
		       for (int j = 1 ; j < ifseq.getSeqLen(0) ; j++) {
			   gcCV.strokeLine(50 + (j-1)*xs, -cnsvar.getCnsvar().get(i).get(j-1).doubleValue()*ys*0.4 + (i+1)*ys, 50 + j*xs, -cnsvar.getCnsvar().get(i).get(j).doubleValue()*ys*0.4 + (i+1)*ys);
		       } 
		   }

		   root4.getChildren().add(canvasCV);
		   cvScene = new Scene(root4, 900, 700);
		   cvStage.setScene(cvScene);
		   cvStage.show();
		   cvStage.setX(cvStage.getX() + 100);
		   cvStage.setY(cvStage.getY() + 100);
	       } catch(IOException e) {
		   e.printStackTrace();
	       }
	  });

      mnuSetting.setOnAction(event -> {
	      // 新しいウィンドウを生成
	      Stage mnuStage = new Stage();
	      mnuStage.setWidth(600);
	      mnuStage.setHeight(550);
	      // モーダルウィンドウに設定
	      mnuStage.initModality(Modality.APPLICATION_MODAL);
	      // オーナーを設定
	      mnuStage.initOwner(stage);
	      // 新しいウィンドウ内に配置するコンテンツを生成
	      Label label1 = new Label("Space between Neighboring Concentric Circles");
	      TextField textField1 = new TextField("0.02");
	      Button btn1 = new Button("help");
	      HBox hbox1 = new HBox();
	      hbox1.getChildren().addAll(new Label("   "), label1, new Label("   "), textField1, new Label("    "), btn1);

	      CheckBox checkBox2 = new CheckBox("ReUse of Psurfer Output");
	      Reuse = 0;
	      checkBox2.setOnAction(event2 -> {
		      Reuse = 1;
		  });
	      Button btn2 = new Button("help");
	      HBox hbox2 = new HBox();
	      hbox2.getChildren().addAll(new Label("   "), checkBox2, btn2);
	      Button btn3 = new Button("OK");
	      btn3.setOnAction(event3 -> {
		      SpaceForconcentricCircles = Double.parseDouble(textField1.getText());  
		      mnuStage.close();
		  });
	      HBox hbox3 = new HBox();
	      hbox3.getChildren().addAll(new Label("   "), btn3);
	      VBox vbox1 = new VBox();
	      vbox1.getChildren().addAll(new Label("  "), hbox1, new Label("  "), hbox2, new Label("  "),hbox3);
	      mnuStage.setScene(new Scene(vbox1));
	      mnuStage.show();
	  });


      mnuMolFunc.setOnAction(event -> {
	      if (ifseq == null)  {
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("AlertType.INFORMATION");
		 alert.setHeaderText("No Available Alignment!!");
		 alert.setContentText("Alignment data is required for this operation");
		 alert.showAndWait();			  
		  
	      }
	      else {
		  try{
		      FileWriter out = new FileWriter("search.json");
		      BufferedWriter out_b = new BufferedWriter(out);
		      out_b.write("[{\"idclass\":\"http://purl.uniprot.org/core/Protein\",");
		      out_b.newLine();
		      out_b.write("\"ids\":[");
		      for (int i = 0 ; i < ifseq.getSeqSize() ; ++i) {
			  if (ifseq.getName(i).substring(1,3).equals("sp") || ifseq.getName(i).substring(1,3).equals("tr"))  {
			      String[] namearray1 = ifseq.getName(i).split("\\|");
			      if (namearray1[1].length() != 6)  continue;
			      //			      String   namex2 = namearray1[2].split(" ")[0];
			      out_b.write("\"http://purl.uniprot.org/uniprot/");
			      out_b.write(namearray1[1]);
			      if (i < ifseq.getSeqSize() - 1) {
				  out_b.write("\",");
				  out_b.newLine();
			      }
			  }
		      }
		      out_b.write("\"]");
		      out_b.newLine();
		      out_b.write("}]");

		      out_b.close();
		      out.close();

		      Runtime runtime = Runtime.getRuntime();
		      if (Reuse == 0)   {
			  try {
			      Process p = runtime.exec("./Sys");
			      //			      Process p = runtime.exec("java -jar psurfer -Xms1g -Xmx1g psurfer.jar search.json output.json");
			      p.waitFor();
			  }
			  catch(InterruptedException e) {
			      e.printStackTrace();
			  }
		      }
		      gop = new GOParse("output.json");

		      /*	無根系統樹の描きなおし	      */

		      Group root2 = new Group();

		      njt = new BorderPane();
		      //		      njt.setPrefSize(700, 700);
		      njt.setPrefSize(wSize, wSize);

		      //		      ScrollPane njsp = new ScrollPane(njt);

		      canvasNJ = new Canvas(wSize,wSize);
		      //		      final Canvas canvas = new Canvas(wSize,wSize);
	
		      gcNJ = canvasNJ.getGraphicsContext2D();
		      //		      GraphicsContext gc = canvas.getGraphicsContext2D();

		      gcNJ.setLineWidth(1.0);
		      gcNJ.setStroke(Color.RED);

		      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
			  int nodeid = tb.getAncestralNodes()[i]; 
			  if ( nodeid == 0)  continue;
			  else   {
			      //		System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 = " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i)); 
			      switch(tdi.getnodeColor(i))  {
			          case 'W':  gcNJ.setStroke(Color.WHITE);
				            break;
			          case 'B':  gcNJ.setStroke(Color.BLUE);
				            break;
			          case 'Y':  gcNJ.setStroke(Color.YELLOW);
				            break;
			          case 'R':  gcNJ.setStroke(Color.RED);
				            break;
			          default :  gcNJ.setStroke(Color.RED);
			      }
			      gcNJ.strokeLine(tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5,tdi.getX2(i)*bRatio+wSize*0.5,tdi.getY2(i)*bRatio+wSize*0.5);
			      if (tb.getDescendantNodes()[i][0] != -1)  {
				  double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
				  double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
				  gcNJ.strokeArc(wSize*0.5-tdi.getLength(i)*bRatio,wSize*0.5-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				  //				  gcNJ.strokeOval(wSize*0.5-2.5,wSize*0.5-2.5,5,5);
			      }
			      else {
				  gcNJ.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5);
			      }
			  }	    
		      }


		      /* GOが得られたUniProtIDに対応するleafのID(-1)を決定する */
                      ArrayList<String> uniprotid = gop.getuniProtId();
                      int[] IdOfUniprotId = new int[uniprotid.size()];
		      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
			  IdOfUniprotId[i] = -1;
		      }  
		      for(int i = 0 ; i < uniprotid.size() ; ++i)  {
			  for (int j = 0 ; j < ifseq.getSeqSize() ; ++j) {
			      if (ifseq.getName(j).substring(1,3).equals("sp") || ifseq.getName(j).substring(1,3).equals("tr"))  {
				  String[] namearray1 = ifseq.getName(j).split("\\|");
				  if (namearray1[1].length() != 6)  continue;
				  else {
				      if (uniprotid.get(i).equals(namearray1[1]))  {
					  IdOfUniprotId[i] = j;
				      }
				  }
			      }
			  }
		      }
		      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
			  System.out.println(IdOfUniprotId[i] + " : " + uniprotid.get(i) + " : " + ifseq.getName(IdOfUniprotId[i]));
		      }

		      /* GOの要約テーブルを入手 */
		      int[][] mfArray = gop.getmfArray();
		      //		      int[][] ccArray = gop.getccArray();

		      /* GO 同心円を描くためにtdi.getLengthの最大値を調べる */
		      double maxLen = 0.0;
                      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ;  --i)  {
			  if (maxLen < tdi.getLength(i)) maxLen = tdi.getLength(i);
		      }
		      /* 同心円の数を決定する */
		      //		      int arcNum = gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize();
		      int arcNum = gop.getuniquemolecularFunctionSize();
		      goCircle= new double[arcNum];
                      double angleSize = 360.0/tb.getleafNumber(); 
		      maxLen += 0.02;
		      double rad = maxLen;
		      for (int i = 0 ; i < arcNum ; ++i)  {
			  if ((i % 5) == 0)       gcNJ.setStroke(Color.RED);
			  else if ((i % 5) == 1)  gcNJ.setStroke(Color.YELLOW);
			  else if ((i % 5) == 2)  gcNJ.setStroke(Color.BLUE);
			  else if ((i % 5) == 3)  gcNJ.setStroke(Color.GREEN);
			  else if ((i % 5) == 4)  gcNJ.setStroke(Color.BLACK);

			  gcNJ.setLineWidth(0.1);
		      	  rad += SpaceForconcentricCircles;;
			  goCircle[i] = rad;
		          gcNJ.strokeOval(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio);
			  //			  if (i < gop.getuniquemolecularFunctionSize())  {
			      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
				  if (mfArray[j][i] == 1)  {
				      double ag = tdi.getAngle(IdOfUniprotId[j]);
				      gcNJ.setLineWidth(2.0);
				      gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio, 
						     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
				  }
			      }
			      /*
			  } 
			  else {
			      int cci = i - gop.getuniquemolecularFunctionSize();
			      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
				  if (ccArray[j][cci] == 1)  {
				      double ag = tdi.getAngle(IdOfUniprotId[j]);
				      gcNJ.setLineWidth(2.0);
				      gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio, 
						     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
				  }
			      }			      
			  }
			      */
		      }

		      njt.setLeft(canvasNJ);

		      //		      root2.getChildren().add(njsp);
		      root2.getChildren().add(canvasNJ);
		      
		      njScene = new Scene(root2, wSize, wSize);
		      //		      Scene njScene = new Scene(root2, wSize, wSize);

		      njScene.setOnMouseClicked(eventNJ -> getInfoMF(eventNJ)); /*  check */

		      njStage.setScene(njScene);
		      //		      njScene.getStylesheets().add(Viewer.class.getResource("Viewer.css").toExternalForm());

		      njStage.show();
		      njStage.setX(njStage.getX() + 100);
		      njStage.setY(njStage.getY() + 100);	      	      

		      
		  }
		  catch(IOException e) {
		      System.out.println("Output Error");
		  }
	      }
	  });

      mnuCelComp.setOnAction(event -> {
	      if (ifseq == null)  {
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("AlertType.INFORMATION");
		 alert.setHeaderText("No Available Alignment!!");
		 alert.setContentText("Alignment data is required for this operation");
		 alert.showAndWait();			  
		  
	      }
	      else {
		  try{
		      FileWriter out = new FileWriter("search.json");
		      BufferedWriter out_b = new BufferedWriter(out);
		      out_b.write("[{\"idclass\":\"http://purl.uniprot.org/core/Protein\",");
		      out_b.newLine();
		      out_b.write("\"ids\":[");
		      for (int i = 0 ; i < ifseq.getSeqSize() ; ++i) {
			  if (ifseq.getName(i).substring(1,3).equals("sp") || ifseq.getName(i).substring(1,3).equals("tr"))  {
			      String[] namearray1 = ifseq.getName(i).split("\\|");
			      if (namearray1[1].length() != 6)  continue;
			      //			      String   namex2 = namearray1[2].split(" ")[0];
			      out_b.write("\"http://purl.uniprot.org/uniprot/");
			      out_b.write(namearray1[1]);
			      if (i < ifseq.getSeqSize() - 1) {
				  out_b.write("\",");
				  out_b.newLine();
			      }
			  }
		      }
		      out_b.write("\"]");
		      out_b.newLine();
		      out_b.write("}]");

		      out_b.close();
		      out.close();

		      Runtime runtime = Runtime.getRuntime();
		      if(Reuse == 0)  {
			  try {
			      Process p = runtime.exec("./Sys");
			      p.waitFor();
			  }
			  catch(InterruptedException e) {
			      e.printStackTrace();
			  }
		      }
		      
		      gop = new GOParse("output.json");

		      /*	無根系統樹の描きなおし	      */

		      Group root2 = new Group();

		      njt = new BorderPane();
		      //		      njt.setPrefSize(700, 700);
		      njt.setPrefSize(wSize, wSize);

		      //		      ScrollPane njsp = new ScrollPane(njt);

		      canvasNJ = new Canvas(wSize,wSize);
		      //		      final Canvas canvas = new Canvas(wSize,wSize);
	
		      gcNJ = canvasNJ.getGraphicsContext2D();
		      //		      GraphicsContext gc = canvas.getGraphicsContext2D();

		      gcNJ.setLineWidth(1.0);
		      gcNJ.setStroke(Color.RED);

		      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
			  int nodeid = tb.getAncestralNodes()[i]; 
			  if ( nodeid == 0)  continue;
			  else   {
			      //		System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 = " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i)); 
			      switch(tdi.getnodeColor(i))  {
			          case 'W':  gcNJ.setStroke(Color.WHITE);
				            break;
			          case 'B':  gcNJ.setStroke(Color.BLUE);
				            break;
			          case 'Y':  gcNJ.setStroke(Color.YELLOW);
				            break;
			          case 'R':  gcNJ.setStroke(Color.RED);
				            break;
			          default :  gcNJ.setStroke(Color.RED);
			      }
			      gcNJ.strokeLine(tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5,tdi.getX2(i)*bRatio+wSize*0.5,tdi.getY2(i)*bRatio+wSize*0.5);
			      if (tb.getDescendantNodes()[i][0] != -1)  {
				  double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
				  double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
				  gcNJ.strokeArc(wSize*0.5-tdi.getLength(i)*bRatio,wSize*0.5-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				  //				  gcNJ.strokeOval(wSize*0.5-2.5,wSize*0.5-2.5,5,5);
			      }
			      else {
				  gcNJ.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5);
			      }
			  }	    
		      }


		      /* GOが得られたUniProtIDに対応するleafのID(-1)を決定する */
                      ArrayList<String> uniprotid = gop.getuniProtId();
                      int[] IdOfUniprotId = new int[uniprotid.size()];
		      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
			  IdOfUniprotId[i] = -1;
		      }  
		      for(int i = 0 ; i < uniprotid.size() ; ++i)  {
			  for (int j = 0 ; j < ifseq.getSeqSize() ; ++j) {
			      if (ifseq.getName(j).substring(1,3).equals("sp") || ifseq.getName(j).substring(1,3).equals("tr"))  {
				  String[] namearray1 = ifseq.getName(j).split("\\|");
				  if (namearray1[1].length() != 6)  continue;
				  else {
				      if (uniprotid.get(i).equals(namearray1[1]))  {
					  IdOfUniprotId[i] = j;
				      }
				  }
			      }
			  }
		      }
		      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
			  System.out.println(IdOfUniprotId[i] + " : " + uniprotid.get(i) + " : " + ifseq.getName(IdOfUniprotId[i]));
		      }

		      /* GOの要約テーブルを入手 */
		      int[][] ccArray = gop.getccArray();
		      //		      int[][] ccArray = gop.getccArray();

		      /* GO 同心円を描くためにtdi.getLengthの最大値を調べる */
		      double maxLen = 0.0;
                      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ;  --i)  {
			  if (maxLen < tdi.getLength(i)) maxLen = tdi.getLength(i);
		      }
		      /* 同心円の数を決定する */
		      //		      int arcNum = gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize();
		      int arcNum = gop.getuniquecellularComponentSize();
		      goCircle= new double[arcNum];
                      double angleSize = 360.0/tb.getleafNumber(); 
		      maxLen += 0.02;
		      double rad = maxLen;
		      for (int i = 0 ; i < arcNum ; ++i)  {
			  if ((i % 5) == 0)       gcNJ.setStroke(Color.RED);
			  else if ((i % 5) == 1)  gcNJ.setStroke(Color.YELLOW);
			  else if ((i % 5) == 2)  gcNJ.setStroke(Color.BLUE);
			  else if ((i % 5) == 3)  gcNJ.setStroke(Color.GREEN);
			  else if ((i % 5) == 4)  gcNJ.setStroke(Color.BLACK);

			  gcNJ.setLineWidth(0.1);
		      	  rad += SpaceForconcentricCircles;;
			  goCircle[i] = rad;
		          gcNJ.strokeOval(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio);
			  //			  if (i < gop.getuniquemolecularFunctionSize())  {
			      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
				  if (ccArray[j][i] == 1)  {
				      double ag = tdi.getAngle(IdOfUniprotId[j]);
				      gcNJ.setLineWidth(2.0);
				      gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio, 
						     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
				  }
			      }
			      /*
			  } 
			  else {
			      int cci = i - gop.getuniquemolecularFunctionSize();
			      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
				  if (ccArray[j][cci] == 1)  {
				      double ag = tdi.getAngle(IdOfUniprotId[j]);
				      gcNJ.setLineWidth(2.0);
				      gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio, 
						     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
				  }
			      }			      
			  }
			      */
		      }

		      njt.setLeft(canvasNJ);

		      //		      root2.getChildren().add(njsp);
		      root2.getChildren().add(canvasNJ);
		      
		      njScene = new Scene(root2, wSize, wSize);
		      //		      Scene njScene = new Scene(root2, wSize, wSize);

		      njScene.setOnMouseClicked(eventNJ -> getInfoCC(eventNJ)); /*  check */

		      njStage.setScene(njScene);
		      //		      njScene.getStylesheets().add(Viewer.class.getResource("Viewer.css").toExternalForm());

		      njStage.show();
		      njStage.setX(njStage.getX() + 100);
		      njStage.setY(njStage.getY() + 100);	      	      

		      
		  }
		  catch(IOException e) {
		      System.out.println("Output Error");
		  }
	      }
	  });

      mnuBioProc.setOnAction(event -> {
	      if (ifseq == null)  {
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setTitle("AlertType.INFORMATION");
		 alert.setHeaderText("No Available Alignment!!");
		 alert.setContentText("Alignment data is required for this operation");
		 alert.showAndWait();			  
		  
	      }
	      else {
		  try{
		      FileWriter out = new FileWriter("search.json");
		      BufferedWriter out_b = new BufferedWriter(out);
		      out_b.write("[{\"idclass\":\"http://purl.uniprot.org/core/Protein\",");
		      out_b.newLine();
		      out_b.write("\"ids\":[");
		      for (int i = 0 ; i < ifseq.getSeqSize() ; ++i) {
			  if (ifseq.getName(i).substring(1,3).equals("sp") || ifseq.getName(i).substring(1,3).equals("tr"))  {
			      String[] namearray1 = ifseq.getName(i).split("\\|");
			      if (namearray1[1].length() != 6)  continue;
			      //			      String   namex2 = namearray1[2].split(" ")[0];
			      out_b.write("\"http://purl.uniprot.org/uniprot/");
			      out_b.write(namearray1[1]);
			      if (i < ifseq.getSeqSize() - 1) {
				  out_b.write("\",");
				  out_b.newLine();
			      }
			  }
		      }
		      out_b.write("\"]");
		      out_b.newLine();
		      out_b.write("}]");

		      out_b.close();
		      out.close();

		      Runtime runtime = Runtime.getRuntime();
		      if (Reuse == 0)  {
			  try {
			      Process p = runtime.exec("./Sys");
			      p.waitFor();
			  }
			  catch(InterruptedException e) {
			  e.printStackTrace();
			  }
		      }
		      
		      gop = new GOParse("output.json");

		      /*	無根系統樹の描きなおし	      */

		      Group root2 = new Group();

		      njt = new BorderPane();
		      //		      njt.setPrefSize(700, 700);
		      njt.setPrefSize(wSize, wSize);

		      //		      ScrollPane njsp = new ScrollPane(njt);

		      canvasNJ = new Canvas(wSize,wSize);
		      //		      final Canvas canvas = new Canvas(wSize,wSize);
	
		      gcNJ = canvasNJ.getGraphicsContext2D();
		      //		      GraphicsContext gc = canvas.getGraphicsContext2D();

		      gcNJ.setLineWidth(1.0);
		      gcNJ.setStroke(Color.RED);

		      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
			  int nodeid = tb.getAncestralNodes()[i]; 
			  if ( nodeid == 0)  continue;
			  else   {
			      //		System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 = " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i)); 
			      switch(tdi.getnodeColor(i))  {
			          case 'W':  gcNJ.setStroke(Color.WHITE);
				            break;
			          case 'B':  gcNJ.setStroke(Color.BLUE);
				            break;
			          case 'Y':  gcNJ.setStroke(Color.YELLOW);
				            break;
			          case 'R':  gcNJ.setStroke(Color.RED);
				            break;
			          default :  gcNJ.setStroke(Color.RED);
			      }
			      gcNJ.strokeLine(tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5,tdi.getX2(i)*bRatio+wSize*0.5,tdi.getY2(i)*bRatio+wSize*0.5);
			      if (tb.getDescendantNodes()[i][0] != -1)  {
				  double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
				  double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
				  gcNJ.strokeArc(wSize*0.5-tdi.getLength(i)*bRatio,wSize*0.5-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				  //				  gcNJ.strokeOval(wSize*0.5-2.5,wSize*0.5-2.5,5,5);
			      }
			      else {
				  gcNJ.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5);
			      }
			  }	    
		      }


		      /* GOが得られたUniProtIDに対応するleafのID(-1)を決定する */
                      ArrayList<String> uniprotid = gop.getuniProtId();
                      int[] IdOfUniprotId = new int[uniprotid.size()];
		      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
			  IdOfUniprotId[i] = -1;
		      }  
		      for(int i = 0 ; i < uniprotid.size() ; ++i)  {
			  for (int j = 0 ; j < ifseq.getSeqSize() ; ++j) {
			      if (ifseq.getName(j).substring(1,3).equals("sp") || ifseq.getName(j).substring(1,3).equals("tr"))  {
				  String[] namearray1 = ifseq.getName(j).split("\\|");
				  if (namearray1[1].length() != 6)  continue;
				  else {
				      if (uniprotid.get(i).equals(namearray1[1]))  {
					  IdOfUniprotId[i] = j;
				      }
				  }
			      }
			  }
		      }
		      for (int i = 0 ; i < uniprotid.size() ; ++i)  {
			  System.out.println(IdOfUniprotId[i] + " : " + uniprotid.get(i) + " : " + ifseq.getName(IdOfUniprotId[i]));
		      }

		      /* GOの要約テーブルを入手 */
		      int[][] bpArray = gop.getbpArray();
		      //		      int[][] ccArray = gop.getccArray();

		      /* GO 同心円を描くためにtdi.getLengthの最大値を調べる */
		      double maxLen = 0.0;
                      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ;  --i)  {
			  if (maxLen < tdi.getLength(i)) maxLen = tdi.getLength(i);
		      }
		      /* 同心円の数を決定する */
		      //		      int arcNum = gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize();
		      int arcNum = gop.getuniquebiologicalProcessSize();
		      goCircle= new double[arcNum];
                      double angleSize = 360.0/tb.getleafNumber(); 
		      maxLen += 0.02;
		      double rad = maxLen;
		      for (int i = 0 ; i < arcNum ; ++i)  {
			  if ((i % 5) == 0)       gcNJ.setStroke(Color.RED);
			  else if ((i % 5) == 1)  gcNJ.setStroke(Color.YELLOW);
			  else if ((i % 5) == 2)  gcNJ.setStroke(Color.BLUE);
			  else if ((i % 5) == 3)  gcNJ.setStroke(Color.GREEN);
			  else if ((i % 5) == 4)  gcNJ.setStroke(Color.BLACK);

			  gcNJ.setLineWidth(0.1);
		      	  rad += SpaceForconcentricCircles;
			  goCircle[i] = rad;
		          gcNJ.strokeOval(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio);
			  //			  if (i < gop.getuniquemolecularFunctionSize())  {
			      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
				  if (bpArray[j][i] == 1)  {
				      double ag = tdi.getAngle(IdOfUniprotId[j]);
				      gcNJ.setLineWidth(2.0);
				      gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio, 
						     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
				  }
			      }
			      /*
			  } 
			  else {
			      int cci = i - gop.getuniquemolecularFunctionSize();
			      for (int j = 0 ; j < uniprotid.size() ; ++j)  {
				  if (ccArray[j][cci] == 1)  {
				      double ag = tdi.getAngle(IdOfUniprotId[j]);
				      gcNJ.setLineWidth(2.0);
				      gcNJ.strokeArc(wSize*0.5-rad*bRatio, wSize*0.5-rad*bRatio, 2.0*rad*bRatio, 2.0*rad*bRatio, 
						     ag - angleSize/2.0+0.5, angleSize-1.0, ArcType.OPEN);
				  }
			      }			      
			  }
			      */
		      }

		      njt.setLeft(canvasNJ);

		      //		      root2.getChildren().add(njsp);
		      root2.getChildren().add(canvasNJ);
		      
		      njScene = new Scene(root2, wSize, wSize);
		      //		      Scene njScene = new Scene(root2, wSize, wSize);

		      njScene.setOnMouseClicked(eventNJ -> getInfoBP(eventNJ)); /*  check */

		      njStage.setScene(njScene);
		      //		      njScene.getStylesheets().add(Viewer.class.getResource("Viewer.css").toExternalForm());

		      njStage.show();
		      njStage.setX(njStage.getX() + 100);
		      njStage.setY(njStage.getY() + 100);	      	      

		      
		  }
		  catch(IOException e) {
		      System.out.println("Output Error");
		  }
	      }
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
	      TextField textField3 = new TextField("3");
	      Button btn3 = new Button("help");
              HBox dfbox = new HBox();
	      dfbox.getChildren().addAll(new Label("   "), label3, new Label("                   "), textField3, new Label("      "), btn3);
	      Label label4 = new Label("Window Size to Draw a Tree");
	      TextField textField4 = new TextField("800.0");
	      Button btn4 = new Button("help");
              HBox wsbox = new HBox();
	      wsbox.getChildren().addAll(new Label("   "), label4, new Label("                   "), textField4, new Label("      "), btn4);
	      /*
              int proot = 1;
	      Label label5 = new Label("Option for Putative Root");
              ToggleGroup group5 = new ToggleGroup();
	      RadioButton rbtn50 = new RadioButton("Method 0");
	      rbtn50.setOnAction(event50->{
		      proot = 0;
		  });
	      rbtn50.setToggleGroup(group5);
	      RadioButton rbtn51 = new RadioButton("Method 1");
	      rbtn51.setOnAction(event51->{
		      proot = 1;
		  });
	      rbtn51.setToggleGroup(group5);
	      HBox prhbox = new HBox();
	      Button btn5 = new Button("help");
	      prhbox.getChildren().addAll(new Label("   "), rbtn50, new Label("    "), rbtn51, new Label("    "), btn5);
	      */
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

		      int bootstrapIteration = Integer.parseInt(textField1.getText());
		      double limitSize4Display = Double.parseDouble(textField2.getText());
		      int defaultClusterSize = Integer.parseInt(textField3.getText());
		      wSize = Double.parseDouble(textField4.getText());
		      cx = wSize*0.5;
		      cy = wSize*0.5;

		      tb = new njtreeBuilder(distM, 1);

		      nState = new nodeState[2*tb.getleafNumber()];
		      for (int i = 0 ; i < 2*tb.getleafNumber() ; ++i)  {
			  nState[i] = new nodeState();
			  nState[i].setState(-1);
			  /* !!! */
			  /* ここで平均長か最大長と、クラスタサイズの２条件で
			     nState[i]に状態3 (展開抑制) の非表示(-2)を付与する */
			  /* !!! */
		      }

		      tdi = new TreeDrawInfo(tb, 2*tb.getleafNumber()-2, defaultClusterSize);

		      clinf = new importCluster();
		      clinf.setsizeOfCluster(tdi.getSizeOfClusters());
		      ArrayList<String> namelist = new ArrayList<String>();
		      for (int i = 0 ; i < tdi.getSizeOfClusters(); ++i) {
			  namelist.add("Cluster:" + i);
		      }
		      clinf.setNameOfCluster(namelist);

		      ArrayList<Integer> idlist = new ArrayList<Integer>();
		      for (int i = 0 ; i < tdi.getSizeOfClusters() ; ++i)  {
			  idlist.add(tdi.getIDOfClusters(i));
		      }
		      clinf.setidOfclusters(idlist);
		      //		      System.out.println("::### >> " + clinf.getClusterSize());
		      for (int i = 0 ; i < clinf.getClusterSize() ; ++i) {
			  //			  System.out.println("     " + clinf.getClusterID(i));
			  // System.out.println("    1:" + tb.getDescendantNodes()[clinf.getClusterID(i)][0]);
			  // System.out.println("    2:" + tb.getDescendantNodes()[clinf.getClusterID(i)][0]);
			  ArrayList<Integer> members = new ArrayList<Integer>();
			  for (int j = 0 ; j < 2 ; ++j)  {
			      for (int k = 0 ; k < tb.getClusterMembersSize(j)[clinf.getClusterID(i)]; ++k) {
				  if (tb.getClusterMembers(j)[clinf.getClusterID(i)][k] == -1)  continue;
				  members.add(tb.getClusterMembers(j)[clinf.getClusterID(i)][k]);
			      }
			  }
			  clinf.setclusterInfo(members);
			  clinf.setnumberOfMember(members.size());
		      }

		      /*
		      for (int i = 0 ; i < clinf.getClusterSize() ; ++i) {
			  System.out.println("ID = " + clinf.getClusterID(i) + ", size = " + clinf.getNumberOfMember().get(i).intValue());
			  for (int j = 0 ; j < clinf.getNumberOfMember().get(i).intValue() ; ++j)  {
			      System.out.println("    " + clinf.getCluster().get(i).get(j).intValue());
			  } 
		      }

		      System.out.println("END");
		      */

		      lpt = new LeftPanelTree(tb, clinf, tdi);
		      //		      LeftPanelTree lpt = new LeftPanelTree(tb, clinf, tdi);
		      for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
			  System.out.println(lpt.get_newOrder()[i]);
		      }
		      System.out.println("AAAAAAAA");

		      
		      if (maxLength == 0.0) maxLength = tdi.getMaxLength()*2.0;
		      //		      double bRatio = wSize*0.6/maxLength;
		      bRatio = wSize*0.6/maxLength;

		      //		      Stage njStage = new Stage();
		      njStage = new Stage();
		      njStage.setTitle("NJ tree");
		      njStage.setWidth(wSize);
		      njStage.setHeight(wSize);
		      // モーダルウィンドウに設定
		      //		      njStage.initModality(Modality.APPLICATION_MODAL);
		      // オーナーを設定
		      //		      njStage.initOwner(stage);
		      // この設定をしているとNJtreeのウィンドウと元のウィンドウを切り離して
		      // 動かすことができないのでコメントアウトした。（元のウィンドウがアクティブにならない
		      // 2018.03.12

		      Group root2 = new Group();

		      njt = new BorderPane();
		      //		      njt.setPrefSize(700, 700);
		      njt.setPrefSize(wSize, wSize);

		      //		      ScrollPane njsp = new ScrollPane(njt);

		      canvasNJ = new Canvas(wSize,wSize);
		      //		      final Canvas canvas = new Canvas(wSize,wSize);
	
		      gcNJ = canvasNJ.getGraphicsContext2D();
		      //		      GraphicsContext gc = canvas.getGraphicsContext2D();

		      gcNJ.setLineWidth(1.0);
		      gcNJ.setStroke(Color.RED);

		      for (int i = 2*tb.getleafNumber()-2 ; i > -1 ; --i)  {
			  int nodeid = tb.getAncestralNodes()[i]; 
			  if ( nodeid == 0)  continue;
			  else   {
			      //		System.out.println("i = " + i + ", X1 = " + tdi.getX1(i) + ", Y1 = " + tdi.getY1(i) + ", X2 = " + tdi.getX2(i) + ", Y2 = " + tdi.getY2(i)); 
			      switch(tdi.getnodeColor(i))  {
			          case 'W':  gcNJ.setStroke(Color.WHITE);
				            break;
			          case 'B':  gcNJ.setStroke(Color.BLUE);
				            break;
			          case 'Y':  gcNJ.setStroke(Color.YELLOW);
				            break;
			          case 'R':  gcNJ.setStroke(Color.RED);
				            break;
			          default :  gcNJ.setStroke(Color.RED);
			      }
			      gcNJ.strokeLine(tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5,tdi.getX2(i)*bRatio+wSize*0.5,tdi.getY2(i)*bRatio+wSize*0.5);
			      if (tb.getDescendantNodes()[i][0] != -1)  {
				  double ag1 = tdi.getAngle(tb.getDescendantNodes()[i][0]-1);
				  double ag2 = tdi.getAngle(tb.getDescendantNodes()[i][1]-1);
				  gcNJ.strokeArc(wSize*0.5-tdi.getLength(i)*bRatio,wSize*0.5-tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,2.0*tdi.getLength(i)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				  //				  gcNJ.strokeOval(wSize*0.5-2.5,wSize*0.5-2.5,5,5);
			      }
			      else {
				  gcNJ.fillText(String.valueOf(i+1),tdi.getX1(i)*bRatio+wSize*0.5,tdi.getY1(i)*bRatio+wSize*0.5);
			      }
			  }	    
		      }

		      njt.setLeft(canvasNJ);

		      //		      root2.getChildren().add(njsp);
		      root2.getChildren().add(canvasNJ);
		      
		      njScene = new Scene(root2, wSize, wSize);
		      njStage.setScene(njScene);
		      //		      njScene.getStylesheets().add(Viewer.class.getResource("Viewer.css").toExternalForm());

		      njStage.show();
		      njStage.setX(njStage.getX() + 100);
		      njStage.setY(njStage.getY() + 100);	      	      

		      /* Left Panel */

		      rplt = new rePaintLTree(tdi, lpt);

		      /*
		      System.out.println("!!! " + currentNodeId.size());
		      for (int i = 0 ; i < currentNodeId.size() ; ++i)  {
			  System.out.println(">>> " + currentNodeId.get(i).intValue() + " " + nState[currentNodeId.get(i).intValue()].getX() + ", "+nState[currentNodeId.get(i).intValue()].getY());
		      }
		      */

		      left.setLeft(canvasL);

		      /* Right Panel */
		      cls = new ClusterSeq(ifseq, clinf, 0);
		      //		      rePaintR(clinf.getClusterSize());
		      rePaintR();
		      right.setLeft(canvasR);

		      //		      System.out.println(bootstrapIteration + " : " + limitSize4Display + " :" + defaultClusterSize);
		      /*
		      drawUTree();
		      drawTree2();
		      */
		      mnuStage.close();

		  });
	      HBox okbox = new HBox();
	      okbox.getChildren().addAll(new Label("   "), btnOK);
	      //	      vbox1.getChildren().addAll(new Label("    "), bsbox, new Label("    "), llbox, new Label("    "), dfbox, new Label("    "), wsbox, new Label("    "), prhbox, new Label("    "), okbox);	      
              vbox1.getChildren().addAll(new Label("    "), bsbox, new Label("    "), llbox, new Label("    "), dfbox, new Label("    "), wsbox, new Label("    "), okbox);
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

	      //	      System.out.println(" wgm = " + wgm);

	      RadioButton rbtn0 = new RadioButton("Valder-Thornton");
	      rbtn0.setSelected(true);
	      rbtn0.requestFocus();
	      rbtn0.setToggleGroup(group1);
	      rbtn0.setOnAction(event1 -> {
		      CVmethod = 0;
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
	          // 新しいウインドウを生成
	          /* 1. Score MatrixのScoreDBからの読み込み */
	          scoreIdx smc = new scoreIdx("JOND920103");
	          double[][] ScoreMtx = smc.getIdx(); 
	          /* 2. ScoreMtxの最大値のを求める */
	          double max=0.0;
	          for (int i = 0 ; i < 20 ; ++i)  {
		     if (max < ScoreMtx[i][i])  max = ScoreMtx[i][i];
	          }
	          /* 3. 非対角項の最小値を求める */
	          double min = 0.0;
	          for (int i = 0 ; i < 19 ; ++i)  {
		     for (int j = i + 1 ; j < 20 ; ++j)  {
		         if (ScoreMtx[i][j] < min) min = ScoreMtx[i][j];
		     }
	          }
	          for (int i = 0 ; i < 20 ; ++i)  {
		     for (int j = 0;j < 20 ; ++j)  {
		         ScoreMtx[i][j] -= min;
		     }
	           }
	           max -= min;
	           /* 3. ウェイトとスコアマトリクスから保存度を計算 */
		   cnsvar = new CnsVar(ScoreMtx, swg, clinf, ifseq, max);

		   cvStage = new Stage();
		   cvStage.setTitle("Conservation/Variability Profile");
		   cvStage.setWidth(900);
		   cvStage.setHeight(700);
		   Group root4 = new Group();
		   cvp = new BorderPane();
		   cvp.setPrefSize(900, 700);
		   canvasCV = new Canvas(900, 700);
		   gcCV = canvasCV.getGraphicsContext2D();
                   gcCV.setLineWidth(1.0);

		   double ys = 500.0/clinf.getClusterSize();
		   double xs = 800.0/ifseq.getSeqLen(0);
		   for (int i = 0 ; i < clinf.getClusterSize() ; ++i) {
		       int member = clinf.getCluster().get(i).get(0).intValue();
		       char nodecolor = tdi.getColorCode(member);
		       switch(nodecolor)  {
		            case 'B':  gcCV.setStroke(Color.BLUE); 
			               break;
		            case 'R':  gcCV.setStroke(Color.RED);
			               break;
		            case 'Y':  gcCV.setStroke(Color.YELLOW);
			               break;
		       }
		       gcCV.strokeLine(50.0, (i+1)*ys, 850.0, (i+1)*ys);
		       for (int j = 50 ; j < 850 ; j += 50) {
			   gcCV.strokeLine(j*xs, (i+1)*ys, j*xs, (i+1)*ys-10);
		       }
		       for (int j = 1 ; j < ifseq.getSeqLen(0) ; j++) {
			   gcCV.strokeLine(50 + (j-1)*xs, -cnsvar.getCnsvar().get(i).get(j-1).doubleValue()*ys*0.4 + (i+1)*ys, 50 + j*xs, -cnsvar.getCnsvar().get(i).get(j).doubleValue()*ys*0.4 + (i+1)*ys);
		       } 
		   }

		   root4.getChildren().add(canvasCV);
		   cvScene = new Scene(root4, 900, 700);
		   cvStage.setScene(cvScene);
		   cvStage.show();
		   cvStage.setX(cvStage.getX() + 100);
		   cvStage.setY(cvStage.getY() + 100);

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
	      wgm = 2;
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
		      wgm = 2;
		      swg = new SeqWeight(distM, clinf);
		      /*
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
			 }*/
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
	      leftMouse = 1;

	      if (ifseq == null || tb == null && tdi == null)  {
		  Alert alert = new Alert(AlertType.INFORMATION);
		  alert.setTitle("AlertType.INFORMATION");
		  alert.setHeaderText("No Available Sequence or Tree Data!!");
		  alert.setContentText("Both sequence data and tree informatio are required for this operation.");
		  alert.showAndWait();
	      }
	      else {
		  //		  System.out.println("mnuImCls leafNumber = " + tb.getleafNumber());
		  //		  System.out.println("mnuImCls Number of Clusters = " + tdi.getNoOfClusters());

	          String filename = file.getName();
	          clinf = new importCluster(filename);
		  ArrayList<Integer> idList = new ArrayList<Integer>();

		  int[] activenode = new int[2*tb.getleafNumber() - 1];
		  int [][] ancestorChecker = new int[clinf.getClusterSize()][2*tb.getleafNumber()-1];
		  for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
		      for (int j = 0 ; j < 2*tb.getleafNumber()-1 ; ++j)  {
			  ancestorChecker[i][j] = 0;
		      }
		      for (int j = 0 ; j < clinf.getNumberOfMember().get(i).intValue() ; ++j) {
			  ancestorChecker[i][clinf.getCluster().get(i).get(j).intValue()] = 1;
		      }
		  }

		  for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
		      int mxnum = 0;
                      int mxid = -1;
		      for (int j = 0 ; j < 2*tb.getleafNumber()-1 ; ++j)  {
			  if (j < tb.getleafNumber()) activenode[j]=1;
			  else activenode[j]=0;
		      }
		      //                      int cnt = 0;
		      while(true) {
			  for (int j = tb.getleafNumber() ; j < 2*tb.getleafNumber()-1 ; ++j)  {
			      if (activenode[j] == 0)  {
				  int decid1 = tb.getDescendantNodes()[j][0]-1;
				  int decid2 = tb.getDescendantNodes()[j][1]-1;
				  if (activenode[decid1] == 1 && activenode[decid2] == 1)  {
				      ancestorChecker[i][j] = ancestorChecker[i][decid1] + ancestorChecker[i][decid2];
				      activenode[j] = 1;
				      activenode[decid1] = -1;
				      activenode[decid2] = -1;
				      if (ancestorChecker[i][j] > mxnum) {
					  mxnum= ancestorChecker[i][j];
					  mxid = j;
				      }
				  }
				  //				  System.out.println(cnt + ": j = " + j + " (" + activenode[j] + ") , decid1 = " + decid1 + " (" + activenode[decid1] + ") , decid2 = " + decid2 + " (" + activenode[decid2] + ") , " + ancestorChecker[i][decid1] + ";"  + ancestorChecker[i][decid2] + ";" + ancestorChecker[i][j]);
			      }
			  }
			  //			  cnt++;
			  //			  System.out.println("mxnum = " + mxnum + ", size = " + clinf.getNumberOfMember().get(i).intValue());
			  if (mxnum == clinf.getNumberOfMember().get(i).intValue()) {
			      break;
			  }
		      }
		      idList.add(mxid);
		  }
		  clinf.setidOfclusters(idList);

		  char[] colorCode = new char[2*tb.getleafNumber() - 1];

		  if ((clinf.getClusterSize()) % 2 == 0)  {
		      for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
			  for (int j = 0 ; j < 2*tb.getleafNumber()-1 ; ++j)  {
			      if (ancestorChecker[i][j] != 0)  {
				  if ((i % 2) == 0)  {
				      colorCode[j] = 'Y';
				  }
				  else {
				      colorCode[j] = 'B';				      
				  }
			      }
			  }
		      } 

		  }
		  else   {
		      for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
			  for (int j = 0 ; j < 2*tb.getleafNumber()-1 ; ++j)  {
			      if (ancestorChecker[i][j] != 0)  {
				  if ((i % 3) == 0)  {
				      colorCode[j] = 'Y';
				  }
				  else if ((i % 3) == 1) {
				      colorCode[j] = 'B';				      
				  }
				  else {
				      colorCode[j] = 'W';
				  }
			      }
			  }
		      } 

		  }

		  if (maxLength == 0.0) maxLength = tdi.getMaxLength()*2.0;
		  double bRatio = wSize*0.6/maxLength;

		  Group root2 = new Group();

		  njt = new BorderPane();
		  //		  njt.setPrefSize(700, 700);
		  njt.setPrefSize(wSize, wSize);

		  //		  ScrollPane njsp = new ScrollPane(njt);

		  canvasNJ = new Canvas(wSize,wSize);

		  gcNJ = canvasNJ.getGraphicsContext2D();

		  gcNJ.setLineWidth(1.0);
		  gcNJ.setStroke(Color.RED);

		  for (int j = 2*tb.getleafNumber()-2 ; j > -1 ; --j)  {
		      int nodeid = tb.getAncestralNodes()[j];
		      if ( nodeid == 0)  continue;
		      else   {
			  switch(colorCode[j])  {
			  case 'W':  gcNJ.setStroke(Color.WHITE);
			      break;
			  case 'B':  gcNJ.setStroke(Color.BLUE);
			      break;
			  case 'Y':  gcNJ.setStroke(Color.YELLOW);
			      break;
			  case 'R':  gcNJ.setStroke(Color.RED);
			      break;
			  default :  gcNJ.setStroke(Color.RED);
			  }
			  gcNJ.strokeLine(tdi.getX1(j)*bRatio+wSize*0.5,tdi.getY1(j)*bRatio+wSize*0.5,tdi.getX2(j)*bRatio+wSize*0.5,tdi.getY2(j)*bRatio+wSize*0.5);
			  if (tb.getDescendantNodes()[j][0] != -1)  {
			      double ag1 = tdi.getAngle(tb.getDescendantNodes()[j][0]-1);
			      double ag2 = tdi.getAngle(tb.getDescendantNodes()[j][1]-1);
			      gcNJ.strokeArc(wSize*0.5-tdi.getLength(j)*bRatio,wSize*0.5-tdi.getLength(j)*bRatio,2.0*tdi.getLength(j)*bRatio,2.0*tdi.getLength(j)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
			  }
			  else {
			      gcNJ.fillText(String.valueOf(j+1),tdi.getX1(j)*bRatio+wSize*0.5,tdi.getY1(j)*bRatio+wSize*0.5);
			  }
		      }
		  }

		  njt.setLeft(canvasNJ);

		  root2.getChildren().add(canvasNJ);
		  //		  root2.getChildren().add(njsp);

		  njScene = new Scene(root2, wSize, wSize);
		  njStage.setScene(njScene);

		  njStage.show();
		  njStage.setX(njStage.getX() + 100);
		  njStage.setY(njStage.getY() + 100);

		  /*
		  System.out.println("No of Clusters = " + idList.size());
		  for (int i = 0 ; i < idList.size() ; ++i)  {
		      System.out.println("   id = " + idList.get(i).intValue());
		  }
		  */
		  /*
		  for (int i = 0 ; i < tb.getleafNumber() ; ++i)  {
		      int k = 0;
		      for (int j = 0 ; j < clinf.getClusterSize() ; ++j) {
			  if (ancestorChecker[j][i] == 1)  k++;
		      }
		      if (k == 1)  System.out.println("i = " + i);
		  }
		  System.out.println("ENDcheck");
		  */
		  /*
		  for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
		      for (int j = 0 ; j < leafNumber ; ++j)  {
			  
		      }
		  }
		  */
	      }

              rePaintL();
	      left.setLeft(canvasL);

	      cls = new ClusterSeq(ifseq, clinf, 0);

	      //	      rePaintR(clinf.getClusterSize());
	      rePaintR();
	      right.setLeft(canvasR);

	  });

      mnuImSeqCl.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);

	      if (file != null)  {
		  String filename = file.getName();
		  ifseq = new readSeq(filename, 0);
	      }
	      /*
	      if (clinf != null)  {
		  cls = new ClusterSeq(ifseq, clinf, 0);
                  rePaintL();
	          left.setLeft(canvasL);
		  rePaintR();
		  right.setLeft(canvasR);  // <--- setRight の間違い? 
	      }
              */
	  });

      mnuImSeqaFS.setOnAction(event -> {
	      File file = fileChooser.showOpenDialog(stage);
	      if (file != null)  {
		  String filename = file.getName();
		  ifseq = new readSeq(filename, 1);
	      }
	      /* 
	      if (clinf != null)  {
		  cls = new ClusterSeq(ifseq, clinf, 0);
                  rePaintL();
	          left.setLeft(canvasL);
		  rePaintR();
		  right.setLeft(canvasR);
	      }
	      */
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
		      /*
	              if (clinf != null)  {
		          cls = new ClusterSeq(ifseq, clinf, 0);
                          rePaintL();
	                  left.setLeft(canvasL);
		          rePaintR();
		          right.setLeft(canvasR);
	              }
		      */
                  }
	          catch (IOException | InterruptedException e) {
		      e.printStackTrace();
	          }
	      }
	  });

      /* <<< File Menu ************************************************************************************* */

      mnuExit.setOnAction(event -> System.exit(0));

      fileMenu.getItems().addAll(mnuImSequFS, mnuImSeqaFS, mnuImSeqCl, mnuImStr, mnuImTree, mnuImClus, mnuExSeqaFS, mnuExit);
      seqAnal.getItems().addAll(mnuSqDist, mnuNJtree, mnuTaxWgt, mnuAAFreq, mnuCnsVar, mnuFnDiff);

      strAnal.getItems().addAll(mnuMolmil);

      //      webAnal.getItems().addAll(mnuSparql, mnuScraping);
      //      webAnal.getItems().addAll(mnuSparql);
	  webAnal.getItems().addAll(mnuSetting, mnuMolFunc, mnuCelComp, mnuBioProc, mnuSourceO, mnuActSite);
      //      menuBar.getMenus().addAll(fileMenu, seqAnal, strAnal, webAnal);
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

    private void rePaint(MouseEvent event) {
	double x, y, x2, y2;
	x = event.getX();
	y = event.getY();
	//	System.out.println("x = " + x + ", y = " + y);
	//	System.out.println("current Node Id Size = " + currentNodeId.size());


	if (leftMouse == 0)  {
	    if (x >= 10 && x <= 99 && y >= 50+clinf.getClusterSize()*100 && y <= 77+clinf.getClusterSize()*100)  {
		System.out.println("VVVV");
		leftMouse = 1;
		rePaintL();
	    }
	    else {
	    int  nodeCheck = 0;
	    for (int i = 0; i < currentNodeId.size() ; ++i)  {
		x2 = nState[currentNodeId.get(i).intValue()].getX();
		y2 = nState[currentNodeId.get(i).intValue()].getY();
	    
		switch(nState[currentNodeId.get(i).intValue()].getState())  {
	            case 0: if ((Math.pow((x-x2),2) + Math.pow((y - y2),2)) < 1)  {
		               System.out.println("A leaf is clicked");
		               nodeCheck++;
		            }
		            break;  
	            case 1: if ((Math.pow((x-x2),2) + Math.pow((y - y2),2)) < 4)  {
		               System.out.println("A node to be collapsed is clicked");
			       /* 1. 現在のクラスタを構成するノードをtdiから取得 */
			       currentList = tdi.getclusterList();
			       System.out.println("## Size of CurrentList Before  = " + currentList.size());
			       for (int j = 0 ; j < currentList.size() ; ++j)  {
			           System.out.println("#### currentList " + j + " = " + currentList.get(j));
			       }
			   
			       /* 2. 　currentNodeId.get(i).intValue() 配下の表示されているnodeの削除　*/
			       reConstructclusterList(currentNodeId.get(i).intValue());
			       currentList.add(currentNodeId.get(i).intValue()); /* reConstructclusterListで削除されたcurrentNodeId.get(i).intValue()) を復旧 */
			       System.out.println("## Size of CurrentList After  = " + currentList.size());
			       for (int j = 0 ; j < currentList.size() ; ++j)  {
			           System.out.println("#### currentList " + j + " = " + currentList.get(j));
			       }
			   
		               nodeCheck++;
			       /* 3.  tdiのメソッドでnodeColorを再設定 */

			       ArrayList<Integer> newList = new ArrayList<Integer>();
			       for (int j = 0 ; j < currentList.size() ; ++j) {
			           newList.add(currentList.get(j));
			       }
			       tdi.setclusterList(newList);

			       tdi.GenerateColorCode(newList);
			   
			       /* 4. clinfの再設定   4 ~ 7 はcase 2と同じ　*/  

			        clinf = new importCluster();
			        clinf.setsizeOfCluster(tdi.getSizeOfClusters());
				ArrayList<String> namelist = new ArrayList<String>();
				for (int j = 0 ; j < tdi.getSizeOfClusters(); ++j) {
				    namelist.add("Cluster:" + j);
				}
				clinf.setNameOfCluster(namelist);				
			    
			        System.out.println("tdi.getSizeOfClusters = " + tdi.getSizeOfClusters());

			        ArrayList<Integer> idlist = new ArrayList<Integer>();
			        for (int j = 0 ; j < tdi.getSizeOfClusters() ; ++j)  {
				    idlist.add(tdi.getIDOfClusters(j));
			        }
			        clinf.setidOfclusters(idlist);

			        for (int j = 0 ; j < clinf.getClusterSize() ; ++j) {

				    ArrayList<Integer> members = new ArrayList<Integer>();
				    for (int k = 0 ; k < 2 ; ++k)  {
				        for (int l = 0 ; l < tb.getClusterMembersSize(k)[clinf.getClusterID(j)]; ++l) {
					    if (tb.getClusterMembers(k)[clinf.getClusterID(j)][l] == -1)  continue;
					    members.add(tb.getClusterMembers(k)[clinf.getClusterID(j)][l]);
				        }
				    }
				    clinf.setclusterInfo(members);
				    clinf.setnumberOfMember(members.size());
			        }

			        /* 5. 無根系統樹の描画 */
			    
			        lpt = new LeftPanelTree(tb, clinf, tdi);
				/*
				for (int j = 0 ; j < clinf.getClusterSize() ; ++j)  {
				    System.out.println(lpt.get_newOrder()[j]);
				}
				System.out.println("BBBB"); 
				*/

  			        if (maxLength == 0.0) maxLength = tdi.getMaxLength()*2.0;
			        double bRatio = wSize*0.6/maxLength;

			        Group root2 = new Group();

			        njt = new BorderPane();
				//			        njt.setPrefSize(700, 700);
			        njt.setPrefSize(wSize, wSize);

				//			        ScrollPane njsp = new ScrollPane(njt);

			        canvasNJ = new Canvas(wSize,wSize);
	
			        gcNJ = canvasNJ.getGraphicsContext2D();

			        gcNJ.setLineWidth(1.0);
			        gcNJ.setStroke(Color.RED);

			        for (int j = 2*tb.getleafNumber()-2 ; j > -1 ; --j)  {
				    int nodeid = tb.getAncestralNodes()[j]; 
				    if ( nodeid == 0)  continue;
				    else   {
				        switch(tdi.getnodeColor(j))  {
				           case 'W':  gcNJ.setStroke(Color.WHITE);
					          break;
				           case 'B':  gcNJ.setStroke(Color.BLUE);
					          break;
				           case 'Y':  gcNJ.setStroke(Color.YELLOW);
					          break;
				           case 'R':  gcNJ.setStroke(Color.RED);
					          break;
				           default :  gcNJ.setStroke(Color.RED);
				        }
				        gcNJ.strokeLine(tdi.getX1(j)*bRatio+wSize*0.5,tdi.getY1(j)*bRatio+wSize*0.5,tdi.getX2(j)*bRatio+wSize*0.5,tdi.getY2(j)*bRatio+wSize*0.5);
				        if (tb.getDescendantNodes()[j][0] != -1)  {
					    double ag1 = tdi.getAngle(tb.getDescendantNodes()[j][0]-1);
					    double ag2 = tdi.getAngle(tb.getDescendantNodes()[j][1]-1);
					    gcNJ.strokeArc(wSize*0.5-tdi.getLength(j)*bRatio,wSize*0.5-tdi.getLength(j)*bRatio,2.0*tdi.getLength(j)*bRatio,2.0*tdi.getLength(j)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				        }
				        else {
					    gcNJ.fillText(String.valueOf(j+1),tdi.getX1(j)*bRatio+wSize*0.5,tdi.getY1(j)*bRatio+wSize*0.5);
				        }
				    }	    
			        }

			        njt.setLeft(canvasNJ);

				//			        root2.getChildren().add(njsp);
			        root2.getChildren().add(canvasNJ);
		      
			        njScene = new Scene(root2, wSize, wSize);
			        njStage.setScene(njScene);

			        njStage.show();
			        njStage.setX(njStage.getX() + 100);
			        njStage.setY(njStage.getY() + 100);	      	      

                            }

		        /* 6. Left Panel Treeの描画 */

		        rplt = new rePaintLTree(tdi, lpt);

			left.setLeft(canvasL);

			/* 7. Right Panelの描画 */

			cls = new ClusterSeq(ifseq, clinf, 0);
			//			rePaintR(clinf.getClusterSize());
			rePaintR();
			right.setLeft(canvasR);

		        break;
	        case 2: if ((x2 <= x) && ((x2 + 20) >= x) && ((0.5*(x-x2)+ y2) >= y) && ((-0.5*(x-x2)+y2) <= y)) {
			    System.out.println("A node to be deployedd is clicked");
			    //			    System.out.println("State = " + nState[currentNodeId.get(i).intValue()].getState());
			    //			    System.out.println("cluster size = " + tdi.getNoOfClusters());

			    //1. クリックされたノード配下から指定されたクラスタ数分のノードを同定
			    ArrayList<Integer> cllist = tb.ClusteringFromASpecifiedNode(currentNodeId.get(i).intValue(), tdi.getNoOfClusters());
			    /*
			    System.out.println(currentNodeId.get(i).intValue());
			    System.out.println(tb.getDescendantNodes()[currentNodeId.get(i).intValue()][0]-1);
			    System.out.println(tb.getDescendantNodes()[currentNodeId.get(i).intValue()][1]-1);
			    System.out.println("size = " + cllist.size());
			    for (int onep = 0 ; onep < cllist.size() ; ++onep)  {
				System.out.println(" onep = " + onep + " ; " + cllist.get(onep).intValue());
			    }
			    */

			    nodeCheck++;

			    //2. 1で得られたクラスタと現在のクラスタをまとめて、tdiにセット
			    ArrayList<Integer> newList = new ArrayList<Integer>();

			    for (int j = 0 ; j < clinf.getClusterSize() ; ++j)  {
				if (clinf.getClusterID(j) == currentNodeId.get(i).intValue())  {
				        nState[clinf.getClusterID(j)].setState(1);
				}
				else  {
				    newList.add(clinf.getClusterID(j));
				    if (clinf.getClusterID(j) >= tb.getleafNumber())  {
					nState[clinf.getClusterID(j)].setState(2);
				    }
				    else  {
					nState[clinf.getClusterID(j)].setState(0);
				    }
				}
			    }

			    for (int j = 0 ; j < cllist.size() ; ++j)  {
				newList.add(cllist.get(j));
			    }
			    /*			    
			    System.out.println("Size of New Clusters = " + newList.size());
			    for (int j = 0 ; j < newList.size() ; ++j)  {
				System.out.println(newList.get(j).intValue());
			    }
			    */			    

			    //3. tdiのメソッドでnodeColorを再設定
			    tdi.setclusterList(newList);

			    tdi.GenerateColorCode(newList);

			    System.out.println(">>After click");
			    for(int j = 0 ; j < tb.getleafNumber() ; ++j) {
				System.out.println(j + ":" + tdi.getnodeColor(j));
			    }

			    //			    System.out.println(tdi.getSizeOfClusters());

			    //4. clinfの再設定 L319 - L338

			    clinf = new importCluster();
			    clinf.setsizeOfCluster(tdi.getSizeOfClusters());
			    ArrayList<String> namelist = new ArrayList<String>();
			    for (int j = 0 ; j < tdi.getSizeOfClusters(); ++j) {
				namelist.add("Cluster:" + j);
			    }
			    clinf.setNameOfCluster(namelist);
			    ArrayList<Integer> idlist = new ArrayList<Integer>();
			    for (int j = 0 ; j < tdi.getSizeOfClusters() ; ++j)  {
				idlist.add(tdi.getIDOfClusters(j));
			    }
			    clinf.setidOfclusters(idlist);

			    for (int j = 0 ; j < clinf.getClusterSize() ; ++j) {

				ArrayList<Integer> members = new ArrayList<Integer>();
				for (int k = 0 ; k < 2 ; ++k)  {
				    for (int l = 0 ; l < tb.getClusterMembersSize(k)[clinf.getClusterID(j)]; ++l) {
					if (tb.getClusterMembers(k)[clinf.getClusterID(j)][l] == -1)  continue;
					members.add(tb.getClusterMembers(k)[clinf.getClusterID(j)][l]);
				    }
				}
				clinf.setclusterInfo(members);
				clinf.setnumberOfMember(members.size());
			    }

			    //5. 無根系統樹の描画
			    lpt = new LeftPanelTree(tb, clinf, tdi);

			    /*
				for (int j = 0 ; j < clinf.getClusterSize() ; ++j)  {
				    System.out.println(lpt.get_newOrder()[j]);
				}
				System.out.println("BBBB"); 
			    */

			    if (maxLength == 0.0) maxLength = tdi.getMaxLength()*2.0;
			    double bRatio = wSize*0.6/maxLength;

			    Group root2 = new Group();

			    njt = new BorderPane();
			    //			    njt.setPrefSize(700, 700);
			    njt.setPrefSize(wSize, wSize);

			    //			    ScrollPane njsp = new ScrollPane(njt);

			    canvasNJ = new Canvas(wSize,wSize);
	
			    gcNJ = canvasNJ.getGraphicsContext2D();

			    gcNJ.setLineWidth(1.0);
			    gcNJ.setStroke(Color.RED);

			    for (int j = 2*tb.getleafNumber()-2 ; j > -1 ; --j)  {
				int nodeid = tb.getAncestralNodes()[j]; 
				if ( nodeid == 0)  continue;
				else   {
				    switch(tdi.getnodeColor(j))  {
				       case 'W':  gcNJ.setStroke(Color.WHITE);
					          break;
				       case 'B':  gcNJ.setStroke(Color.BLUE);
					          break;
				       case 'Y':  gcNJ.setStroke(Color.YELLOW);
					          break;
				       case 'R':  gcNJ.setStroke(Color.RED);
					          break;
				       default :  gcNJ.setStroke(Color.RED);
				    }
				    gcNJ.strokeLine(tdi.getX1(j)*bRatio+wSize*0.5,tdi.getY1(j)*bRatio+wSize*0.5,tdi.getX2(j)*bRatio+wSize*0.5,tdi.getY2(j)*bRatio+wSize*0.5);
				    if (tb.getDescendantNodes()[j][0] != -1)  {
					double ag1 = tdi.getAngle(tb.getDescendantNodes()[j][0]-1);
					double ag2 = tdi.getAngle(tb.getDescendantNodes()[j][1]-1);
					gcNJ.strokeArc(wSize*0.5-tdi.getLength(j)*bRatio,wSize*0.5-tdi.getLength(j)*bRatio,2.0*tdi.getLength(j)*bRatio,2.0*tdi.getLength(j)*bRatio,ag1,ag2-ag1,ArcType.OPEN);
				    }
				    else {
					gcNJ.fillText(String.valueOf(j+1),tdi.getX1(j)*bRatio+wSize*0.5,tdi.getY1(j)*bRatio+wSize*0.5);
				    }
				}	    
			    }

			    njt.setLeft(canvasNJ);

			    //			    root2.getChildren().add(njsp);
			    root2.getChildren().add(canvasNJ);
		      
			    njScene = new Scene(root2, wSize, wSize);
			    njStage.setScene(njScene);

			    njStage.show();
			    njStage.setX(njStage.getX() + 100);
			    njStage.setY(njStage.getY() + 100);	      	      

                        }

		        //6. Left Panel Treeの描画

		        rplt = new rePaintLTree(tdi, lpt);

			left.setLeft(canvasL);

			//7. Right Panelの描画
			cls = new ClusterSeq(ifseq, clinf, 0);
			//			rePaintR(clinf.getClusterSize());
			rePaintR();
			right.setLeft(canvasR);

		        break;
	        case 3: System.out.println("The cluster cannot be deployed due to the large numbers of the members"); 
		        nodeCheck++;
		        break;
	        }
	        System.out.println("nodeCheck = " + nodeCheck);
	        if (nodeCheck != 0)  break;
	    }
	    if (nodeCheck == 0)  System.out.println("No available node is clicked");
	    }
	}
	else {
	    rePaintL();
	} 
    }

    private void reConstructclusterList(int idx) {	
	int d0 = tb.getDescendantNodes()[idx][0]-1;
	int d1 = tb.getDescendantNodes()[idx][1]-1;

	System.out.println("reConstructclusterList");
	System.out.println(" State = " + nState[idx].getState());

	int k = 0;
	for (int i = 0 ; i < currentList.size() ; ++i)  {
	     if (idx == currentList.get(i).intValue())  {
		    System.out.println(">> Before Size = " + currentList.size());
		    currentList.remove(i);
		    System.out.println(">> After Size = " + currentList.size());
		    k++;
		    break;
	     }
	}
	if (nState[idx].getState() == 0 || nState[idx].getState() == 1 || nState[idx].getState() == 2 )  nState[idx].setState(-1);
	if (nState[idx].getState() == 3)  nState[idx].setState(-2);
        if (k != 0)   return;
        else  {
	     reConstructclusterList(d0);
	     reConstructclusterList(d1);		    
	}
    }

    private void getInfoSO(MouseEvent event) {
	double theta = 0.0;
	double x = event.getX();
	double y = event.getY();

        double r = Math.pow((x- cx),2.0) + Math.pow((y - cy),2.0);
	r = Math.sqrt(r);
	/*
	System.out.println("mm" + minChk + " : " + maxChk);
	System.out.println(bRatio);
	System.out.println(r);
	*/
        if (r/bRatio <= maxChk && r/bRatio >= minChk)  {
	    double sinValue = -(y-cy)/r;
	    double cosValue = (x-cx)/r;
	    double thetaX = Math.acos(cosValue)/Math.PI * 180.0;
	    double thetaY = Math.asin(sinValue)/Math.PI * 180.0;
	    //	    System.out.println("XY " + thetaX + ", " + thetaY);

	    if (thetaX >= 0.0 && thetaX < 90.0 && thetaY >= 0.0) {
		theta = thetaX;
	    }
	    else if(thetaX >= 0.0 && thetaX < 90.0 && thetaY < 0.0) {
		theta = 360.0 - thetaX;
	    }
	    else if (thetaX >= 90.0 && thetaX < 180.0 && thetaY >= 0.0) {
		theta = thetaX;
	    }
	    else if (thetaX >= 90.0 && thetaX < 180.0 && thetaY < 0.0) {
		theta = 180.0 - thetaY;
	    }

            double mind = 100.0;
	    int idu = -1;
	    for (int i = 0 ; i < IdOfUniprotId.length ; ++i)  {
		double ag = tdi.getAngle(IdOfUniprotId[i]);
		if (Math.pow(ag - theta, 2.0) < mind)  {
		    mind = Math.pow(ag - theta, 2.0);
		    idu = i;
		}
	    }

            if (mind < Math.pow(360.0/(2*tb.getleafNumber()), 2.0))  {

		String acName = academicName.get(idu);

		try {
		Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e) {
		    e.printStackTrace();
		}
		catch(Exception e) {
		    e.printStackTrace();
		}

		connection = null;
		statement = null;
		rs = null;
		try {
		    connection = DriverManager.getConnection("jdbc:sqlite:/Users/toh/Desktop/Viewer/taxonomy");
		    statement = connection.createStatement();
		    statement.setQueryTimeout(30);
		    String str = acName.split(" ")[0];
		    String sql = "select * from tol where genus = \"" + str + "\" limit 1; ";
		    rs = statement.executeQuery(sql);			    
		    ta.setText(ifseq.getName(IdOfUniprotId[idu]) + "\n" + "species or subspecies : " + acName + "\n" + "genus : " + rs.getString("genus") + "\n" + "family : " + rs.getString("family") + "\n" + "order : " + rs.getString("order") + "\n" + "class : " + rs.getString("class") + "\n" + "phylum : " + rs.getString("phylum") + "\n" + "kingdom : " + rs.getString("kingdom") + "\n" + "superkingdom : " + rs.getString("superkingdom") + "\n");
		}  catch (SQLException e) {
		    System.err.println(e.getMessage());
		}
		
		ta.setOnMouseClicked(eventA -> {
			    double yta = eventA.getY();

			    setY(yta);
		    
			    Desktop desktop = Desktop.getDesktop();
			    try{
				uriString3 = uriString1;
				if (y1 >=23 && y1 <= 38) {
				    String[] acn = acName.split(" ");
				    for (int l = 0 ; l < acn.length ; ++l) {
					if (l < (acn.length - 1))  {
					    uriString3 = uriString3 + acn[l] + "+";
					}
					else {
					    uriString3 = uriString3 + acn[l];
					}
				    } 
				}
				else if (y1 >= 41 && y1 <= 54) {
				    uriString3 = uriString3 + rs.getString("genus") + "+genus";
				}
				else if (y1 >= 58 && y1 <= 71) {
				    uriString3 = uriString3 + rs.getString("family") + "+family";
				}
				else if (y1 >= 75 && y1 <= 85)  {
				    uriString3 = uriString3 + rs.getString("order")  + "+order";
				}
				else if (y1 >= 92 && y1 <= 105) {
				    uriString3 = uriString3 + rs.getString("class") + "+class";
				}
				else if (y1 >= 109 && y1 <= 123) {
				    uriString3 = uriString3 + rs.getString("phylum") + "+phylum";
				}
				else if (y1 >= 127 && y1 <= 139) {
				    uriString3 = uriString3 + rs.getString("kingdom") + "+kingdom";
				}
				else if (y1 >= 144 && y1 <= 155) {
				    uriString3 = uriString3 + rs.getString("superkingdom") + "+superkingdom";
				}
				uriString3 = uriString3 + uriString2;

				URI uri = new URI( uriString3 );
				desktop.browse( uri );
			    }catch( Exception e ){
				e.printStackTrace();
			    }

		    });
	    }
	    else {
		double mind2 = 100.0;
		int idx = -1;
		for (int k = 0 ; k < tb.getleafNumber() ; ++k)  {
		    double ag = tdi.getAngle(k);
		    System.out.println(k + " | " + ag);
		    if (Math.pow(ag - theta, 2.0) < mind2)  {
			mind2 = Math.pow(ag - theta, 2.0);
			idx = k;
		    }
		}
		ta.setText(ifseq.getName(idx) + "\n");
		System.out.println(" id = " + idx);
	    }
	}
    }

    private void getInfoMF(MouseEvent event) {
	double x = event.getX();
	double y = event.getY();

	/*
	double cx = wSize*0.5;
	double cy = wSize*0.5;
	*/

	System.out.println(x + " : " + y + " : " + cx + ": " + cy); 
	double margin = 0.01;

        double r = Math.pow((x- cx),2.0) + Math.pow((y - cy),2.0);
	r = Math.sqrt(r) / bRatio;

	System.out.println(">> r = " + r);
	//	System.out.println(gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize());
	System.out.println(gop.getuniquemolecularFunctionSize());
	//	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize() ; ++i) {
	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize() ; ++i) {
	    System.out.println(goCircle[i]);
	}

	//	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize() ; ++i)  {
	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize() ; ++i)  {
	    if (r > (goCircle[i]-margin) && r < (goCircle[i] + margin))  { 
		//		if (i < gop.getuniquemolecularFunctionSize())  {
		    ta.setText("Molecular Function : " + gop.getuniqueMF().get(i));
		    break;
		    //		}
		    //	        else {
		    //		    ta.setText("Cellular Component : " + gop.getuniqueCC().get(i-gop.getuniquemolecularFunctionSize()));
		    //		    break;
		    //		}
	    }
	}
    }

    private void getInfoCC(MouseEvent event) {
	double x = event.getX();
	double y = event.getY();

	/*
	double cx = wSize*0.5;
	double cy = wSize*0.5;
	*/

	System.out.println(x + " : " + y + " : " + cx + ": " + cy); 
	double margin = 0.01;

        double r = Math.pow((x- cx),2.0) + Math.pow((y - cy),2.0);
	r = Math.sqrt(r) / bRatio;

	System.out.println(">> r = " + r);
	//	System.out.println(gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize());
	System.out.println(gop.getuniquecellularComponentSize());
	//	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize() ; ++i) {
	for (int i = 0 ; i < gop.getuniquecellularComponentSize() ; ++i) {
	    System.out.println(goCircle[i]);
	}

	//	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize() ; ++i)  {
	for (int i = 0 ; i < gop.getuniquecellularComponentSize() ; ++i)  {
	    if (r > (goCircle[i]-margin) && r < (goCircle[i] + margin))  { 
		//		if (i < gop.getuniquemolecularFunctionSize())  {
		    ta.setText("Cellular Component : " + gop.getuniqueCC().get(i));
		    break;
		    //		}
		    //	        else {
		    //		    ta.setText("Cellular Component : " + gop.getuniqueCC().get(i-gop.getuniquemolecularFunctionSize()));
		    //		    break;
		    //		}
	    }
	}
    }

    private void getInfoBP(MouseEvent event) {
	double x = event.getX();
	double y = event.getY();

	/*
	double cx = wSize*0.5;
	double cy = wSize*0.5;
	*/

	System.out.println(x + " : " + y + " : " + cx + ": " + cy); 
	double margin = 0.01;

        double r = Math.pow((x- cx),2.0) + Math.pow((y - cy),2.0);
	r = Math.sqrt(r) / bRatio;

	System.out.println(">> r = " + r);
	//	System.out.println(gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize());
	System.out.println(gop.getuniquebiologicalProcessSize());
	//	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize() ; ++i) {
	for (int i = 0 ; i < gop.getuniquebiologicalProcessSize() ; ++i) {
	    System.out.println(goCircle[i]);
	}

	//	for (int i = 0 ; i < gop.getuniquemolecularFunctionSize()+gop.getuniquecellularComponentSize() ; ++i)  {
	for (int i = 0 ; i < gop.getuniquebiologicalProcessSize() ; ++i)  {
	    if (r > (goCircle[i]-margin) && r < (goCircle[i] + margin))  { 
		//		if (i < gop.getuniquemolecularFunctionSize())  {
		    ta.setText("Biological Process : " + gop.getuniqueBP().get(i));
		    break;
		    //		}
		    //	        else {
		    //		    ta.setText("Cellular Component : " + gop.getuniqueCC().get(i-gop.getuniquemolecularFunctionSize()));
		    //		    break;
		    //		}
	    }
	}
    }

    /*
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
    */

    void rePaintL()  {
	 c1 = new char[clinf.getClusterSize()];
	 c2 = new char[clinf.getClusterSize()];
         for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
  	      c1[i] = ' ';
       	      c2[i] = ' ';
	  }

	 //	 canvasL = new Canvas(500,500);
	  canvasL = new Canvas((2*25 + 30), clinf.getClusterSize()*50+20);
	  gcL = canvasL.getGraphicsContext2D();
	  for (int i = 0 ; i < clinf.getClusterSize() ; ++i)  {
	      gcL.setLineWidth(2.0);
   	      gcL.setStroke(Color.BLUE);
	      //       	      gcL.strokeRect(10,34+i*25,20,20);
       	      gcL.strokeRect(10,32+i*100,20,20);

       	      gcL.setStroke(Color.ORANGE);
	      //       	      gcL.strokeRect(35,34+i*25,20,20);
       	      gcL.strokeRect(35,32+i*100,20,20);

       	      gcL.setFont(new Font(20));
	      //       	      gcL.fillText(clinf.getClusterName(i), 65, 50 + i*25);
       	      gcL.fillText(clinf.getClusterName(i), 65, 52 + i*100);
       	  }
	  gcL.setLineWidth(1.0);
	  gcL.setStroke(Color.BLACK);
	  //       	  gcL.strokeRect(10, 38+clinf.getClusterSize()*25,82,20);
       	  gcL.strokeRect(10, 60+clinf.getClusterSize()*100,82,20);
          gcL.setFont(new Font(18));
	  //	  gcL.fillText("clear all",12,54+clinf.getClusterSize()*25);
	  gcL.fillText("clear all",12,78+clinf.getClusterSize()*100);
       	  gcL.strokeRect(10, 90+clinf.getClusterSize()*100,82,20);
	  gcL.fillText("tree",12,107+clinf.getClusterSize()*100);
    }

    //    void rePaintR(int clsize)  {
    void rePaintR()  {
	//canvasR = new Canvas(500,500);
	//	 canvasR = new Canvas((ifseq.getSeqLen(0)*25+50), (ifseq.getSeqSize()*50+50));
	int clsize = clinf.getClusterSize();
	 canvasR = new Canvas((ifseq.getSeqLen(0)*25+50), (clinf.getClusterSize()*50+50));
         gcR = canvasR.getGraphicsContext2D();
	 int[] newOrder = lpt.get_newOrder();
         for (int i = 0 ; i < clsize ; ++i)  {
	     int k = newOrder[i];
	      for (int j = 0 ; j < ifseq.getSeqLen(0) ; ++j)  {
		 double Rc = cls.getFreq()[k][cls.getmxIdx()[k][j]][j];
		 double Bc = 1.0 - cls.getFreq()[k][cls.getmxIdx()[k][j]][j];
		 Color col;
		 if (cls.getmxIdx()[k][j]==20)  col = new Color(0.0,0.0,0.0,1.0);
		 else col = new Color(Rc, 0.0, Bc, 1.0);
		 gcR.setFill(col);
		 gcR.setFont(new Font(20));
		 //		 gcR.fillText(cls.getcSeq().get(i).substring(j, j+1), 10+j*25, 50+i*100);
		 gcR.fillText(cls.getcSeq().get(k).substring(j, j+1), 10+j*25, 50+i*50);
	      }
	 }

    }

    public class rePaintLTree  {
	private double[] xcd;
	private double[] ycd;
        private int leafNumber;
	private int[][] DescendantNodes;
	private ArrayList<Integer> idOfClusters;
        private int clusterSize;
        private char[] nodeColor;

	rePaintLTree(TreeDrawInfo tdi, LeftPanelTree lpt) {
	    leafNumber = lpt.get_leafNumber();
	    xcd = lpt.get_Xcoordinates();
            ycd = lpt.get_Ycoordinates();
	    DescendantNodes = lpt.get_DescendantNodes();
	    idOfClusters = lpt.get_idOfClusters();
            clusterSize = lpt.get_clusterSize();
	    nodeColor = tdi.getnodeColorArray();

	    //canvasL = new Canvas(500,500);
	    canvasL = new Canvas((2*25 + 300), maxD(ycd)+150);
	    gcL = canvasL.getGraphicsContext2D();

	    currentNodeId = new ArrayList<Integer>(); 
	    DrawTree(2*leafNumber-2);

	    /* button to cluster selection */
	    gcL.setStroke(Color.BLACK);
       	    gcL.strokeRect(10, 50+clinf.getClusterSize()*100,89,22);
            gcL.setFont(new Font(12));
	    gcL.fillText("Select Clusters",12,66+clinf.getClusterSize()*100);
	}

        void  DrawTree(int idx)  {
	    int k = -1;
	    /* node id  3, -2の処理を入れる必要がある */
	    /*  確認用 */
	    System.out.println("## node = " + idx + ", state = " + nState[idx].getState());
	    System.out.println("#### child1 = " + (tb.getDescendantNodes()[idx][0]-1));
	    System.out.println("#### child1 = " + (tb.getDescendantNodes()[idx][1]-1));
	    /*    確認用 */

	    for (int i = 0 ; i < clusterSize ; ++i)  {
		if (idx == idOfClusters.get(i).intValue()) {
		    k = 1;
                    break;
		}
	    }
	    if (k > 0 || idx < tb.getleafNumber())  {
		gcL.setLineWidth(2.0);
                switch(nodeColor[idx])  {
		   case 'R': gcL.setStroke(Color.RED);
		             break;
		   case 'W': gcL.setStroke(Color.WHITE);
		             break;
		   case 'Y': gcL.setStroke(Color.YELLOW);
		             break;
		   case 'B': gcL.setStroke(Color.BLUE);
		             break;
                }
		if (idx < leafNumber)   {
		    gcL.strokeOval(xcd[idx]-1,ycd[idx]-1,2,2);
		    nState[idx].setState(0);
		    nState[idx].setCoordinates(xcd[idx], ycd[idx]);
		    currentNodeId.add(idx);
		}
		else {
		    double[] trix = {xcd[idx], xcd[idx]+20, xcd[idx]+20, xcd[idx]};
                    double[] triy = {ycd[idx], ycd[idx]+10, ycd[idx]-10, ycd[idx]};
                    gcL.strokePolyline(trix,triy,4);
		    nState[idx].setState(2);
		    nState[idx].setCoordinates(xcd[idx], ycd[idx]);		    
		    currentNodeId.add(idx);
		}
		return;
	    } 
	    else {
		int d1 = DescendantNodes[idx][0]-1;
		int d2 = DescendantNodes[idx][1]-1;
		gcL.setLineWidth(2.0);
		gcL.strokeOval(xcd[idx]-2,ycd[idx]-2,4,4);

		nState[idx].setState(1);
		nState[idx].setCoordinates(xcd[idx], ycd[idx]);
		currentNodeId.add(idx);

		System.out.println("CHK d2 = " + d2);

                switch(nodeColor[d2])  {
		   case 'R': gcL.setStroke(Color.RED);
		             break;
		   case 'W': gcL.setStroke(Color.WHITE);
		             break;
		   case 'Y': gcL.setStroke(Color.YELLOW);
		             break;
		   case 'B': gcL.setStroke(Color.BLUE);
		             break;
                }
		gcL.strokeLine(xcd[idx],ycd[idx],xcd[idx],ycd[d2]);
		gcL.strokeLine(xcd[idx],ycd[d2],xcd[d2],ycd[d2]);
                switch(nodeColor[d1])  {
		   case 'R': gcL.setStroke(Color.RED);
		             break;
		   case 'W': gcL.setStroke(Color.WHITE);
		             break;
		   case 'Y': gcL.setStroke(Color.YELLOW);
		             break;
		   case 'B': gcL.setStroke(Color.BLUE);
		             break;
                }
		gcL.strokeLine(xcd[idx],ycd[idx],xcd[idx],ycd[d1]);
		gcL.strokeLine(xcd[idx],ycd[d1],xcd[d1],ycd[d1]);
		DrawTree(d1);
		DrawTree(d2);
	    }
	}

        double maxD(double[] da) {
	    double mx = 0.0;
            for (int i = 0 ; i < da.length ; ++i) {
		if (mx < da[i])  mx = da[i];
	    }
	    return  mx;
	}

    }


    HashMap<String, ArrayList<Integer>> getColorCode(int taxonomicGroup, List<String> uniqueList)  {
	HashMap<String, ArrayList<Integer>> hashmap = new HashMap<String, ArrayList<Integer>>();
	int col = taxonomicGroup % 3;
	int size = uniqueList.size();
	int mx;
	int numc;
	ArrayList<Integer> code;
	if (size <= 5)  {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 5 ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(250 - i*50);
			   code.add(0);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 1: numc = 0;
		       for (int i = 0 ; i < size ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(250 - i*50);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i < size ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(0);
			   code.add(250 - i*50);
			   hashmap.put(uniqueList.get(i), code);
		       }
	    }	    
	}
	else if (size <= 10)  {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 10 ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(250 - i*25);
			   code.add(0);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 1: numc = 0;
                       for (int i = 0 ; i <= 10 ; ++i) {
			   numc++;
			   if (numc > size)  break;
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(250 - i*25);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i <= 10 ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(0);
			   code.add(250 -i*25);
			   hashmap.put(uniqueList.get(i), code);
		       }
	    }	    
	}
	else if (size <= 25)  {
	    switch(col) {
	       case 0: numc = 0;
		       System.out.println("CHK");  /* CHK */
		       for (int i = 0 ; i <= 5 ; ++i) {
			   for (int j = 0 ; j <= 5 ; ++j)  {
			       numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 - i*50);
			      code.add(0);
			      code.add(j*50);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }  
		       break;
	       case 1: numc = 0;
		       System.out.println("CHK"); /* CHK */
		       for (int i = 0 ; i <=5 ; ++i) {
			   for (int j = 0 ; j <=5 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 - i*50);
			      code.add(80);
			      code.add(j*50);
			      hashmap.put(uniqueList.get(numc-1), code);
			      System.out.println(uniqueList.get(numc-1)); /* CHK */
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i <=5 ; ++i) {
			   for (int j = 0 ; j <=5 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 -i*50);
			      code.add(160);
			      code.add(j*50);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }		       
	    }	    
	}
	else if (size <= 50)  {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 50 ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(250 - i*5);
			   code.add(0);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 1: numc = 0;
		       for (int i = 0 ; i <= 50 ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(250 -i*5);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i < size ; ++i) {
			   numc++;
			   if (numc > size) break;
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(0);
			   code.add(250 - i*5);
			   hashmap.put(uniqueList.get(i), code);
		       }
	    }	    
	}
	else if (size <= 100) {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 10 ; ++i) {
			   for (int j = 0 ; j <= 10 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 -i*25);
			      code.add(0);
			      code.add(j*25);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 1: numc = 0;
		       for (int i = 0 ; i <= 10 ; ++i) {
			   for (int j = 0 ; j <= 10 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 - i*25);
			      code.add(80);
			      code.add(j*25);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i <= 10 ; ++i) {
			   for (int j = 0 ; j <= 10 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(160);
			      code.add(j*25);
			      code.add(250 - i*25);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }		       
	    }	    
	}
	else if (size <= 125)  {
	    switch(col)  {
	       case 0 : numc = 0;
		        for (int i = 0 ; i <= 5 ; ++i) {
		            for (int j = 0 ; j <= 5 ; ++j )  {
			        for (int k = 0 ; k <= 5 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(250 - i*50);
			            code.add(j*50);
			            code.add(k*50);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 1 :numc = 0;
		        for (int i = 0 ; i <= 5 ; ++i) {
		            for (int j = 0 ; j <= 5 ; ++j )  {
			        for (int k = 0 ; k <= 5 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(k*50);
			            code.add(250 - i*50);
			            code.add(j*50);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 2 : numc = 0;
		        for (int i = 0 ; i <= 5 ; ++i) {
		            for (int j = 0 ; j <= 5 ; ++j )  {
			        for (int k = 0 ; k <= 5 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(j*50);
			            code.add(k*50);
			            code.add(250 - i*50);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
	    }
	}
	else if (size <= 250) {
	    switch(col) {
	       case 0: for (int i = 0 ; i < size ; ++i) {
		           code = new ArrayList<Integer>();
			   code.add(250 - i);
			   code.add(0);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 1: for (int i = 0 ; i < size ; ++i) {
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(250 - i);
			   code.add(0);
			   hashmap.put(uniqueList.get(i), code);
		       }
		       break;
	       case 2:for (int i = 0 ; i < size ; ++i) {
		           code = new ArrayList<Integer>();
			   code.add(0);
			   code.add(0);
			   code.add(250 - i);
			   hashmap.put(uniqueList.get(i), code);
		       }
	    }	    
	}
	else if (size <= 625)  {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 25 ; ++i) {
			   for (int j = 0 ; j <= 25 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 - i*10);
			      code.add(0);
			      code.add(j*10);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 1: numc = 0;
		       for (int i = 0 ; i <= 25 ; ++i) {
			   for (int j = 0 ; j <= 25 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(80);
			      code.add(250 - i*10);
			      code.add(j*10);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i <= 25 ; ++i) {
			   for (int j = 0 ; j <= 25 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(160);
			      code.add(j*10);
			      code.add(250 - i*10);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }		       
	    }	    
	}
	else if (size <= 1000)  {
	    switch(col)  {
	       case 0 : numc = 0;
		        for (int i = 0 ; i <= 10 ; ++i) {
		            for (int j = 0 ; j <= 10 ; ++j )  {
			        for (int k = 0 ; k <= 10 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(250 - i*25);
			            code.add(j*25);
			            code.add(k*25);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	        case 1 :numc = 0;
		        for (int i = 0 ; i <= 10 ; ++i) {
		            for (int j = 0 ; j <= 10 ; ++j )  {
			        for (int k = 0 ; k <= 10 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(k*25);
			            code.add(250 - i*25);
			            code.add(j*25);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 2 : numc = 0;
		        for (int i = 0 ; i <= 10 ; ++i) {
		            for (int j = 0 ; j <= 10 ; ++j )  {
			        for (int k = 0 ; k <= 10 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(j*25);
			            code.add(k*25);
			            code.add(250 - i*25);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
	    }
	}
	else if (size <= 2500) {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 50 ; ++i) {
			   for (int j = 0 ; j <= 50 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 - i*5);
			      code.add(0);
			      code.add(j*5);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 1: numc = 0;
		       for (int i = 0 ; i <= 50 ; ++i) {
			   for (int j = 0 ; j <= 50 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(60);
			      code.add(250 - i*5);
			      code.add(j*5);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i <= 50 ; ++i) {
			   for (int j = 0 ; j <= 50 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(160);
			      code.add(j*5);
			      code.add(250 - i*5);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }		       
	    }	    
	}
	else if (size <= 15625)  {
	    switch(col)  {
	       case 0 : numc = 0;
		        for (int i = 0 ; i <= 25 ; ++i) {
		            for (int j = 0 ; j <= 25 ; ++j )  {
			        for (int k = 0 ; k <= 25 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(250 - i*10);
			            code.add(j*10);
			            code.add(k*10);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 1 : numc = 0;
		        for (int i = 0 ; i <= 25 ; ++i) {
		            for (int j = 0 ; j <= 25 ; ++j )  {
			        for (int k = 0 ; k <= 25 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(k*10);
			            code.add(250 - i*10);
			            code.add(j*10);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 2 : numc = 0;
		        for (int i = 0 ; i <= 25 ; ++i) {
		            for (int j = 0 ; j <= 25 ; ++j )  {
			        for (int k = 0 ; k <= 25 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(j*10);
			            code.add(k*10);
			            code.add(250 - i*10);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
	    }
	}
	else if (size <= 62500) {
	    switch(col) {
	       case 0: numc = 0;
		       for (int i = 0 ; i <= 250 ; ++i) {
			   for (int j = 0 ; j <= 250 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(250 - i);
			      code.add(0);
			      code.add(j);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 1: numc = 0;
		       for (int i = 0 ; i <= 250 ; ++i) {
			   for (int j = 0 ; j <= 250 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(60);
			      code.add(250 - i);
			      code.add(j);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }
		       break;
	       case 2: numc = 0;
		       for (int i = 0 ; i <= 250 ; ++i) {
			   for (int j = 0 ; j <= 250 ; ++j)  {
			      numc++;
			      if (numc > size) break;
		              code = new ArrayList<Integer>();
			      code.add(160);
			      code.add(j);
			      code.add(250 - i);
			      hashmap.put(uniqueList.get(numc-1), code);
			   }
			   if (numc > size) break;
		       }		       
	    }	    
	}
	else if (size <= 125000) {
	    switch(col)  {
	       case 0 : numc = 0;
		        for (int i = 0 ; i <= 50 ; ++i) {
		            for (int j = 0 ; j <= 50 ; ++j )  {
			        for (int k = 0 ; k <= 50 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(250 - i*5);
			            code.add(j*5);
			            code.add(k*5);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 1 : numc = 0;
		        for (int i = 0 ; i <= 50 ; ++i) {
		            for (int j = 0 ; j <= 50 ; ++j )  {
			        for (int k = 0 ; k <= 50 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(k*5);
			            code.add(250 - i*5);
			            code.add(j*5);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 2 : numc = 0;
		        for (int i = 0 ; i <= 50 ; ++i) {
		            for (int j = 0 ; j <= 50 ; ++j )  {
			        for (int k = 0 ; k <= 50 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(j*5);
			            code.add(k*5);
			            code.add(250 - i*5);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
	    }
	}
	else {
	    switch(col)  {
	       case 0 : numc = 0;
		        for (int i = 0 ; i <= 250 ; ++i) {
		            for (int j = 0 ; j <= 250 ; ++j )  {
			        for (int k = 0 ; k <= 250 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(250 - i);
			            code.add(j);
			            code.add(k);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 1 : numc = 0;
		        for (int i = 0 ; i <= 250 ; ++i) {
		            for (int j = 0 ; j <= 250 ; ++j )  {
			        for (int k = 0 ; k <= 250 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(k);
			            code.add(250 - i);
			            code.add(j);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
		        break;
	       case 2 : numc = 0;
		        for (int i = 0 ; i <= 250 ; ++i) {
		            for (int j = 0 ; j <= 250 ; ++j )  {
			        for (int k = 0 ; k <= 250 ; ++k)  {
			            numc++;
			            if (numc > size) break;
		                    code = new ArrayList<Integer>();
			            code.add(j);
			            code.add(k);
			            code.add(250 - i);
			            hashmap.put(uniqueList.get(numc-1), code);
			        }
			        if (numc > size) break;
		            }
		            if (numc > size) break;
		        }
	    }
	}

	return hashmap;
    }

    void setY(double y)  {
	y1 = y;
    }
    /*
    char n2AA(int a)  {
	String aa1="ARNDCQEGHILKMFPSTWYV";
	return aa1.charAt(a);
    }
    */
}

