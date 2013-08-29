package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import models.Database;
import models.Enum;
import models.Field;
import models.Model;
import models.Property;
import models.Table;

public class GenWritter {
	
	public static void genConstants(String namespace) throws IOException{
		PrintWriter out = new PrintWriter(new FileWriter("out/src/constants/Cnst.java"));
		out.println("package " + namespace + ";");
		out.println("");
		out.println("");
		
		out.println("public class Cnst {");
		out.println("	public static final int MODE_CRE = 1;");
		out.println("	public static final int MODE_MOD = 2;");
		out.println("}");
		
		out.close();
	}
	
	public static void genModelDialog(Model model, String rootNamespace) throws IOException{
		PrintWriter out = new PrintWriter(new FileWriter("out/src/activities/dialogs/"+model.getName()+"Dialog.java"));
		out.println("package " + rootNamespace +".activities.dialogs;");
		out.println("");
		out.println("import android.content.Context;");
		out.println("import android.widget.Button;");
		out.println("import android.widget.EditText;");
		out.println("");
		out.println("import " + rootNamespace +".R;");
		out.println("import " + rootNamespace + ".definitions.Cnst;");
		out.println("");
		out.println("public class " + model.getName() + "Dialog extends AbstractDialog {");
		for(Property p : model.getProperties()){
			out.println("	private EditText " + p.getName() + "Txt;");
		}
		out.println("");
		out.println("	public " + model.getName() + "Dialog(final Context context, final MainActivity mainActivity, int mode, int idItem) {");
		out.println("		super(context, mainActivity, mode, idItem);");
		out.println("");
		out.println("		if (mode == Cnst.MODE_MOD) {");
		out.println("			" + model.getName() + " " +model.getInstanceName() + " = dbService.get" + model.getName() + "(idItem);");
		for(Property p : model.getProperties()){
			out.println("			" + p.getName().toLowerCase() + "Txt.setText(" + model.getInstanceName() + ".get" + p.getNameUpper() + "());");
		}
		out.println("			setTitle(\"Modification de _______\");");
		out.println("		}else{");
		out.println("			setTitle(\"Ajout de _______\");");
		out.println("		}");
		out.println("	}");
		out.println("");
		out.println("	public void initialise() {");
		out.println("		this.setContentView(R.layout." + model.getName().toLowerCase() + "_dialog_layout);");
		out.println("		");
		out.println("		this.btn1 = (Button) findViewById(R.id." + model.getName().toLowerCase() + "_dial_btn1);");
		out.println("		this.btn2 = (Button) findViewById(R.id." + model.getName().toLowerCase() + "_dial_btn2);");
		out.println("		this.btn3 = (Button) findViewById(R.id." + model.getName().toLowerCase() + "_dial_btn3);");
		for(Property p : model.getProperties()){
			out.println("		this." + p.getName() + "Txt = (EditText) findViewById(R.id." + model.getName().toLowerCase() + "_dial_" + p.getName() +");");
		}
		out.println("		");
		out.println("	}");
		out.println("");
		out.println("	public void insert() {");
		out.println("		");
		out.println("	}");
		out.println("");
		out.println("	public void update() {");
		out.println("		");
		out.println("	}");
		out.println("");
		out.println("	public void delete() {");
		out.println("		");
		out.println("	}");
		out.println("}");
		out.close();
	}
	
	public static void genAbstractDialog(String namespace) throws IOException{
		PrintWriter out = new PrintWriter(new FileWriter("out/src/activities/dialogs/AbstractDialog.java"));
		out.println("package " + namespace + ";");
		out.println("");
		out.println("");
		out.println("import android.app.Dialog;");
		out.println("import android.content.Context;");
		out.println("import android.content.DialogInterface;");
		out.println("import android.view.View;");
		out.println("import android.view.Window;");
		out.println("import android.widget.Button;");
		out.println("");
		out.println("public abstract class AbstractDialog extends Dialog {");
		out.println("	protected Button btn1;");
		out.println("	protected Button btn2;");
		out.println("	protected Button btn3;");
		out.println("");
		out.println("	protected int mode;");
		out.println("	protected int idItem;");
		out.println("	protected DBService dbService;");
		out.println("");
		out.println("	public abstract void initialise();");
		out.println("");
		out.println("	public abstract void insert();");
		out.println("");
		out.println("	public abstract void update();");
		out.println("");
		out.println("	public abstract void delete();");
		out.println("");
		out.println("	public AbstractDialog(final Context context,");
		out.println("			final MainActivity mainActivity, int mode, int idItem) {");
		out.println("		super(context);");
		out.println("		this.mode = mode;");
		out.println("		this.idItem = idItem;");
		out.println("		this.dbService = new DBService(context);");
		out.println("		requestWindowFeature(Window.FEATURE_NO_TITLE);");
		out.println("		initialise();");
		out.println("		handleMode();");
		out.println("		");
		out.println("");
		out.println("		btn1.setOnClickListener(new View.OnClickListener() {");
		out.println("			public void onClick(View v) {");
		out.println("				if (getMode() == Cnst.MODE_CRE) {");
		out.println("					insert();");
		out.println("					mainActivity.updateList();");
		out.println("				} else {");
		out.println("					handleUpdate(context, mainActivity);");
		out.println("				}");
		out.println("				dismiss();");
		out.println("			}");
		out.println("		});");
		out.println("");
		out.println("		btn2.setOnClickListener(new View.OnClickListener() {");
		out.println("			public void onClick(View v) {");
		out.println("				handleDelete(context, mainActivity);");
		out.println("				dismiss();");
		out.println("			}");
		out.println("		});");
		out.println("");
		out.println("		btn3.setOnClickListener(new View.OnClickListener() {");
		out.println("			public void onClick(View v) {");
		out.println("				dismiss();");
		out.println("			}");
		out.println("		});");
		out.println("	}");
		out.println("");
		out.println("	private void handleDelete(Context ctx, final MainActivity mainActivity) {");
		out.println("		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(ctx);");
		out.println("		helpBuilder.setTitle(\"Confirmation\");");
		out.println("		helpBuilder");
		out.println("				.setMessage(\"Etes-vous sûr de vouloir supprimer cet élément ?\");");
		out.println("		final Context tmpCtx = ctx;");
		out.println("		helpBuilder.setPositiveButton(\"Oui\",");
		out.println("				new DialogInterface.OnClickListener() {");
		out.println("					public void onClick(DialogInterface dialog, int which) {");
		out.println("						delete();");
		out.println("						mainActivity.updateList();");
		out.println("					}");
		out.println("				});");
		out.println("		helpBuilder.setNegativeButton(\"Non\",");
		out.println("				new DialogInterface.OnClickListener() {");
		out.println("					public void onClick(DialogInterface dialog, int which) {");
		out.println("					}");
		out.println("				});");
		out.println("		AlertDialog helpDialog = helpBuilder.create();");
		out.println("		helpDialog.show();");
		out.println("	}");
		out.println("");
		out.println("	private void handleUpdate(Context ctx, final MainActivity mainActivity) {");
		out.println("		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(ctx);");
		out.println("		helpBuilder.setTitle(\"Confirmation\");");
		out.println("		helpBuilder");
		out.println("				.setMessage(\"Etes-vous sûr de vouloir modifier cet élément ?\");");
		out.println("		final Context tmpCtx = ctx;");
		out.println("		helpBuilder.setPositiveButton(\"Oui\",");
		out.println("				new DialogInterface.OnClickListener() {");
		out.println("					public void onClick(DialogInterface dialog, int which) {");
		out.println("						update();");
		out.println("						mainActivity.updateList();");
		out.println("					}");
		out.println("				});");
		out.println("		helpBuilder.setNegativeButton(\"Non\",");
		out.println("				new DialogInterface.OnClickListener() {");
		out.println("					public void onClick(DialogInterface dialog, int which) {");
		out.println("					}");
		out.println("				});");
		out.println("		AlertDialog helpDialog = helpBuilder.create();");
		out.println("		helpDialog.show();");
		out.println("	}");
		out.println("");
		out.println("	private void handleMode() {");
		out.println("		switch (mode) {");
		out.println("		case Cnst.MODE_CRE:");
		out.println("			btn1.setVisibility(View.VISIBLE);");
		out.println("			btn1.setText(\"Créer\");");
		out.println("			btn2.setVisibility(View.GONE);");
		out.println("			btn3.setVisibility(View.VISIBLE);");
		out.println("			btn3.setText(\"Annuler\");");
		out.println("			break;");
		out.println("		case Cnst.MODE_MOD:");
		out.println("			btn1.setVisibility(View.VISIBLE);");
		out.println("			btn1.setText(\"Modifier\");");
		out.println("			btn2.setVisibility(View.VISIBLE);");
		out.println("			btn2.setText(\"Supprimer\");");
		out.println("			btn3.setVisibility(View.VISIBLE);");
		out.println("			btn3.setText(\"Annuler\");");
		out.println("			break;");
		out.println("		default:");
		out.println("			break;");
		out.println("		}");
		out.println("	}");
		out.println("");
		out.println("	public int getMode() {");
		out.println("		return mode;");
		out.println("	}");
		out.println("}");
		out.println("");
		out.close();
	}
	
	public static void initDirectories(){
		File f = new File("out");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/constants");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/enums");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/models");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/services");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/activities");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/activities/dialogs");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/src/activities/adapters");
		if(!f.exists()){
			f.mkdir();
		}
		
		f = new File("out/res");
		if(!f.exists()){
			f.mkdir();
		}
		
	}
	
	public static void genActivity(Model model, String namespace) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/src/activities/"+model.getName()
				+ "Activity.java"));
		out.println("package " + namespace + ";");
		out.println("");
		out.println("public class " + model.getName()
				+ "Activity extends Activity {");
		out.println("	private ListView itemList;");
		out.println("");
		out.println("	@Override");
		out.println("	protected void onCreate(Bundle savedInstanceState) {");
		out.println("		super.onCreate(savedInstanceState);");
		out.println("		setContentView(R.layout."+ model.getInstanceName()+"list_layout);");
		out.println("");
		out.println("		this.itemList = (ListView) findViewById(R.id."
				+ model.getInstanceName() + "_list);");

		out.println("		itemList.setOnItemClickListener(new OnItemClickListener() {");
		out.println("			@Override");
		out.println("			@SuppressWarnings(\"unchecked\")");
		out.println("			public void onItemClick(AdapterView<?> a, View v, int position, long id) {");
		out.println("				");
		out.println("			}");
		out.println("		});");
		out.println("");
		out.println("		itemList.setOnItemLongClickListener(new OnItemLongClickListener() {");
		out.println("			@SuppressWarnings(\"unchecked\")");
		out.println("			@Override");
		out.println("			public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {");
		out.println("				");
		out.println("				return true;");
		out.println("			}");
		out.println("		});");

		out.println("	}");
		out.println("}");
		out.close();
	}

	public static void genModelLayout(Model model) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/res/"+
				model.getInstanceName() + "_layout.xml"));
		out.println("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
		out.println("	android:layout_width=\"wrap_content\"");
		out.println("	android:layout_height=\"wrap_content\"");
		out.println("	android:orientation=\"vertical\" >");
		for (Property p : model.getProperties()) {
			out.println("	<TextView");
			out.println("		android:id=\"@+id/" + model.getInstanceName()
					+ "_" + p.getName() + "\"");
			out.println("		android:layout_width=\"wrap_content\"");
			out.println("		android:layout_height=\"wrap_content\"/>");
			out.println("");
		}

		out.println("</LinearLayout>");
		out.close();
	}
	
	public static void genModelListLayout(Model model) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/res/"+
				model.getInstanceName() + "_list_layout.xml"));
		out.println("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
		out.println("	android:layout_width=\"wrap_content\"");
		out.println("	android:layout_height=\"wrap_content\"");
		out.println("	android:orientation=\"vertical\" >");
		for (Property p : model.getProperties()) {
			out.println("	<TextView");
			out.println("		android:id=\"@+id/" + model.getInstanceName()
					+ "_list_" + p.getName() + "\"");
			out.println("		android:layout_width=\"wrap_content\"");
			out.println("		android:layout_height=\"wrap_content\"/>");
			out.println("");
		}

		out.println("</LinearLayout>");
		out.close();
	}

	public static void genDialogLayout(Model model) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/res/"+
				model.getInstanceName() + "_dial_layout.xml"));
		out.println("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
		out.println("	android:layout_width=\"wrap_content\"");
		out.println("	android:layout_height=\"wrap_content\"");
		out.println("	android:orientation=\"vertical\">");

		for (Property p : model.getProperties()) {
			out.println("	<LinearLayout");
			out.println("		android:layout_width=\"fill_parent\"");
			out.println("		android:layout_height=\"wrap_content\"");
			out.println("		android:orientation=\"horizontal\">");
			out.println("		<TextView");
			out.println("			android:layout_width=\"wrap_content\"");
			out.println("			android:layout_height=\"wrap_content\"");
			out.println("			android:text=\"" + p.getNameUpper() + "\"/>");
			out.println("		<EditText");
			out.println("			android:id=\"@+id/" + model.getInstanceName()
					+ "_dial_" + p.getName() + "\"");
			out.println("			android:layout_width=\"wrap_content\"");
			out.println("			android:layout_height=\"wrap_content\"/>");
			out.println("	</LinearLayout>");
		}

		out.println("		<LinearLayout");
		out.println("			android:layout_width=\"wrap_content\"");
		out.println("			android:layout_height=\"wrap_content\"");
		out.println("			android:orientation=\"horizontal\">");

		out.println("			<Button");
		out.println("				android:id=\"@+id/" + model.getInstanceName()
				+ "_dial_btn1\"");
		out.println("				android:layout_width=\"wrap_content\"");
		out.println("				android:layout_height=\"wrap_content\"/>");
		out.println("			<Button");
		out.println("				android:id=\"@+id/" + model.getInstanceName()
				+ "_dial_btn2\"");
		out.println("				android:layout_width=\"wrap_content\"");
		out.println("				android:layout_height=\"wrap_content\"/>");
		out.println("			<Button");
		out.println("				android:id=\"@+id/" + model.getInstanceName()
				+ "_dial_btn3\"");
		out.println("				android:layout_width=\"wrap_content\"");
		out.println("				android:layout_height=\"wrap_content\"/>");
		out.println("		</LinearLayout>");

		out.println("</LinearLayout>");
		out.close();
	}

	public static void genAdapter(Model model, String packageName)
			throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/src/activities/adapters/"+model.getName()
				+ "Adapter.java"));
		out.println("package " + packageName + ";");
		out.println("");
		out.println("import android.content.Context;");
		out.println("import android.view.LayoutInflater;");
		out.println("import android.view.View;");
		out.println("import android.view.ViewGroup;");
		out.println("import android.widget.ArrayAdapter;");
		out.println("");
		out.println("public class " + model.getName()
				+ "Adapter extends ArrayAdapter<" + model.getName() + "> {");
		out.println("	private Context context;");
		out.println("	private " + model.getName() + " data[] = null;");
		out.println("");
		out.println("	public " + model.getName()
				+ "Adapter(Context context, int layoutResourceId, "
				+ model.getName() + "[] data) {");
		out.println("		super(context, layoutResourceId, data);");
		out.println("		this.context = context;");
		out.println("		this.data = data;");
		out.println("	}");
		out.println("");
		out.println("	@Override");
		out.println("	public View getView(int position, View convertView, ViewGroup parent) {");
		out.println("		" + model.getName() + " " + model.getInstanceName()
				+ " = data[position];");
		out.println("		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);");
		out.println("		View rowView = inflater.inflate(R.layout." + model.getName().toLowerCase()+"_list_layout, parent, false);");
		out.println("		/*");
		for (Property p : model.getProperties()) {
			out.println("		TextView "
					+ p.getName()
					+ "TextView = (TextView) rowView.findViewById(R.id."+model.getName().toLowerCase()+"_list_"+p.getName()+");");
			out.println("		" + p.getName() + "TextView.setText("
					+ model.getInstanceName() + ".get" + p.getNameUpper()
					+ "());");
		}
		out.println("		*/");
		out.println("		return rowView;");
		out.println("	}");
		out.close();
	}

	public static void genDatabase(Database db, String packageName)
			throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/src/services/DBService.java"));

		// package
		out.println("package " + packageName + ";");
		out.println("");

		// imports
		out.println("import java.util.ArrayList;");
		out.println("import java.util.List;");
		out.println("");
		out.println("import android.content.ContentValues;");
		out.println("import android.content.Context;");
		out.println("import android.database.Cursor;");
		out.println("import android.database.sqlite.SQLiteDatabase;");
		out.println("import android.database.sqlite.SQLiteOpenHelper;");
		out.println("import android.database.sqlite.SQLiteStatement;");
		out.println("");

		// header class
		out.println("public class DBService extends {");

		// proprietes
		out.println("	private static final String DATABASE_NAME = \""
				+ db.getName() + ".db\";");
		out.println("	private static final int DATABASE_VERSION = 1;");
		for (Table t : db.getTables()) {
			out.println("	private static final String TABLE_NAME_"
					+ t.getName().toUpperCase() + " = \"" + t.getName() + "\";");
		}
		out.println("");
		out.println("	private Context context;");
		out.println("	private SQLiteDatabase database;");
		out.println("	private SQLiteStatement statement;");
		out.println("");

		// constructeur par defaut
		out.println("	public DBService(Context context) {");
		out.println("		this.context = context;");
		out.println("		OpenHelper openHelper = new OpenHelper(this.context);");
		out.println("		this.database = openHelper.getWritableDatabase();");
		out.println("	}");
		out.println("");

		// methode bouchon
		out.println("	public void bouchon() {");
		out.println("");
		out.println("	}");
		out.println("");

		// methodes principales
		for (Table t : db.getTables()) {
			genInsert(t, out);
			out.println("");
			genUpdate(t, out);
			out.println("");
			genDelete(t, out);
			out.println("");
			genNextId(t, out);
			out.println("");
			genSingle(t, out);
			out.println("");
			genList(t, out);
			out.println("");
		}

		// sous-classe
		out.println("	private static class OpenHelper extends SQLiteOpenHelper {");
		out.println("		OpenHelper(Context context) {");
		out.println("			super(context, DATABASE_NAME, null, DATABASE_VERSION);");
		out.println("		}");
		out.println("");
		out.println("		@Override");
		out.println("		public void onCreate(SQLiteDatabase database) {");
		for (Table t : db.getTables()) {
			String fields = t.getFieldsString();
			out.println("			database.execSQL(\"CREATE TABLE \" + TABLE_NAME_"
					+ t.getName().toUpperCase() + " + \"(" + fields + ")\");");
		}
		out.println("		}");
		out.println("");
		out.println("		@Override");
		out.println("		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {");
		for (Table t : db.getTables()) {
			String fields = t.getFieldsString();
			out.println("			database.execSQL(\"DROP TABLE IF EXISTS \" + TABLE_NAME_"
					+ t.getName().toUpperCase() + ");");
			out.println("			database.execSQL(\"CREATE TABLE \" + TABLE_NAME_"
					+ t.getName().toUpperCase() + " + \"(" + fields + ")\");");
		}
		out.println("			onCreate(database);");
		out.println("		}");
		out.println("	}");

		// fermeture classe
		out.println("}");
		out.close();
	}

	private static void genInsert(Table t, PrintWriter out) {
		out.println("	public void insert" + t.getNameUpper() + "("
				+ t.getNameUpper() + " " + t.getName() + ") {");
		String inter = "";
		String fi = "";
		for (Field f : t.getFields()) {
			if (!inter.isEmpty()) {
				inter += ", ";
				fi += ", ";
			}
			inter += "?";
			fi += f.getName();
		}
		out.println("		String sql = \"insert into \" + TABLE_NAME_"
				+ t.getName().toUpperCase() + " +\"(" + fi + ") values ("
				+ inter + ")\";");
		out.println("		statement = database.compileStatement(sql);");
		out.println("");
		int cpt = 1;
		for (Field f : t.getFields()) {
			String tmp = "";
			if (f.getType().equals("TEXT") || f.getType().equals("DATE")) {
				tmp = "String";
			} else if (f.getType().equals("INTEGER")) {
				tmp = "Long";
			} else if (f.getType().equals("DOUBLE")) {
				tmp = "Double";
			}
			out.println("		statement.bind" + tmp + "(" + cpt + ", "
					+ t.getName() + ".get" + f.getNameUpper() + "());");
			cpt++;
		}
		out.println("		statement.executeInsert();");
		out.println("	}");
	}

	private static void genUpdate(Table t, PrintWriter out) {
		out.println("	public void update" + t.getNameUpper() + "(int id, "
				+ t.getNameUpper() + " " + t.getName() + ") {");
		out.println("		ContentValues args = new ContentValues();");
		for (Field f : t.getFields()) {
			out.println("		args.put(\"" + f.getName() + "\", " + t.getName()
					+ ".get" + f.getNameUpper() + "());");
		}
		out.println("		database.update(TABLE_NAME_" + t.getName().toUpperCase()
				+ ", args, \"id=\" + id, null);");
		out.println("	}");
	}

	private static void genDelete(Table t, PrintWriter out) {
		out.println("	public void delete" + t.getNameUpper() + "(int id) {");
		out.println("		database.delete(TABLE_NAME_" + t.getName().toUpperCase()
				+ ",\"id=\" + id, null);");
		out.println("	}");
	}

	private static void genNextId(Table t, PrintWriter out) {
		out.println("	public int getNext" + t.getNameUpper() + "Id() {");
		out.println("		Cursor cursor = database.query(TABLE_NAME_"
				+ t.getName().toUpperCase()
				+ ", new String[] { \"max(id)\" }, null, null, null, null, null);");
		out.println("		if (cursor.moveToFirst()) {");
		out.println("			do {");
		out.println("				return cursor.getInt(0) + 1;");
		out.println("			} while (cursor.moveToNext());");
		out.println("		}");
		out.println("		if (cursor != null && !cursor.isClosed()) {");
		out.println("			cursor.close();");
		out.println("		}");
		out.println("		return 1;");
		out.println("	}");
	}

	private static void genSingle(Table t, PrintWriter out) {
		out.println("	public " + t.getNameUpper() + " get" + t.getNameUpper()
				+ "(int id) {");
		String tmp = "";
		for (Field f : t.getFields()) {
			if (!tmp.isEmpty()) {
				tmp += ", ";
			}
			tmp += "\"" + f.getName() + "\"";
		}
		out.println("		Cursor cursor = database.query(TABLE_NAME_"
				+ t.getName().toUpperCase() + ", new String[] { " + tmp
				+ "}, \"id=\" + id, null, null, null, null);");
		out.println("		if (cursor.moveToFirst()) {");
		out.println("			do {");
		out.println("				try{");
		out.println("					" + t.getNameUpper() + " " + t.getName() + " = new "
				+ t.getNameUpper() + "();");
		int cpt = 0;
		for (Field f : t.getFields()) {
			out.println("					" + t.getName() + ".set" + f.getNameUpper()
					+ "(cursor.getString(" + cpt + "));");
			cpt++;
		}
		out.println("					return " + t.getName() + ";");
		out.println("				}catch(Exception e){");
		out.println("					String s = e.getMessage();");
		out.println("				}");
		out.println("");
		out.println("			} while (cursor.moveToNext());");
		out.println("		}");
		out.println("		if (cursor != null && !cursor.isClosed()) {");
		out.println("			cursor.close();");
		out.println("		}");
		out.println("		return null;");
		out.println("	}");
	}

	private static void genList(Table t, PrintWriter out) {
		out.println("	// public " + t.getNameUpper() + "[] get"
				+ t.getNameUpper() + "s() {");
		out.println("	public List<" + t.getNameUpper() + "> get"
				+ t.getNameUpper() + "s() {");
		out.println("		List<" + t.getNameUpper() + "> res = new ArrayList<"
				+ t.getNameUpper() + ">();");
		String tmp = "";
		for (Field f : t.getFields()) {
			if (!tmp.isEmpty()) {
				tmp += ", ";
			}
			tmp += "\"" + f.getName() + "\"";
		}
		out.println("		Cursor cursor = database.query(TABLE_NAME_"
				+ t.getName().toUpperCase() + ", new String[] { " + tmp
				+ "}, null, null, null, null, null);");
		out.println("		if (cursor.moveToFirst()) {");
		out.println("			do {");
		out.println("				try{");
		out.println("					" + t.getNameUpper() + " " + t.getName() + " = new "
				+ t.getNameUpper() + "();");
		int cpt = 0;
		for (Field f : t.getFields()) {
			out.println("					" + t.getName() + ".set" + f.getNameUpper()
					+ "(cursor.getString(" + cpt + "));");
			cpt++;
		}
		out.println("					res.add(" + t.getName() + ";");
		out.println("				}catch(Exception e){");
		out.println("					String s = e.getMessage();");
		out.println("				}");
		out.println("");
		out.println("			} while (cursor.moveToNext());");
		out.println("		}");
		out.println("		if (cursor != null && !cursor.isClosed()) {");
		out.println("			cursor.close();");
		out.println("		}");
		out.println("		// return (" + t.getNameUpper() + "[]) res.toArray(new "
				+ t.getNameUpper() + "[res.size()]);");
		out.println("		return res;");
		out.println("	}");
	}

	public static void genModel(Model model, String packageName)
			throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/src/models/"+model.getName()
				+ ".java"));

		// package
		out.println("package " + packageName + ";");
		out.println("");

		// header class
		String ext = model.getExtendClass()==null ? ""
				: ("extends " + model.getExtendClass());
		out.println("public class " + model.getName() + " " + ext + " {");

		// proprietes
		String pptLine = "";
		for (Property p : model.getProperties()) {
			out.println("	private " + p.getType() + " " + p.getName() + ";");
			if (!pptLine.isEmpty()) {
				pptLine += ", ";
			}
			pptLine += p.getType() + " " + p.getName();
		}
		out.println("");

		// constructeur par defaut
		out.println("	public " + model.getName() + "() {");
		out.println("	}");
		out.println("");

		// constructeur avec proprietes
		out.println("	public " + model.getName() + "(" + pptLine + ") {");
		for (Property p : model.getProperties()) {
			out.println("		this." + p.getName() + " = " + p.getName() + ";");
		}
		out.println("	}");

		// getter et setter
		for (Property p : model.getProperties()) {
			out.println("");
			out.println("	public void set" + p.getNameUpper() + "("
					+ p.getType() + " " + p.getName() + ") {");
			out.println("		this." + p.getName() + " = " + p.getName() + ";");
			out.println("	}");
			out.println("");
			out.println("	public " + p.getType() + " get" + p.getNameUpper()
					+ "() {");
			out.println("		return " + p.getName() + ";");
			out.println("	}");
		}

		// fermeture classe
		out.println("}");
		out.close();
	}

	public static void genEnum(Enum e, String packageName) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("out/src/enums/"+e.getNameUpper()
				+ ".java"));

		// package
		out.println("package " + packageName + ";");
		out.println("");

		// header enum
		out.println("public enum " + e.getNameUpper() + " {");
		String tmp = "";
		for (String value : e.getValues()) {
			if (!value.isEmpty()) {
				tmp += ", ";
			}
			tmp += value.toUpperCase();
		}
		tmp += ";";
		out.println("	" + tmp);

		// fermeture classe
		out.println("}");
		out.close();
	}
}