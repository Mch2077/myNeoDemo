package mig.chen.domain;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "是")
public class Is {
	@Id
    @GeneratedValue
	private Long id;
	
	private static String name = "是";
	
	@StartNode
	private Environment ev;

	@EndNode
	private Property property;

	public Is() {
	}

	public Is(Environment ev, Property property) {
		this.ev = ev;
		this.property = property;
	}

	public Long getId() {
	    return id;
	}
	
	public Property getProperty() {
		return property;
	}

	public String getName() {
		return name;
	}

}
