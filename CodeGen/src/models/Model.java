package models;
import java.util.ArrayList;
import java.util.List;



public class Model{
	private String name;
	private String extendClass;
	private List<Property> properties;
	
	public Model(){
		this.properties = new ArrayList<Property>();
	}
	
	public Model(String name, List<Property> properties) {
		this.name = name;
		this.properties = properties;
	}

	public void add(Property ppt){
		properties.add(ppt);
	}

	public String getName() {
		return name;
	}
	
	public String getInstanceName() {
		char[] tmp = name.toCharArray();
		tmp[0]=Character.toLowerCase(tmp[0]);
		return new String(tmp); 
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtendClass() {
		return extendClass;
	}

	public void setExtendClass(String extendClass) {
		this.extendClass = extendClass;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
}