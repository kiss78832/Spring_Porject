package com.application.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ApplicationVO implements java.io.Serializable{

	private String app_num;
	private String group_id;
	private String member_id;
	private String route_id;
	private String egc_contact;
	private String egc_phone;
	private String satellite;
	private String radio;
	private Integer app_status;
	private Timestamp app_time;
	private Integer app_days;
	private Date first_day;
	private Integer dailybed_provided;
	private Integer meal_provided;
	private String locations;
	
	
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	
	public String getApp_num() {
		return app_num;
	}
	public void setApp_num(String app_num) {
		this.app_num = app_num;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getEgc_contact() {
		return egc_contact;
	}
	public void setEgc_contact(String egc_contact) {
		this.egc_contact = egc_contact;
	}
	public String getEgc_phone() {
		return egc_phone;
	}
	public void setEgc_phone(String egc_phone) {
		this.egc_phone = egc_phone;
	}
	public String getSatellite() {
		return satellite;
	}
	public void setSatellite(String satellite) {
		this.satellite = satellite;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public Integer getApp_status() {
		return app_status;
	}
	public void setApp_status(Integer app_status) {
		this.app_status = app_status;
	}
	public Timestamp getApp_time() {
		return app_time;
	}
	public void setApp_time(Timestamp app_time) {
		this.app_time = app_time;
	}
	public Integer getApp_days() {
		return app_days;
	}
	public void setApp_days(Integer app_days) {
		this.app_days = app_days;
	}
	public Date getFirst_day() {
		return first_day;
	}
	public void setFirst_day(Date first_day) {
		this.first_day = first_day;
	}
	public Integer getDailybed_provided() {
		return dailybed_provided;
	}
	public void setDailybed_provided(Integer dailybed_provided) {
		this.dailybed_provided = dailybed_provided;
	}
	public Integer getMeal_provided() {
		return meal_provided;
	}
	public void setMeal_provided(Integer meal_provided) {
		this.meal_provided = meal_provided;
	}
	
	
	
	
	
	
	
	
}
