package models;
import java.util.ArrayList;
import java.util.List;

public class Database{
	private List<Table> tables;
	private String name;
	
	public Database(){
		this.name = "____";
		this.tables = new ArrayList<Table>(); 
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addTable(Table table) {
		tables.add(table);
	}
}