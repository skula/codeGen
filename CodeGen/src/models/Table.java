package models;
import java.util.ArrayList;
import java.util.List;



public class Table{
	private String name;
	private List<Field> fields;
	
	public Table(){
		this.fields = new ArrayList<Field>();
	}
	
	public String getNameUpper(){
		char[] tmp = name.toCharArray();
		tmp[0]=Character.toUpperCase(tmp[0]);
		return new String(tmp); 
	}
	
	public void add(Field field){
		fields.add(field);
	}
	
	public String getFieldsString(){
		String res = "";
		for(Field f : fields){
			if(!res.isEmpty()){
				res+=", ";
			}
			res+=f.getName()+" "+f.getType();
			res+=f.isPrimaryKey()?" PRIMARY KEY":"";
		}
		return res;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public void addField(Field field){
		fields.add(field);
	}
}