package com.adel.comparehospitals;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class MainActivity extends Activity {
	
	final String LOG_TAG = "myLogs";
	ExpandableListView elvMain1;
	ExpandableListView elvMain2;
	AdapterHelper ah;
	SimpleExpandableListAdapter adapter;
	TextView tvInfo1;
	TextView tvInfo2;
	CSVSearch csvsearch;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvInfo1 = (TextView) findViewById(R.id.tvInfo1);
       
        
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();
        
        elvMain1 = (ExpandableListView) findViewById(R.id.elvMain1);
        elvMain1.setAdapter(adapter);
        elvMain2 = (ExpandableListView) findViewById(R.id.elvMain2);
        elvMain2.setAdapter(adapter);
        
        elvMain1.setOnChildClickListener(new OnChildClickListener() {
        	public boolean onChildClick(ExpandableListView parent, 
        								  View v,
        			                      int groupPosition,   
        			                      int childPosition, 
        			                      long id) {
        		Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition + 
        			  " childPosition = " + childPosition + 
                      " id = " + id);
        	
        		//tvInfo1.setText(ah.getGroupChildText(groupPosition, childPosition));
        		Intent intent = new Intent(MainActivity.this, SelectHospitalActivity.class);
        		Bundle b = new Bundle();
        		b.putString("key", ah.getChildText(groupPosition, childPosition));
        		intent.putExtras(b); //Put your id to your next Intent
        		startActivityForResult(intent,1); 
        		//finish();
        		return false;
        	}
          
        });
              
              // нажатие на группу
//        elvMain1.setOnGroupClickListener(new OnGroupClickListener() {
//        	public boolean onGroupClick(ExpandableListView parent, View v,
//        			                      int groupPosition, long id) {
//                Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition + 
//                      " id = " + id);
//              // блокируем дальнейшую обработку события для группы с позицией 1
//              //if (groupPosition == 1) return true;
//              
//             return false;
//            }
//          });
              
              // сворачивание группы        
//        elvMain1.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//            public void onGroupCollapse(int groupPosition) {
//              Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + groupPosition);
//              tvInfo1.setText("Свернули " + ah.getGroupText(groupPosition));
//            }
//          });
              
              // разворачивание группы
//        elvMain1.setOnGroupExpandListener(new OnGroupExpandListener() {
//            public void onGroupExpand(int groupPosition) {
//              Log.d(LOG_TAG, "onGroupExpand groupPosition = " + groupPosition);
//              tvInfo1.setText("Равзвернули " + ah.getGroupText(groupPosition));
//            }
//          });
        
        tvInfo2 = (TextView) findViewById(R.id.tvInfo2); 
              
        elvMain2.setOnChildClickListener(new OnChildClickListener() {
        	public boolean onChildClick(ExpandableListView parent, View v,
                      					  int groupPosition,   
                      					  int childPosition, 
                      					  long id) {
               
        		Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition + 
                            " childPosition = " + childPosition + 
                            " id = " + id);
        		//tvInfo2.setText(ah.getGroupChildText(groupPosition, childPosition));
        		Intent intent = new Intent(MainActivity.this, SelectHospitalActivity.class);
        		Bundle b = new Bundle();
        		b.putString("key", ah.getChildText(groupPosition, childPosition));
        		intent.putExtras(b); //Put your id to your next Intent
        		startActivityForResult(intent,2);                   
        		return false;
                  }
           });
        
             //elvMain2.setOnChildClickListener(onChildClickListener)
               // нажатие на группу
//        elvMain2.setOnGroupClickListener(new OnGroupClickListener() {
//             public boolean onGroupClick(ExpandableListView parent, View v,
//                      					   int groupPosition, long id) {
//             Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition + 
//                            " id = " + id);
//                    // блокируем дальнейшую обработку события для группы с позицией 1
//                    //if (groupPosition == 1) return true;
//                    
//                    return false;
//                  }
//           });
                    
                    // сворачивание группы        
//         elvMain2.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//              public void onGroupCollapse(int groupPosition) {
//              Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + groupPosition);
//                 tvInfo2.setText("Свернули " + ah.getGroupText(groupPosition));
//                  }
//           });
                    
                    // разворачивание группы
//         elvMain2.setOnGroupExpandListener(new OnGroupExpandListener() {
//              public void onGroupExpand(int groupPosition) {
//              Log.d(LOG_TAG, "onGroupExpand groupPosition = " + groupPosition);
//              tvInfo2.setText("Равзвернули " + ah.getGroupText(groupPosition));
//                  }
//            });
              
              // разворачиваем группу с позицией 2
              //elvMain1.expandGroup(1);     
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	if(data == null){ return;}
    	String hospital = data.getStringExtra("hospital");
    	if(requestCode==1){tvInfo1.setText(hospital);}
    	else {tvInfo2.setText(hospital);}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
