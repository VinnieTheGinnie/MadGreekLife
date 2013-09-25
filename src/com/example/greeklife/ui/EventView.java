package com.example.greeklife.ui;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.greeklife.EventInfo;
import com.example.greeklife.HttpConnectionFailedThrowable;
import com.example.greeklife.R;
import com.example.greeklife.models.Event;
import com.example.greeklife.transactions.QueryDb;



public class EventView extends ListActivity {

	
	ArrayList<Event> events;
	public static HashSet<String> subscribed;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = this.getCurrentFocus();
		
		subscribed = viewSubscribed();
		try {
			events = QueryDb.queryEvents(subscribed);
		} catch (HttpConnectionFailedThrowable e) {
			events = this.getIntent().getParcelableArrayListExtra("events_list");
		}
		
		ArrayList<String> eventNames = new ArrayList<String>();
		
		for(int i = 0; i < events.size(); i++) {
			eventNames.add(events.get(i).getName());
		}
		
		setListAdapter(new ArrayAdapter<String>(EventView.this,android.R.layout.simple_list_item_1, eventNames ));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.layout.activity_event_view, menu);
		return true;
	}
	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Event event = events.get(position);
		ArrayList<String> data = Event.listOfEventInfo(event);
		Bundle extras = new Bundle();
		extras.putStringArrayList("values", data);
		Intent intent = new Intent(EventView.this, EventInfo.class);
		intent.putExtras(extras);
		startActivity(intent);
	}
	

	public HashSet<String> viewSubscribed() {
		SQLiteDatabase db;
		HashSet<String> results = new HashSet<String>();
		db = openOrCreateDatabase( "subscribed.db"        , SQLiteDatabase.CREATE_IF_NECESSARY        , null          );
		try {
			String sql = "select NAME from tbl_Contain" ;       
			Cursor c = db.rawQuery(sql, null);


			if (c != null ) {
				if  (c.moveToFirst()) {
					do {
						String name = c.getString(c.getColumnIndex("NAME"));
						results.add(name);
					}while (c.moveToNext());
				} 
			}

		}
		catch (Exception e) { 
			db.close();
			return results;
		}
		
		db.close();
		return results;
		
	}
}
