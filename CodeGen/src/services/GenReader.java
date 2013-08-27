package services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.Database;
import models.Enum;
import models.Field;
import models.Model;
import models.Property;
import models.Table;

public class GenReader {
	private String packageRoot;
	private List<Model> models;
	private List<Enum> enums;

	private Database database;

	public static void main(String args[]) throws IOException {
		new GenReader("in.txt");
	}

	public GenReader(String filename) throws IOException {
		this.database = new Database();
		this.models = new ArrayList<Model>();
		this.enums = new ArrayList<Enum>();
		this.packageRoot = "";
		parse(filename);
	}

	public void parse(String filename) throws IOException {
		InputStream ips = new FileInputStream(filename);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);

		String ligne = "";
		while ((ligne = br.readLine()) != null) {
			if (ligne.startsWith("dbn:")) {
				database.setName(ligne.substring(4));
			} else if (ligne.startsWith("pak:")) {
				this.packageRoot = ligne.substring(4);
			} else if (ligne.startsWith("mod:")) {
				parseModel(ligne.substring(4));
			} else if (ligne.startsWith("tab:")) {
				parseTable(ligne.substring(4));
			} else if (ligne.startsWith("enu:")) {
				parseEnum(ligne.substring(4));
			}
		}
		br.close();
	}

	// person=id=TEXT:lastname,firstname;INTEGER:id;DATE:birthday
	public void parseTable(String line) throws IOException {
		Table table = new Table();
		// decoupé en [nom][ppts]
		String[] tab1 = line.split("=");
		table.setName(tab1[0].toLowerCase());
		String primaryKey = tab1[1];
		// decoupé en [type1:nom1,nom2][type2:nom3,nom4]
		for (String ppts : tab1[2].split(";")) {
			// decoupé en [type][nom1,nom2]
			String tab2[] = ppts.split(":");
			String typePpt = tab2[0];
			// decoupé en [nom1][nom2][nom3]
			for (String nomPpt : tab2[1].trim().split(",")) {
				table.add(new Field(nomPpt, typePpt, nomPpt.equals(primaryKey)));
			}
		}
		database.addTable(table);
	}

	// person=String:lastname,firstname;int:age,taille
	public void parseModel(String line) throws IOException {
		Model model = new Model();
		// decoupé en [nom][ppts]
		String[] tab1 = line.split("=");

		String[] ext = tab1[0].split(":");
		model.setName(ext[0]);
		model.setExtendClass(ext[1]);

		// decoupé en [type1:nom1,nom2][type2:nom3,nom4]
		for (String ppts : tab1[1].split(";")) {
			// decoupé en [type][nom1,nom2]
			String tab2[] = ppts.split(":");
			String typePpt = tab2[0];
			// decoupé en [nom1][nom2][nom3]
			for (String nomPpt : tab2[1].trim().split(",")) {
				model.add(new Property(nomPpt, typePpt));
			}
		}
		models.add(model);
	}

	// persontype=typ1,typ2,...,typen
	public void parseEnum(String line) throws IOException {
		Enum e = new Enum();
		// decoupé en [nom][ppts]
		String[] tab1 = line.split("=");
		e.setName(tab1[0]);
		// decoupé en [type1:nom1,nom2][type2:nom3,nom4]
		for (String value : tab1[1].split(",")) {
			e.add(value);
		}
		enums.add(e);
	}

	public List<Model> getModels() {
		return models;
	}

	public List<Enum> getEnums() {
		return enums;
	}

	public String getPackageRoot() {
		return packageRoot;
	}

	public Database getDatabase() {
		return database;
	}
}