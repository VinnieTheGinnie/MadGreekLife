package com.example.greeklife;


import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import com.example.greeklife.adapters.FraternityAdapter;
import com.example.greeklife.adapters.MenuListAdapter;


public class Globals {
	
	public static String[] mDrawerOptions = {Constants.FRAT_DRAWER_OPTION , Constants.SORORITY_DRAWER_OPTION,
														Constants.COED_DRAWER_OPTION, Constants.SETTINGS_DRAWER_OPTION};
	public static ListView mDrawerList;
	public static DrawerLayout mDrawerLayout;
	static MenuListAdapter mMenuAdapter;
	
	public static FraternityAdapter mFratAdapter;
	
}
