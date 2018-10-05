import java.io.File;
import java.io.IOException;
import java.util.*;
 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class Main2 {
 
    public static void main(String[] args) {
        try {
	    // ObjectMapperを作成
	    ObjectMapper mapper = new ObjectMapper();
	    // ルートノードを取得
	    JsonNode rootNode = mapper.readValue(new File("mydata.json"), JsonNode.class);
      
	    JsonNode current;
	    for (int i=0; (current = rootNode.get(i)) != null; i++) {
		// "name"オブジェクトのノードを取得
		JsonNode nameNode = current.get("name");
		System.out.println("name: ");
		// "name"オブジェクトのフィールドデータを取得して表示
		Iterator<String> nameNodeFields = nameNode.getFieldNames();
		while (nameNodeFields.hasNext()) {
		    String nameNodeField = nameNodeFields.next();
		    System.out.println("    " + nameNodeField + ": " + nameNode.get(nameNodeField));
		}
    
		// "mail"フィールドのノードを取得してデータを表示
		JsonNode mailNode = current.get("mail");
		System.out.println("mail: " + mailNode.getTextValue());
	    }

        } catch (IOException e) {
            e.printStackTrace();
        }
 
 
    }
 
}
