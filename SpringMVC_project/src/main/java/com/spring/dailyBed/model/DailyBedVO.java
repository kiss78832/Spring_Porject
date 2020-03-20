package com.spring.dailyBed.model;

import java.sql.Date;

public class DailyBedVO implements java.io.Serializable{

	private String dailyBed_id;
	private String location_id;
	private Integer remaining_total;
	private Integer provided_total;
	private Date dailyBed_date;
	
	public String getDailyBed_id() {
		return dailyBed_id;
	}
	
	public void setDailyBed_id(String dailyBed_id) {
		this.dailyBed_id = dailyBed_id;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public Integer getRemaining_total() {
		return remaining_total;
	}

	public void setRemaining_total(Integer remaining_total) {
		this.remaining_total = remaining_total;
	}

	public Integer getProvided_total() {
		return provided_total;
	}

	public void setProvided_total(Integer provided_total) {
		this.provided_total = provided_total;
	}

	public Date getDailyBed_date() {
		return dailyBed_date;
	}

	public void setDailyBed_date(Date dailyBed_date) {
		this.dailyBed_date = dailyBed_date;
	}
	
	

	
	
}
