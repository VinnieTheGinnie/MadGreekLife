package com.example.greeklife;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.greeklife.adapters.MenuListAdapter;
import com.example.greeklife.ui.FraternityListView;


public class ActionBar extends ActionBarActivity {

	
	private final static String TAG = "ActionBar";
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	String[] subtitle;
	int[] icon;
	private ActionBarDrawerToggle mDrawerToggle;
	public FraternityListView fratListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout_main);

		mTitle = mDrawerTitle = Constants.mMainTitle;
		getSupportActionBar().setTitle(mTitle);
		
		
		Globals.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		Globals.mDrawerList = (ListView) findViewById(R.id.left_drawer);
		Globals.mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// set a custom shadow that overlays the main content when the drawer opens
		Globals.mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener


		subtitle = new String[] { "Subtitle Fragment 2", 
				"Subtitle Fragment 3" , "Subtitle Fragment 3", "Subtitle Fragment 1" };
		icon = new int[] {0, 0, 0, 0};

		Globals.mMenuAdapter = new MenuListAdapter(this, Globals.mDrawerOptions, subtitle, icon);

		//  Globals.mDrawerList.setAdapter(new ArrayAdapter<String>(this,
		// 	R.layout.drawer_list_item, Globals.mDrawerOptions));

		Globals.mDrawerList.setAdapter(Globals.mMenuAdapter);

		Globals.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		Log.d(TAG, "DEBUG");

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				Globals.mDrawerLayout,         /* DrawerLayout object */
				R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
				R.string.drawer_open,  /* "open drawer" description for accessibility */
				R.string.drawer_close  /* "close drawer" description for accessibility */
				) {
			public void onDrawerClosed(View view) {
//				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
//				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};



		Globals.mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {

			fratListView = new FraternityListView();
			getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fratListView).commit();
			Globals.mDrawerList.setItemChecked(Constants.FRAT_DRAWER_POSITION, true);
		}

	}


	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {


		// update the main content by replacing fragments
		if(position == Constants.FRAT_DRAWER_POSITION) {


		} else if(position == Constants.SORO_DRAWER_POSITION) {




		} else if(position == Constants.COED_DRAWER_POSITION) {



		} else {
			//do nothing
			Globals.mDrawerList.setItemChecked(position, false);
		}

	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (Globals.mDrawerLayout.isDrawerOpen(Globals.mDrawerList)) {
				Globals.mDrawerLayout.closeDrawer(Globals.mDrawerList);
			} else {
				Globals.mDrawerLayout.openDrawer(Globals.mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}




	//	@Override
	//	public void onCreate(Bundle savedInstanceState) {
	//		super.onCreate(savedInstanceState);
	//		setContentView(R.layout.activity_action_bar);
	//
	//	
	//
	//		// Tab for Photos
	//		TabSpec fratTab = tabHost.newTabSpec("Frats");
	//		// setting Title and Icon for the Tab
	//		fratTab.setIndicator("Fraternity");
	//		Intent frats = new Intent(this, FratView.class);
	//		frats.putStringArrayListExtra("fraternity_list", this.getIntent().getStringArrayListExtra("frat_list"));
	//		frats.putExtra("type", FRAT);
	//		fratTab.setContent(frats);
	//
	//		// Tab for Songs
	//		TabSpec soroTab = tabHost.newTabSpec("Soro");
	//		soroTab.setIndicator("Sororities");
	//		Intent soros = new Intent(this, FratView.class);
	//		soros.putExtra("sorority_list", this.getIntent().getStringArrayListExtra("soro_list"));
	//		soros.putExtra("type", SORO);
	//		soroTab.setContent(soros);
	//
	//		// Tab for Videos
	//		TabSpec coedTab = tabHost.newTabSpec("Coed");
	//		coedTab.setIndicator("CoEd");
	//		Intent coEd = new Intent(this, FratView.class);
	//		coEd.putStringArrayListExtra("coed_list", this.getIntent().getStringArrayListExtra("coed_list"));
	//		coEd.putExtra("type", COED);
	//		coedTab.setContent(coEd);
	//		
	//		TabSpec eventTab = tabHost.newTabSpec("Events");
	//		// setting Title and Icon for the Tab
	//		eventTab.setIndicator("Events");
	//		Intent event = new Intent(this, EventView.class);
	//		event.putParcelableArrayListExtra("events_list", this.getIntent().getParcelableArrayListExtra("events_list"));
	//		eventTab.setContent(event);
	//		
	//		TabSpec searchTab = tabHost.newTabSpec("search");
	//		// setting Title and Icon for the Tab
	//		searchTab.setIndicator("Search");
	//		Intent search = new Intent(this, MainActivity.class);
	//		searchTab.setContent(search);
	//
	//		// Adding all TabSpec to TabHost
	//		tabHost.addTab(fratTab); // Adding fraternity tab
	//		tabHost.addTab(soroTab); // Adding sorority tab
	//		tabHost.addTab(coedTab); // Adding coed tab
	//		tabHost.addTab(eventTab); //adding event tab
	//		tabHost.addTab(searchTab);
	//	}

}
