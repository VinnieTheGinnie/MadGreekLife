package com.example.greeklife.models;

public class GreekGroup {

	public String id;
	public String name;
	public String description;
	public String address;
	public String contactName;
	public String contactEmail;
	
	
	public GreekGroup(String id, String name, String descript, String add, String contactName, String contactEmail) {
		this.id = id;
		this.name = name;
		this.description = descript;
		this.address = add;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		
	}	
}
