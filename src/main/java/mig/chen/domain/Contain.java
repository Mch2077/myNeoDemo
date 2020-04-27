package mig.chen.domain;



import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "包含")
public class Contain {
	@Id
    @GeneratedValue
	private Long id;
	
	private static String name = "包含";
	
	@StartNode
	private Environment EVO;

	@EndNode
	private Environment EVI;

	public Contain() {
	}

	public Contain(Environment EVO, Environment EVI) {
		this.EVO = EVO;
		this.EVI = EVI;
	}

	public Long getId() {
	    return id;
	}

	public Environment getEVI() {
		return EVI;
	}

	public String getName() {
		return name;
	}
	
}
