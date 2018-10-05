import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class GOParse {
    private ArrayList<String> uniprotId = new ArrayList<String>();
    private ArrayList<String> academicName = new ArrayList<String>();
    private ArrayList<String> commonName = new ArrayList<String>();
    private ArrayList<ArrayList<String>> molecularFunction = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> cellularComponent = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> biologicalProcess = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> mFGO = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> cCGO = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> bPGO = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<Integer>> activeSite = new ArrayList<ArrayList<Integer>>();
    private ArrayList<String> mFunction = new ArrayList<String>();
    private ArrayList<String> cComponent = new ArrayList<String>();
    private ArrayList<String> bProcess = new ArrayList<String>();
    private ArrayList<String> mGO = new ArrayList<String>();
    private ArrayList<String> cGO = new ArrayList<String>();
    private ArrayList<String> bGO = new ArrayList<String>();
    private ArrayList<Integer> aPos = new ArrayList<Integer>();
    private List<String> uniqueMF;
    private List<String> uniqueCC;
    private List<String> uniqueBP;
    private String label;
    private String GOId;
    private int[][] mfArray;
    private int[][] ccArray;
    private int[][] bpArray;
    private int check = 0;
    private int check2 = 0;
    private int uniquemolecularFunctionSize;
    private int uniquecellularComponentSize;
    private int uniquebiologicalProcessSize;

    GOParse(String filename) {
        try {
	    // JsonFactoryの生成
	    JsonFactory factory = new JsonFactory();
	    // JsonParserの取得
	    //	    JsonParser parser = factory.createJsonParser(new File("sample3.json"));
	    JsonParser parser = factory.createParser(new File(filename));
      
	    // 配列の処理
	    if (parser.nextToken() == JsonToken.START_ARRAY) {
		while (parser.nextToken() != JsonToken.END_ARRAY) {
		    // 各オブジェクトの処理
		    if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
			while (parser.nextToken() != JsonToken.END_OBJECT) {
			    if (parser.getCurrentToken() == JsonToken.FIELD_NAME && "input".equals(parser.getText())) {
				    if (check2 != 0)   {
					molecularFunction.add(mFunction);
					cellularComponent.add(cComponent);
					biologicalProcess.add(bProcess);
					mFGO.add(mGO);
					cCGO.add(cGO);
					bPGO.add(bGO);
					activeSite.add(aPos);
				    }
				    check = 0;
				    mFunction = new ArrayList<String>();
				    cComponent = new ArrayList<String>();
				    bProcess = new ArrayList<String>();
				    mGO = new ArrayList<String>();
				    cGO = new ArrayList<String>();
				    bGO = new ArrayList<String>();
				    aPos = new ArrayList<Integer>();
				    check2++;
				    parser.nextToken();
				    if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
					while(parser.nextToken() != JsonToken.END_OBJECT) {
					    if (parser.getCurrentToken() == JsonToken.FIELD_NAME && "id".equals(parser.getText())) {
						parser.nextToken();
						String name = parser.getText();
						String[]  name2 = name.split("/");
						uniprotId.add(name2[name2.length-1]);
					    }
					}
				    }
			    }
			    else if (parser.getCurrentToken() == JsonToken.FIELD_NAME && "output".equals(parser.getText())) {
				parser.nextToken();
				if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
				    while(parser.nextToken() != JsonToken.END_ARRAY)  {
					if (parser.getCurrentToken() ==JsonToken.START_OBJECT)  {
					    while(parser.nextToken() != JsonToken.END_OBJECT) {
						if (parser.getCurrentToken() == JsonToken.FIELD_NAME  &&  "idclass".equals(parser.getText()))  {
						    parser.nextToken();
						    String name = parser.getText();
						    String[] name2 = name.split("/");
						    if ("Taxon".equals(name2[name2.length-1]))  check = 1;         // idclass = Taxon
						    else if ("owl#Class".equals(name2[name2.length-1]))  check = 2;  // idclass = owl#Class
						    else if ("faldo#ExactPosition".equals(name2[name2.length-1])) check = 3; // idclass = faldo#ExactPosition
						}
						else if (parser.getCurrentToken() == JsonToken.FIELD_NAME  &&  "result".equals(parser.getText()))  {
							parser.nextToken();
							if (parser.getCurrentToken() == JsonToken.START_ARRAY)  {
							    while(parser.nextToken() != JsonToken.END_ARRAY)  {
								if (parser.getCurrentToken() == JsonToken.START_OBJECT)  {
								    while(parser.nextToken() != JsonToken.END_OBJECT) {
									if (parser.getCurrentToken() == JsonToken.FIELD_NAME && "label".equals(parser.getText()))  {
									    parser.nextToken();
									    if (check == 1)  {
										//										System.out.println("academic name : " + parser.getText());
										academicName.add(parser.getText());
									    }
									    else if (check == 2) {
										label = parser.getText(); 
										//										System.out.println("   label = " + label);
									    }
									}
									else if (parser.getCurrentToken() == JsonToken.FIELD_NAME && "id".equals(parser.getText()))  {
									    parser.nextToken();
									    if (check == 2)  {
										String id1 = parser.getText();
										String[] id2 = id1.split("/");
										GOId = id2[id2.length-1];
										//										System.out.println("    id = " + id);
									    }
									}
									else if (parser.getCurrentToken() == JsonToken.FIELD_NAME && "info".equals(parser.getText()))  {
									    parser.nextToken();
									    if (check == 1)  {
										//										System.out.println("common name : " + parser.getText());
										commonName.add(parser.getText());
									    }
									    else if (check == 2)  {
										String info = parser.getText();
                    								  //										System.out.println("    info = " + info);
										if ("null".equals(info))  continue;
										else {
										    if ("molecular_function".equals(info))  {
											mFunction.add(label);
											mGO.add(GOId);
										    }
										    else if ("cellular_component".equals(info)) {
											cComponent.add(label);
											cGO.add(GOId);
										    }
										    else if ("biological_process".equals(info)) {
											bProcess.add(label);
											bGO.add(GOId);
										    }
										}
									    }
									    else if (check == 3)  {
										String info = parser.getText();

										if ("[ ]".equals(info))  continue;
										else {
										    System.out.println(info);
										    //										    aPos.add(Integer.valueOf(info.substring(1,info.length()-1)));
										}
									    }
									}
								    }
								}
							    }
							}
						}					        
						else {
						    parser.skipChildren();
						}
					    }   
					}
				    } 
				}
			    }
			    else  { 
				parser.skipChildren();
			    }
		         }
		      }
		}

		//	    else {
		//		parser.skipChildren();
		//          }
	    }
	}  catch (IOException e) {
            e.printStackTrace();
        }

	molecularFunction.add(mFunction);
	cellularComponent.add(cComponent);
	biologicalProcess.add(bProcess);
	mFGO.add(mGO);
	cCGO.add(cGO);
	bPGO.add(bGO);

        ArrayList<String> nonuniqueMF = new ArrayList<String>();
	ArrayList<String> nonuniqueCC = new ArrayList<String>();
	ArrayList<String> nonuniqueBP = new ArrayList<String>();

	for (int ii = 0 ; ii < molecularFunction.size() ; ++ii) {
	    for (int j = 0 ; j < molecularFunction.get(ii).size() ; ++j)  {
	        nonuniqueMF.add(molecularFunction.get(ii).get(j));
	    }
	    for (int j = 0 ; j < cellularComponent.get(ii).size() ; ++j)  {
	        nonuniqueCC.add(cellularComponent.get(ii).get(j));
	    }
	    for (int j = 0 ; j < biologicalProcess.get(ii).size() ; ++j)  {
		nonuniqueBP.add(biologicalProcess.get(ii).get(j));
	    }
	}

	List<String> mflist = nonuniqueMF;
        List<String> cclist = nonuniqueCC;
	List<String> bplist = nonuniqueBP;

	Set<String> setmf = new HashSet<String>();
        setmf.addAll(mflist);
        Set<String> setcc = new HashSet<String>();
        setcc.addAll(cclist);
	Set<String> setbp = new HashSet<String>();
	setbp.addAll(bplist);

        uniqueMF = new ArrayList<String>();
        uniqueMF.addAll(setmf);
        uniqueCC = new ArrayList<String>();
        uniqueCC.addAll(setcc);
	uniqueBP = new ArrayList<String>();
	uniqueBP.addAll(setbp);

        uniquemolecularFunctionSize = uniqueMF.size();
        uniquecellularComponentSize = uniqueCC.size();
	uniquebiologicalProcessSize = uniqueBP.size();

        mfArray = new int[molecularFunction.size()][uniqueMF.size()];
        for (int ii = 0 ; ii < molecularFunction.size() ; ++ii) {
       	    for (int j = 0 ; j < uniqueMF.size() ; ++j)  {
		mfArray[ii][j] = 0;
	    }
	}
	for (int ii = 0 ; ii < molecularFunction.size() ; ++ii)   {
	     for (int j = 0 ; j < molecularFunction.get(ii).size() ; ++j)  {
	        for (int k = 0 ; k < uniqueMF.size() ; ++k)  {
	      	    if (uniqueMF.get(k).equals(molecularFunction.get(ii).get(j))) {
		        mfArray[ii][k] = 1;
		    }
		}
	    }
	}

	ccArray = new int[cellularComponent.size()][uniqueCC.size()];
	for (int ii = 0 ; ii < cellularComponent.size() ; ++ii) {
	    for (int j = 0 ; j < uniqueCC.size() ; ++j)  {
	        ccArray[ii][j] = 0;
	    }
	}
	for (int ii = 0 ; ii < cellularComponent.size() ; ++ii)   {
      	    for (int j = 0 ; j < cellularComponent.get(ii).size() ; ++j)  {
	      for (int k = 0 ; k < uniqueCC.size() ; ++k)  {
		    if (uniqueCC.get(k).equals(cellularComponent.get(ii).get(j))) {
			    ccArray[ii][k] = 1;
		    }
		}
	    }
	}

	bpArray = new int[biologicalProcess.size()][uniqueBP.size()];
	for (int ii = 0 ; ii < biologicalProcess.size() ; ++ii)   {
	    for (int j = 0 ; j < uniqueBP.size() ; ++j)  {
		bpArray[ii][j] = 0;
	    }
	}
        for (int ii = 0 ; ii < biologicalProcess.size() ; ++ii)  {
	    for (int j = 0 ; j < biologicalProcess.get(ii).size() ; ++j)  {
		for (int k = 0 ; k < uniqueBP.size() ; ++k) {
		    if (uniqueBP.get(k).equals(biologicalProcess.get(ii).get(j))) {
			bpArray[ii][k] = 1;
		    }
		}
	    }
	}
    }

    int getNumberOfObject()  {
	return  uniprotId.size();
    }
    int getuniquemolecularFunctionSize() {
	return  uniquemolecularFunctionSize;
    }
    int getuniquecellularComponentSize() {
	return  uniquecellularComponentSize;
    }
    int getuniquebiologicalProcessSize() {
	return uniquebiologicalProcessSize;
    }
    ArrayList<String> getuniProtId() {
	return uniprotId;
    }
    ArrayList<String> getacademicName() {
	return academicName;
    }
    ArrayList<String> getcommonName() {
	return commonName;
    }
    ArrayList<ArrayList<String>> getmolFunction() {
	return molecularFunction;
    }
    ArrayList<ArrayList<String>> getcelComponent()  {
	return cellularComponent;
    }
    ArrayList<ArrayList<String>> getbioProcess() {
	return biologicalProcess;
    }
    ArrayList<ArrayList<String>> getmFGO() {
	return mFGO;
    }
    ArrayList<ArrayList<String>> getcCGO() {
	return cCGO;
    }
    ArrayList<ArrayList<String>> getbPGO() {
	return bPGO;
    }
    ArrayList<String> getAcademicName()  {
	return  academicName;
    }
    int[][] getmfArray() {
	return mfArray;
    }
    int[][] getccArray() {
	return ccArray;
    }
    int[][] getbpArray() {
	return bpArray;
    }
    List<String> getuniqueMF()  {
	return uniqueMF;
    }
    List<String> getuniqueCC()  {
	return uniqueCC;
    }
    List<String> getuniqueBP()  {
	return uniqueBP;
    }
}
