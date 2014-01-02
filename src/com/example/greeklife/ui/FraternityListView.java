package com.example.greeklife.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.Views;

import com.example.greeklife.R;
import com.example.greeklife.adapters.FraternityAdapter;
import com.example.greeklife.models.GreekGroup;

public class FraternityListView extends Fragment implements OnScrollListener{
	
	
	@InjectView(R.id.fraternity_list) ListView groupList;
	View view;
	FraternityAdapter mFratAdapter;
	List<GreekGroup> mFraternityList;
	
	public FraternityListView() {
		//Stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.frg_frat_list, container, false);		
		Views.inject(this, view);
		
		
		mFraternityList = new ArrayList<GreekGroup>();
		
		//TEST LINE, MUST REMOVE BEFORE PRODUCTION 
		generateFraternities();
		
		
		// Have to add footer view before setting the adapter, so add an empty frame layout then remove it
		mFratAdapter = new FraternityAdapter(getActivity(), 0, mFraternityList );
		groupList.setAdapter(mFratAdapter);
		groupList.setOnScrollListener(this);
		
		groupList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
				// TODO: Create a method onItemClick in ActionBar activity
			}

		});


		return view;
	}
	
	//Test Method, Do not use in production app
	private void generateTestFraternities() {
		
		for(int i=0; i < 100 ; i++) {
			String ii = Integer.toString(i);
			GreekGroup curr = new GreekGroup(ii, "Fraternity " + ii, 
					"Test Description for this fraternity" , "1234 Address" , "Joe Schmo" , "joe@schmo.com");
			mFraternityList.add(curr);
		}
		
	}
	
	private void generateFraternities() {
		
		final InputStream frat_file = this.getResources().openRawResource(R.raw.fraternity);
		
			InputStreamReader inputStreamReader = new InputStreamReader(frat_file);
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
				int count = 0;
				while((line = bufferedReader.readLine()) != null){
					/*adds each line to the arraylist that
					 * will be used to display all the sororities*/
					list.add(line.toString());
					GreekGroup curr = new GreekGroup(Integer.toString(count), list.get(count), 
							"Test Description for this fraternity" , "1234 Address" , "Joe Schmo" , "joe@schmo.com");
					mFraternityList.add(curr);
					
				}
				frat_file.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}






}
