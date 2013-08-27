package models;

import java.util.ArrayList;
import java.util.List;

public class Enum {
	private String name;
	private List<String> values;

	public Enum() {
		this.values = new ArrayList<String>();
	}

	public void add(String value) {
		values.add(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getNameUpper() {
		char[] tmp = name.toCharArray();
		tmp[0] = Character.toUpperCase(tmp[0]);
		return new String(tmp);
	}

	public String getInstanceName() {
		char[] tmp = name.toCharArray();
		tmp[0] = Character.toLowerCase(tmp[0]);
		return new String(tmp);
	}
}