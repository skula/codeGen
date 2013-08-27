package models;

public class Field{
	private String type;
	private String name;
	private boolean primaryKey;
	
	public Field(){
	}
	
	public Field(String name, String type, boolean primaryKey) {
		this.type = type;
		this.name = name;
		this.primaryKey = primaryKey;
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
	
	public boolean isPrimaryKey(){
		return primaryKey;
	}
	
	public void setPrimaryKey(boolean primaryKey){
		this.primaryKey = primaryKey;
	}
}