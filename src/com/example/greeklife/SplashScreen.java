package com.example.greeklife;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.greeklife.models.Event;
import com.example.greeklife.transactions.EventListLoader;
import com.example.greeklife.transactions.ListLoader;


public class SplashScreen extends Activity {

	public ProgressBar progress;
	public static HashSet<String> subscribed;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final InputStream frat_file = this.getResources().openRawResource(R.raw.fraternity);
		final InputStream soro_file = this.getResources().openRawResource(R.raw.sorority);
		final InputStream coed_file = this.getResources().openRawResource(R.raw.coed);
		Activity action = new ActionBar();
		final ListLoader listLoad = new ListLoader(this, action);   
		final EventListLoader eListLoad = new EventListLoader(this, action);   
		
		setContentView(R.layout.splash);
		progress=(ProgressBar)findViewById(R.id.progressBar1);
		Thread timer = new Thread(){
			
			public void run() {
				try {
					sleep(5000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					
					HashSet<String> subscribed = viewSubscribed();
			        listLoad.execute(frat_file, soro_file, coed_file);
			        eListLoad.execute(subscribed);
			        ArrayList<ArrayList<String>> listOfLists = null;
			        ArrayList<Event> events = null;
			        
			        try {
						listOfLists = listLoad.get();
						events = eListLoad.get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
			    	Intent intent = new Intent(SplashScreen.this, ActionBar.class);
					intent.putStringArrayListExtra("frat_list", listOfLists.get(0));
					intent.putStringArrayListExtra("soro_list", listOfLists.get(1));
					intent.putStringArrayListExtra("coed_list", listOfLists.get(2));
					intent.putParcelableArrayListExtra("events_list", events);
			
					startActivity(intent);
				}
			}
		};
		timer.start();
		
		
		
	/** I commented out the usual way to run a splash screen, the 
	 * above method creates an async task, and captures the 
	 * information we need for the 3 lists, fraternity, sorority, 
	 * and co-ed 
	 */
        
		/*The next two lines of code gets rid of the title and makes the app fullscreen. We want to do it right
		 * before we set the content view*/
/*		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
 */

	}
	/**
	 * This overriden method ensures that the splash doesn't come back
	 * if the user presses the back button on their phone.
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();/*pretty much kills this activity as soon as the 5 seconds is over*/
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
