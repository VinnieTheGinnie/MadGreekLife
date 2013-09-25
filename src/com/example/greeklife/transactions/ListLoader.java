package com.example.greeklife.transactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import com.example.greeklife.SplashScreen;

public class ListLoader extends AsyncTask<InputStream, Void, ArrayList<ArrayList<String>>> {

	SplashScreen splash;
	Activity activity;
	public ListLoader(SplashScreen s, Activity act) {
		this.splash = s;
		this.activity = act;
	}
	
	@Override
    protected void onPreExecute() { 
		splash.progress.setVisibility(View.VISIBLE);
    }
	
	
	@Override
	protected ArrayList<ArrayList<String>> doInBackground(InputStream... files) {
		
		int count = files.length;
		ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
		
		for(int i=0; i < count ; i++) {
			InputStreamReader inputStreamReader = new InputStreamReader(files[i]);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String line = "";
			/*list will contain all the names of the sororities that will
			 * be displayed when the sorority button is clicked*/
			ArrayList<String> list = new ArrayList<String>();
			/*There has to be a try/catch block because there might not
			 * be a file and it has to be handled*/
			try {
				/*this says while it's not the end of file yet and assigns each
				 * line to the variable 'line'*/
				while((line = bufferedReader.readLine()) != null){
					/*adds each line to the arraylist that
					 * will be used to display all the sororities*/
					list.add(line.toString());
				}
				files[i].reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			listOfLists.add(list);	
		}
		
		
		/* Now query for the events */ 

		
	
		
		return listOfLists;
	}
	
	protected void onPostExecute(ArrayList<ArrayList<String>> res) {
		super.onPostExecute(res);
	
	}

	

}


