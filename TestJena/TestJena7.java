import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.core.Prologue;

public class TestJena7 {

    public static void main(String[] args) {

 
	// クエリを投げるSPARQLエンドポイント
	String service = "http://ja.dbpedia.org/sparql";


	// SPARQLクエリ
    String queryString = 
	"PREFIX category-ja:<http://ja.dbpedia.org/resource/Category:>" + 
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	"PREFIX dbpedia-owl:<http://dbpedia.org/ontology/>" +
	"PREFIX dbpedia-ja: <http://ja.dbpedia.org/resource/>" +
	"PREFIX prop-ja:<http://ja.dbpedia.org/property/>" +
	"select distinct ?s ?o where {" + 
	"  ?s rdf:type dbpedia-owl:Place;" + 
	"     dbpedia-owl:wikiPageWikiLink category-ja:日本の都道府県;" +
	"     dbpedia-owl:postalCode ?o.        " +
	"}ORDER BY DESC(?o)" ;

    Query query = QueryFactory.create(queryString);
    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
    ResultSet results = qe.execSelect();
    ResultSetFormatter.out(System.out, results, query);
    qe.close();

    }

}
