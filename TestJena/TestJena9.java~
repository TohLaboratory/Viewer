import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.Prologue;

public class TestJena9 {

    public static void main(String[] args) {

 
	// クエリを投げるSPARQLエンドポイント
	String service = "http://ja.dbpedia.org/sparql";


	// SPARQLクエリ
    String queryString =
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
	"PREFIX dbpedia-owl:  <http://dbpedia.org/ontology/>" +
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	"PREFIX dbpedia-ja: <http://ja.dbpedia.org/resource/>" +
	"PREFIX category-ja: <http://ja.dbpedia.org/resource/Category:>" +
	"select distinct ?pref (count(?s) AS ?c) where {" + 
	"    ?pref rdf:type dbpedia-owl:Place." +
	"    ?pref dbpedia-owl:wikiPageWikiLink category-ja:日本の都道府県." +
	"    ?s rdf:type dbpedia-owl:Person;" +
	"    dbpedia-owl:wikiPageWikiLink category-ja:日本のアイドル;" +
	"    dbpedia-owl:birthPlace ?pref." +
	"}GROUP BY ?pref ORDER BY ?c" ;

    Query query = QueryFactory.create(queryString);
    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
    ResultSet results = qe.execSelect();
    ResultSetFormatter.out(System.out, results, query);
    qe.close();

    }

}
