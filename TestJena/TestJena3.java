import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.Prologue;

public class TestJena3 {

    public static void main(String[] args) {

 
	// クエリを投げるSPARQLエンドポイント
	String service = "http://ja.dbpedia.org/sparql";


	// SPARQLクエリ
    String queryString = "prefix skos: <http://www.w3.org/2004/02/skos/core#> " + 
    "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
	"prefix dbpedia-owl: <http://dbpedia.org/ontology/>" + 
    "SELECT DISTINCT ?mascot ?name " +
    "WHERE {" + "?areamascots skos:broader <http://ja.dbpedia.org/resource/Category:地域限定のマスコット> ;" + "rdfs:label ?areaname ." + "?mascot dbpedia-owl:wikiPageWikiLink ?areamascots ;" + "rdfs:label ?name.}";

    Query query = QueryFactory.create(queryString);
    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
    ResultSet results = qe.execSelect();
    ResultSetFormatter.out(System.out, results, query);
    qe.close();

    }

}
