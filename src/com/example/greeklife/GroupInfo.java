package com.example.greeklife;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupInfo extends Activity {	
	TextView nm;
	TextView decription;
	TextView add;
	TextView conName;
	TextView conEm;
	ArrayList<String> values = new ArrayList<String>();
	HashSet<String> subscribed;
	boolean subscribedBool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_info);


		Bundle extras = getIntent().getExtras();
		ArrayList<String> vals = extras.getStringArrayList("values");
		values.clear();
		values.addAll(vals);
		subscribed = viewSubscribed();

		TextView nm = (TextView) findViewById(R.id.name);
		nm.setText(vals.get(1));


		TextView decription = (TextView) findViewById(R.id.description);
		decription.setText(vals.get(2));


		TextView add = (TextView) findViewById(R.id.address);
		add.setText(vals.get(3));


		TextView conName = (TextView) findViewById(R.id.contact_name);
		conName.setText(vals.get(4));


		TextView conEm = (TextView) findViewById(R.id.contact_email);
		conEm.setText(vals.get(5));
		
		Button subscribe = (Button) findViewById(R.id.subscribe);
		if(subscribed.contains(vals.get(1))) {
			subscribe.setText("un-subscribe");
			subscribe.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        unSubscribeFromEventFeed();
			    } 
			});
		}
		
		ImageView image = (ImageView) findViewById(R.id.fratImage);
		boolean test = true;
		if(test) {
			image.setVisibility(View.INVISIBLE);
		}
		
		
	}

	
	public void subscribeToEventFeed(View v) {
		Bundle extras = getIntent().getExtras();
		ArrayList<String> vals = extras.getStringArrayList("values");
		String frat = vals.get(1);
		SQLiteDatabase db;
		db = openOrCreateDatabase( "subscribed.db"        , SQLiteDatabase.CREATE_IF_NECESSARY        , null          );
		try {
			/* While testing, you may want to delete your table from the emulator/phone.  To do this simply uncomment this line of code 
			 * db.execSQL("DROP TABLE IF EXISTS tbl_Contain");
			 */
			
			
			final String CREATE_TABLE_CONTAIN = "CREATE TABLE IF NOT EXISTS tbl_Contain ("
					+ "ID INTEGER primary key AUTOINCREMENT,"
					+ "NAME TEXT);";
			db.execSQL(CREATE_TABLE_CONTAIN);
			String sql = "INSERT or replace INTO tbl_Contain (NAME) VALUES('"+frat+"')" ;       
			db.execSQL(sql);
		}
		catch (Exception e) {      
			e.printStackTrace();
		}
		db.close();
	}
	
	public void unSubscribeFromEventFeed() {
		Bundle extras = getIntent().getExtras();
		ArrayList<String> vals = extras.getStringArrayList("values");
		String frat = vals.get(1);
		SQLiteDatabase db;
		db = openOrCreateDatabase( "subscribed.db"        , SQLiteDatabase.CREATE_IF_NECESSARY        , null          );
		try {
			String table_name = "tbl_Contain";
			String where = "NAME = '"+frat+"'";
			String[] whereArgs = null;
			db.delete(table_name, where, whereArgs);
		}
		catch (Exception e) {      
			e.printStackTrace();
		}
		db.close();
		
		
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
