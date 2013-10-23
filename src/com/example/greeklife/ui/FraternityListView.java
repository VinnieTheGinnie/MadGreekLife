package com.example.greeklife.ui;

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
		generateTestFraternities();
		
		
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
