package com.example.greeklife.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;

import com.example.greeklife.R;
import com.example.greeklife.models.GreekGroup;

public class FraternityAdapter extends ArrayAdapter<GreekGroup>{
	@InjectView(R.id.fraternity_name) TextView fratName;
	@InjectView(R.id.fraternity_description) TextView fratDescription;


	private ArrayList<GreekGroup> mGreekGroupList = new ArrayList<GreekGroup>();
	private LayoutInflater mInflater;

	public FraternityAdapter(Context context, int resource,
			int textViewResourceId, List<GreekGroup> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		return (mGreekGroupList.size() > 0) ? mGreekGroupList.size() : 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.fraternity_list_item, null);
		}
		Views.inject(this, convertView);
		
		fratName.setText(mGreekGroupList.get(position).name);
		fratDescription.setText(mGreekGroupList.get(position).description);
		

		return convertView;

	}


}
