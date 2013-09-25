package com.example.greeklife.ui;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.greeklife.GroupInfo;
import com.example.greeklife.HttpConnectionFailedThrowable;
import com.example.greeklife.transactions.QueryDb;

public class FratView extends ListActivity{

	
	ArrayList<String> populatedList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/*The next two lines of code gets rid of the title and makes the app fullscreen. We want to do it right
		 * before we set the content view*/
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		super.onCreate(savedInstanceState);
		//This gets the list of frats or sorority or coed from MainActivity.java. 
		ArrayList<String> list = new ArrayList<String>(); 
		String type = this.getIntent().getStringExtra("type");
		if(type.equals("frat")) {
			list.addAll(this.getIntent().getStringArrayListExtra("fraternity_list"));
		} else if(type.equals("soro")) {
			list.addAll(this.getIntent().getStringArrayListExtra("sorority_list"));
		} else if(type.equals("coed")) {
			list.addAll(this.getIntent().getStringArrayListExtra("coed_list"));
		} else if(type.equals("search")) {
			list.addAll(this.getIntent().getStringArrayListExtra("search_list"));
		}
		
		populatedList = list;
		//setContentView(R.layout.frat_view);
		//This turns the array of the frat or sorority or coed list into a list in android
		setListAdapter(new ArrayAdapter<String>(FratView.this,android.R.layout.simple_list_item_1,list));
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		try {
		ArrayList<String> data = new ArrayList<String>();
		String name = populatedList.get(position);
		data = QueryDb.queryGreekGroups(data, populatedList.get(position));
		Bundle extras = new Bundle();
		extras.putStringArrayList("values", data);
		Intent intent = new Intent(FratView.this, GroupInfo.class);
		intent.putExtras(extras);
		startActivity(intent);
		} catch (HttpConnectionFailedThrowable e) {
			Toast.makeText(this, "You do not have internet connection!", 5000).show();
		}
	}
}
