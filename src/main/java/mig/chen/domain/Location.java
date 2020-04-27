package mig.chen.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Location {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private static String color="Blue";

	public Location() {}
	public Location(String name){
		this.name=name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
}