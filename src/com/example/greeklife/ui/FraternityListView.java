package com.example.greeklife.ui;

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

import com.example.greeklife.Globals;
import com.example.greeklife.R;

public class FraternityListView extends Fragment implements OnScrollListener{
	@InjectView(R.id.fraternity_list) ListView groupList;
	View view;


	public FraternityListView() {
		//Stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		view = inflater.inflate(R.layout.frg_frat_list, container, false);

		groupList = (ListView) view.findViewById(R.id.fraternity_list);


		// Have to add footer view before setting the adapter, so add an empty frame layout then remove it
		FrameLayout blankLayout = new FrameLayout(getActivity());
		groupList.addFooterView(blankLayout);

		groupList.setAdapter(Globals.mFratAdapter);
		groupList.setOnScrollListener(this);
		groupList.removeFooterView(blankLayout);

		groupList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
				// TODO: Create a method onItemClick in ActionBar activity
			}

		});


		return view;
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
