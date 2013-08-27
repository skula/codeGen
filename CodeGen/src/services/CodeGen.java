package services;
import java.io.IOException;

import models.Model;



public class CodeGen{

	public static void main(String args[]) throws IOException{
		GenReader gr = new GenReader("in.txt");
		for(Model model : gr.getModels()){
			GenWriter.genModel(model, gr.getPackageRoot() + ".models");
			GenWriter.genAdapter(model, gr.getPackageRoot() + ".activities.adapters");		
			GenWriter.genActivity(model, gr.getPackageRoot() + ".activities");
			//GenWriter.genItemListLayout(model);
			//GenWriter.genDialogLayout(model);
		}
		GenWriter.genDatabase(gr.getDatabase(), gr.getPackageRoot() + ".services");
	}
}