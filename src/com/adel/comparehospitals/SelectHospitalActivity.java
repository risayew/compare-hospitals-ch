package com.adel.comparehospitals;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectHospitalActivity extends Activity {
	
	final String LOG_TAG = "myLogs";
	ListView lvMain;
	TextView tvText;
	List<String> hospitalsList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_hospital);
		
		Bundle b = getIntent().getExtras();
		String value = b.getString("key");
		
		CSVSearch csvSearch = new CSVSearch(this);
		hospitalsList = csvSearch.search(value);

		
		lvMain = (ListView) findViewById(R.id.listView1);
	    tvText = (TextView) findViewById(R.id.textView1);

//	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//	        this, R.array.names, android.R.layout.simple_list_item_1);
//	    lvMain.setAdapter(adapter);
	    
	    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, 
	    		                              android.R.layout.simple_list_item_1,
	    		                               hospitalsList);
	    lvMain.setAdapter(adapter);
	    		

	    lvMain.setOnItemClickListener(new OnItemClickListener() {
	      public void onItemClick(AdapterView<?> parent, View view,
	          int position, long id) {
	        Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "
	            + id);
	        //tvText.setText(hospitalsList.get(position));
	        Intent intent = new Intent();
	        intent.putExtra("hospital", hospitalsList.get(position));
	        setResult(RESULT_OK, intent);
	        finish();
	      }
	    });

//	    lvMain.setOnItemSelectedListener(new OnItemSelectedListener() {
//	      public void onItemSelected(AdapterView<?> parent, View view,
//	          int position, long id) {
//	        Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = "
//	            + id);
//	      }
//
//	      public void onNothingSelected(AdapterView<?> parent) {
//	        Log.d(LOG_TAG, "itemSelect: nothing");
//	      }
//	    });

//	    lvMain.setOnScrollListener(new OnScrollListener() {
//	      public void onScrollStateChanged(AbsListView view, int scrollState) {
//	        Log.d(LOG_TAG, "scrollState = " + scrollState);
//	      }
//
//	      public void onScroll(AbsListView view, int firstVisibleItem,
//	          int visibleItemCount, int totalItemCount) {
//	        Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
//	            + ", visibleItemCount" + visibleItemCount
//	            + ", totalItemCount" + totalItemCount);
//	      }
//	    });

	  
	}

}
