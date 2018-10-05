/*
http://kottas.hatenablog.com/entry/2013/05/21/192100  より
*/

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class TestJena {

    public static void main(String[] args) {

 
	// クエリを投げるSPARQLエンドポイント
	String service = "http://ja.dbpedia.org/sparql";


	// SPARQLクエリ
	String queryString = "SELECT distinct ?p " +
	    "WHERE{?s ?p ?o}" +
	    "LIMIT 100";

	Query query = QueryFactory.create(queryString);
	QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
	ResultSet results = qe.execSelect();
	ResultSetFormatter.out(System.out, results, query);
	qe.close();

    }

}
