package mig.chen.domain;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@NodeEntity
public class Property {
	@Id
	@GeneratedValue
	private Long id;
	private String value;
	private static String color="Pink";
	
	public Property() {}
	public Property(String value){
		this.value=value;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getColor() {
		return color;
	}
	
}