package mig.chen.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import mig.chen.domain.Environment;


public interface CqlRepository extends Neo4jRepository<Environment, Long> {
	
	@Query("MATCH p=(a)-[*]->(b) where a.name=~$startNode And b.name=~$endNode RETURN p")
	Collection<Environment> findRelWithoutRel(@Param("startNode")String startNode, @Param("endNode")String endNode);
	
	@Query("MATCH p=(a)-[*]->(b) where b.name=~$endNode RETURN p")
	Collection<Environment> findRelWithoutRelAndStart(@Param("endNode")String endNode);
	
	@Query("MATCH p=(a)-[*]->(b) where a.name=~$startNode RETURN p")
	Collection<Environment> findRelWithoutRelAndEnd(@Param("startNode")String startNode);
	
	@Query("MATCH p=(a)-[]->(b) where b.name=$name RETURN a")
	List<Environment> findUpperLevel(@Param("name") String name);
	
	@Query("MATCH p=(b)-[]->(c) where b.name=$name RETURN c")
	List<Environment> findLowerLevel(@Param("name") String name);
	
	@Query("MATCH p=(a)-[*1..9]->(b) where a.name=~$name RETURN p")
	Collection<Environment> findGraph(@Param("name") String name);
	
	@Query("call apoc.cypher.run($cqlString, null) yield value return value")
	Collection<Environment> findRel(@Param("cqlString")String cqlString);
	
	@Query("MATCH (n) where n.name=$name set n.name=$value")
	void updateNode(@Param("name") String name,@Param("value") String value);
	
	@Query("MATCH (n) where n.name=$name delete n")
	void deleteNode(@Param("name") String name);
	
	@Query("MATCH (a),(b) where a.name=$startNode and b.name=$endNode Create (a)-[r]->(b) set r.name=$relationship Return a,b,r")
	Collection<Environment> createRel(@Param("startNode")String startNode,@Param("endNode")String endNode,@Param("relationship")String relationship);
}

