package com.example.greeklife.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

	String host;
	String name;
	String description;
	String address;
	String contact;


	public Event(String h, String n, String d, String a, String c) {
		this.host = h;
		this.name = n;
		this.description = d;
		this.address = a;
		this.contact = c;
	}


	public static ArrayList<String> listOfEventInfo(Event e) {
		ArrayList<String> info = new ArrayList<String>();

		info.add(e.host);
		info.add(e.name);
		info.add(e.description);
		info.add(e.address);
		info.add(e.contact);

		return info;
	}


	public static final Parcelable.Creator<Event> CREATOR
	= new Parcelable.Creator<Event>() {
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};
	
	public Event(Parcel in) {
		
		String[] vals = new String[5];
		in.readStringArray(vals);
		
		this.host = vals[0];
		this.name = vals[1];
		this.description = vals[2];
		this.address = vals[3];
		this.contact = vals[4];
		
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		String[] vals = {this.host, this.name, this.description, this.address, this.contact};
		dest.writeStringArray(vals);
		
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}




}
