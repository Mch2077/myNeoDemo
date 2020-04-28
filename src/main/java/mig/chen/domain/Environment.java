package mig.chen.domain;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
public class Environment {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String label;
	private static String color="Red";

	
	public Environment() {}
	public Environment(String name,String label) {
		this.name=name;
		this.label=label;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnoreProperties("Environment")
	@Relationship(type = "包含", direction = Relationship.OUTGOING)
	private List<Contain> container;
	
	public List<Contain> getContainer() {
		return container;
	}

	public void addContainer(Contain container) {
		if (this.container == null) {
			this.container = new ArrayList<>();
		}
		this.container.add(container);
	}
	
	@JsonIgnoreProperties("Environment")
	@Relationship(type = "是", direction = Relationship.OUTGOING)
	private List<Is> propertiesList;
	
	public List<Is> getPropertiesList() {
		return propertiesList;
	}

	public void addPropertiesList(Is is) {
		if (this.propertiesList == null) {
			this.propertiesList = new ArrayList<>();
		}
		this.propertiesList.add(is);
	}
	
	@JsonIgnoreProperties("Environment")
	@Relationship(type = "位于", direction = Relationship.OUTGOING)
	private List<At> locationList;
	
	public List<At> getLocationList() {
		return locationList;
	}

	public void addLocationList(At at) {
		if (this.locationList == null) {
			this.locationList = new ArrayList<>();
		}
		this.locationList.add(at);
	}
	
	@JsonIgnoreProperties("Environment")
	@Relationship(type = "弱正相关", direction = Relationship.OUTGOING)
	private List<WeakPositiveCorrelation> wpcList;
	
	public List<WeakPositiveCorrelation> getWpcList() {
		return wpcList;
	}

	public void addWpcList(WeakPositiveCorrelation wpc) {
		if (this.wpcList == null) {
			this.wpcList = new ArrayList<>();
		}
		this.wpcList.add(wpc);
	}
	
	@JsonIgnoreProperties("Environment")
	@Relationship(type = "强正相关", direction = Relationship.OUTGOING)
	private List<StrongPositiveCorrelation> spcList;
	
	public List<StrongPositiveCorrelation> getSpcList() {
		return spcList;
	}

	public void addSpcList(StrongPositiveCorrelation spc) {
		if (this.spcList == null) {
			this.spcList = new ArrayList<>();
		}
		this.spcList.add(spc);
	}
	
	@JsonIgnoreProperties("Environment")
	@Relationship(type = "弱负相关", direction = Relationship.OUTGOING)
	private List<WeakNegativeCorrelation> wncList;
	
	public List<WeakNegativeCorrelation> getWncList() {
		return wncList;
	}

	public void addWpcList(WeakNegativeCorrelation wnc) {
		if (this.wncList == null) {
			this.wncList = new ArrayList<>();
		}
		this.wncList.add(wnc);
	}
	
	@JsonIgnoreProperties("Environment")
	@Relationship(type = "强负相关", direction = Relationship.OUTGOING)
	private List<StrongNegativeCorrelation> sncList;
	
	public List<StrongNegativeCorrelation> getSncList() {
		return sncList;
	}

	public void addSncList(StrongNegativeCorrelation snc) {
		if (this.sncList == null) {
			this.sncList = new ArrayList<>();
		}
		this.sncList.add(snc);
	}
	
}
