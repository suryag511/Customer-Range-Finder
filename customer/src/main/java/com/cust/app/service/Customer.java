package com.cust.app.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer implements Comparable<Customer>{

	
	@JsonIgnore
	public String latitude;
	private Integer user_id;
	private String name;
	
	@JsonIgnore
	public String longitude;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int compareTo(Customer o) {
		return user_id-o.getUser_id();
	}

}
