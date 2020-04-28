package mig.chen.util;

import java.sql.SQLException;
import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;


public class ConfigNeo4jDBUtil {
    
    public static List<Record> findRel(String cqlString) throws SQLException {
    	 Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "admin" ) );
         Session session = driver.session();
         Result result = session.run(cqlString);
         List<Record> list = result.list();
         session.close();
         driver.close();
         return list;
    }

}
