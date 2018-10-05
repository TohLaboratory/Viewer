import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Main3 {
 
    public static void main(String[] args) {
        try {
	    // JsonFactoryの生成
	    JsonFactory factory = new JsonFactory();
	    // JsonParserの取得
	    //	    JsonParser parser = factory.createJsonParser(new File("sample3.json"));
	    JsonParser parser = factory.createParser(new File("sample3.json"));
      
	    // 配列の処理
	    if (parser.nextToken() == JsonToken.START_ARRAY) {
		while (parser.nextToken() != JsonToken.END_ARRAY) {
		    // 各オブジェクトの処理
		    if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
			while (parser.nextToken() != JsonToken.END_OBJECT) {
			    String name = parser.getCurrentName();
			    parser.nextToken();
			    // "name"フィールド
			    if ("name".equals(name)) {
				System.out.println(name + ": ");
				while (parser.nextToken() != JsonToken.END_OBJECT) {
				    if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
					System.out.print("    " + parser.getText() + ": ");
				    } else if (parser.getCurrentToken() == JsonToken.VALUE_STRING) {
					System.out.println(parser.getText());
				    }
				}
			    }
			    // "mail"フィールド
			    else if ("mail".equals(name)) {
				System.out.println(name + ": " + parser.getText());
			    } 
			    else {
				parser.skipChildren();
			    }
			}
		    }
		    else {
			parser.skipChildren();
		    }        
		}
	    }
	    else {
		parser.skipChildren();
	    }
        } catch (IOException e) {
            e.printStackTrace();
        }
 
 
    }
 
}
