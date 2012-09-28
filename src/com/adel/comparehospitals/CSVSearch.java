package com.adel.comparehospitals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import au.com.bytecode.opencsv.CSVReader;

public class CSVSearch {
	AssetManager assetManager;
	Context context;
	List<String> hospitalsList = new ArrayList<String>();
	
	public CSVSearch(Context context){
		this.context=context;
	}
	public List<String> search(String word){
		assetManager = context.getAssets();
		
		try {
			InputStream csvStream = assetManager.open("kennzahlen2010.csv");
			InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
			CSVReader csvReader = new CSVReader(csvStreamReader);
			String[] line;
			//find all hospitals matching the word
			while((line = csvReader.readNext()) !=null){
				if (line[0].equals(word)){
					hospitalsList.add(line[1]+", "+line[2]);
				}
			}
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hospitalsList;
	}
}
