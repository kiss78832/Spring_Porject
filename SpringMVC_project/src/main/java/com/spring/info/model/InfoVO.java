package com.spring.info.model;

import java.sql.Timestamp;

public class InfoVO implements java.io.Serializable{
	private String route_ID;
	private String route_Name;
	private Timestamp open_Time;
	private Integer open_Status;
	
	
	public String getRoute_ID() {
		return route_ID;
	}
	public void setRoute_ID(String route_ID) {
		this.route_ID = route_ID;
	}
	public String getRoute_Name() {
		return route_Name;
	}
	public void setRoute_Name(String route_Name) {
		this.route_Name = route_Name;
	}
	public Timestamp getOpen_Time() {
		return open_Time;
	}
	public void setOpen_Time(Timestamp open_Time) {
		this.open_Time = open_Time;
	}
	public Integer getOpen_Status() {
		return open_Status;
	}
	public void setOpen_Status(Integer open_Status) {
		this.open_Status = open_Status;
	}


}
