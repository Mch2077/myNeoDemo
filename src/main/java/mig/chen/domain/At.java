package mig.chen.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "位于")
public class At {
	@Id
    @GeneratedValue
	private Long id;
	
	private static String name = "位于";
	
	@StartNode
	private Environment ev;

	@EndNode
	private Location location;

	public At() {
	}

	public At(Environment ev, Location location) {
		this.ev = ev;
		this.location = location;
	}

	public Long getId() {
	    return id;
	}
	
	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

}