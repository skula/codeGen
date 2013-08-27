package services;
import java.io.IOException;

import models.Model;



public class CodeGen{

	public static void main(String args[]) throws IOException{
		GenWritter.initDirectories();
		GenReader gr = new GenReader("in.txt");
		for(Model model : gr.getModels()){
			GenWritter.genModel(model, gr.getPackageRoot() + ".models");
			GenWritter.genAdapter(model, gr.getPackageRoot() + ".activities.adapters");		
			GenWritter.genActivity(model, gr.getPackageRoot() + ".activities");
			GenWritter.genModelLayout(model);
			GenWritter.genDialogLayout(model);
		}
		GenWritter.genConstants(gr.getPackageRoot() + ".constants");
		GenWritter.genDatabase(gr.getDatabase(), gr.getPackageRoot() + ".services");
		GenWritter.genAbstractDialog(gr.getPackageRoot() + ".activities.dialogs");
	}
}