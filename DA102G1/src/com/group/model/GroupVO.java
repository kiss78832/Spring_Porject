package com.group.model;

import java.sql.Date;
import java.sql.Timestamp;

public class GroupVO implements java.io.Serializable {
	private String group_id;
	private String gp_leader;
	private String gp_name;
	private Integer gp_status;
	private Integer gp_nbp;
	private Timestamp app_time;
	private String satellite;
	private String radio;
	private Integer group_report;
	private Integer dailybed_provided;
	private Integer meal_provided;
	private Date first_day;
	private String target_loca;
	private String locations;
	private String route_id;
	
	
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	public String getTarget_loca() {
		return target_loca;
	}
	public void setTarget_loca(String target_loca) {
		this.target_loca = target_loca;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGp_leader() {
		return gp_leader;
	}
	public void setGp_leader(String gp_leader) {
		this.gp_leader = gp_leader;
	}
	public String getGp_name() {
		return gp_name;
	}
	public void setGp_name(String gp_name) {
		this.gp_name = gp_name;
	}
	public Integer getGp_status() {
		return gp_status;
	}
	public void setGp_status(Integer gp_status) {
		this.gp_status = gp_status;
	}
	public Integer getGp_nbp() {
		return gp_nbp;
	}
	public void setGp_nbp(Integer gp_nbp) {
		this.gp_nbp = gp_nbp;
	}
	
	public Timestamp getApp_time() {
		return app_time;
	}
	public void setApp_time(Timestamp app_time) {
		this.app_time = app_time;
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
	public Integer getGroup_report() {
		return group_report;
	}
	public void setGroup_report(Integer group_report) {
		this.group_report = group_report;
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
	
	public Date getFirst_day() {
		return first_day;
	}
	public void setFirst_day(Date first_day) {
		this.first_day = first_day;
	}

}
