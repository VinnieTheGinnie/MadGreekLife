package com.example.greeklife.transactions;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.greeklife.HttpConnectionFailedThrowable;
import com.example.greeklife.SplashScreen;
import com.example.greeklife.models.Event;

public class EventListLoader extends AsyncTask <HashSet<String>, Void, ArrayList<Event>>{

	SplashScreen splash;
	Activity activity;
	public EventListLoader(SplashScreen s, Activity act) {
		this.splash = s;
		this.activity = act;
	}
	

    protected void onPreExecute() { 
    }
	
	

	protected ArrayList<Event> doInBackground(HashSet<String>... set) {
		
		/* query for the events */ 
		
		
		HashSet<String> subscribed = set[0];
		ArrayList<Event> events = new ArrayList<Event>();
		
		
		if(subscribed.isEmpty()) {
			Event failedEvent = new Event("No Host", "Internet Connection Failed, Unable to get list of Events",
					"Internet Connection Failed, Unable to get list of Events", "Please Try Again",
								"When you have Connection to the internet" );
			events.add(failedEvent);
			
			return events;
		}
		
		try {
			events = QueryDb.queryEvents(subscribed);
		} catch (HttpConnectionFailedThrowable e) {
			Event failedEvent = new Event("No Host", "Internet Connection Failed, Unable to get list of Events",
							"Internet Connection Failed, Unable to get list of Events", "Please Try Again",
										"When you have Connection to the internet" );
			events.add(failedEvent);
		}
		
		return events;
	}
	
	protected void onPostExecute(ArrayList<Event> res) {
		super.onPostExecute(res);
	
	}

	
}
