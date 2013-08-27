package models;

public class Property{
	private String type;
	private String name;
	
	public Property(){
	}
	
	public Property(String name, String type) {
		this.type = type;
		this.name = name;
	}

	public String getNameUpper(){
		char[] tmp = name.toCharArray();
		tmp[0]=Character.toUpperCase(tmp[0]);
		return new String(tmp); 
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}