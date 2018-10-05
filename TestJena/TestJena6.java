import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.Prologue;

public class TestJena6 {

    public static void main(String[] args) {

 
	// クエリを投げるSPARQLエンドポイント
	String service = "http://ja.dbpedia.org/sparql";


	// SPARQLクエリ
    String queryString = 
	"PREFIX category-ja:<http://ja.dbpedia.org/resource/Category:>" + 
	"PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>" +
	"PREFIX foaf:<http://xmlns.com/foaf/0.1/>" +
	"PREFIX prop-ja:<http://ja.dbpedia.org/property/>" +
	"select * where {" +
	"  ?uri dbpedia-owl:wikiPageWikiLink category-ja:日本百名山." +
	"  ?uri foaf:name ?name." +
	"  ?uri prop-ja:標高 ?altitude. } order by ?altitude" ;

    Query query = QueryFactory.create(queryString);
    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
    ResultSet results = qe.execSelect();
    ResultSetFormatter.out(System.out, results, query);
    qe.close();

    }

}
