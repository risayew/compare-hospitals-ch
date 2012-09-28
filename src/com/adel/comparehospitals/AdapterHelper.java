package com.adel.comparehospitals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

public class AdapterHelper {
	
	final String ATTR_GROUP_NAME= "groupName";
	final String ATTR_PHONE_NAME= "selectedItem";
	
	String[] groups = new String[]{"Spitalwahl nach Kanton", "Spitalwahl nach Betriebstyp"};
    
	String[] kantons = new String[] {"Aargau","Appenzell A.Rh.", "Appenzell I.Rh.",
										"Basel-Landschaft", "Basel-Stadt", "Bern",
										"Freiburg", "Genf", "Glarus", "Graubünden",
										"Jura", "Luzern", "Neunburg", "Nidwalden",
										"Obwalden", "Schaffhausen", "Schwyz", "Solothurn",
										"St. Gallen", "Tessin", "Thurgau", "Uri",
										"Waadt", "Wallis", "Zug", "Zürich" };
	String[] spitalTyp = new String[] { "Allgemeinspital, Zentrumsversorgung (Niveau 1, Universitätsspital)",
		    							  "Allgemeinspital, Zentrumsversorgung (Niveau 2)",
		    							  "Allgemeinspital, Grundversorgung (Niveau 3)",
		    							  "Allgemeinspital, Grundversorgung (Niveau 4)",
		    							  "Allgemeinspital, Grundversorgung (Niveau 5)",
		    							  "Spezialklinik: Chirurgie",
		    							  "Spezialklinik: Gynäkologie / Neonatologie",
		    							  "Spezialklinik: Pädiatrie" };
    
	ArrayList<Map<String, String>> groupData; // коллекция для групп
	ArrayList<Map<String, String>> childDataItem; // коллекция для элементов одной группы
	ArrayList<ArrayList<Map<String, String>>> childData; // общая коллекция для коллекций элементов
	// в итоге получится childData = ArrayList<childDataItem>
	
	Map<String, String> m; // список аттрибутов группы или элемента
	
	Context ctx;
	
	AdapterHelper(Context _ctx){
		ctx=_ctx;
	}
	
	SimpleExpandableListAdapter adapter;
	
	SimpleExpandableListAdapter getAdapter(){
	
	 groupData = new ArrayList<Map<String, String>>(); // заполняем коллекцию групп из массива с названиями групп
     for (String group : groups) { // für jede gruppe attributen einsetzen
     	m = new HashMap<String, String>();
         m.put("groupName", group); // name der gruppe
         groupData.add(m);  
     }
     
     String groupFrom[] = new String[] {ATTR_GROUP_NAME}; // список аттрибутов групп для чтения
     int groupTo[] = new int[] {android.R.id.text1}; // список ID view-элементов, в которые будет помещены аттрибуты групп
     childData = new ArrayList<ArrayList<Map<String, String>>>(); // создаем коллекцию для коллекций элементов
     childDataItem = new ArrayList<Map<String, String>>(); // создаем коллекцию элементов для первой группы
     
     for (String kanton : kantons) { // заполняем список аттрибутов для каждого элемента
     	m = new HashMap<String, String>();
         m.put("selectedItem", kanton); // the name of the canton
         childDataItem.add(m);  
     }
     childData.add(childDataItem); // добавляем в коллекцию коллекций

     childDataItem = new ArrayList<Map<String, String>>(); // создаем коллекцию элементов для второй группы
     for (String typ : spitalTyp) {
     	m = new HashMap<String, String>();
         m.put("selectedItem", typ);
         childDataItem.add(m);  
     }
     childData.add(childDataItem);

     
     String childFrom[] = new String[] {"selectedItem"}; // список аттрибутов элементов для чтения
     int childTo[] = new int[] {android.R.id.text1}; // список ID view-элементов, в которые будет помещены аттрибуты элементов
     
     adapter = new SimpleExpandableListAdapter(	ctx, groupData, 
     											android.R.layout.simple_expandable_list_item_1,
     											groupFrom,
     										    groupTo,
     											childData,
     											android.R.layout.simple_list_item_1,
     										    childFrom,
     											childTo);
     return adapter;
	}
	String getGroupText(int groupPos) {
	    return ((Map<String,String>)(adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
	  }
	  
	  String getChildText(int groupPos, int childPos) {
	    return ((Map<String,String>)(adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
	  }
	  
	  String getGroupChildText(int groupPos, int childPos) {
	    return getGroupText(groupPos) + " " +  getChildText(groupPos, childPos);
	  }
}
