package com.example.greeklife;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greeklife.transactions.QueryDb;
import com.example.greeklife.transactions.Search;
import com.example.greeklife.ui.FratView;

/**
 * This class defines the look and functionality of the page after
 * the splash screen. 
 * NOTE: All comments describes what's immediately below except stated otherwise
 */
public class MainActivity extends Activity {
	Button frats,go,sororities,coed,events;
	public static HashMap<String, Integer> fratToIdMap = new HashMap<String, Integer>();
	public final String COED = "coed";
	public final String FRAT = "frat";
	public final String SORO = "soro";
	public final String SEARCH = "search";
	Context context = this;

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*The next three variables are used to get the files that store the list of frats,
		 * sororities, etc. The files are stored in the directory res/raw */
		final InputStream frat_file = this.getResources().openRawResource(R.raw.fraternity);
		final InputStream soro_file = this.getResources().openRawResource(R.raw.sorority);
		final InputStream coed_file = this.getResources().openRawResource(R.raw.coed);
		/*The next two lines of code gets rid of the title and makes the app fullscreen. We want to do it right
		 * before we set the content view*/
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		*/
		
		setContentView(R.layout.activity_main);
		
		/*gets the button from the xml file for this class*/
		go = (Button) findViewById(R.id.bGo);
		/*this code block bekiw handles the click for the sorority buttin*/
		go.setOnClickListener(new View.OnClickListener() {
			/*This reads from an input stream*/
			InputStreamReader inputStreamReader = new InputStreamReader(soro_file);
			/*Puts the input stream into a buffer*/
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			@Override
			public void onClick(View v) {
				/*line will hold each line that's been read*/
				String line = "";
				/*list will contain all the names of the sororities that will
				 * be displayed when the sorority button is clicked*/
				HashMap<String, String> tags = new HashMap<String, String>();
				try {
				tags = QueryDb.queryAllGroups(tags);
				
				EditText text = (EditText) findViewById(R.id.etSearch);
				String search = text.getText().toString();
				ArrayList<String> list = Search.match(tags, search);
				/*There has to be a try/catch block because there might not
				 * be a file and it has to be handled*/
				
				
				
				/*This intent transitions this class to the sorority class*/
				Intent send = new Intent(MainActivity.this,FratView.class);
				/*this sends the arraylist that has the list of soro-
				 *rities to the sorority class */
				send.putExtra("search_list", list);
				send.putExtra("type", SEARCH);
				startActivity(send);
				} catch (HttpConnectionFailedThrowable e) {
					Toast.makeText(context, "No internet connection, could not perform search!", 5000).show();
				}
			}
		});
		
		
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
