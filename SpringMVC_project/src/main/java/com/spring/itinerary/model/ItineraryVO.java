package com.spring.itinerary.model;

public class ItineraryVO implements java.io.Serializable{
	private String itinerary_id;
	private String group_id;
	private Integer total_day;
	private String trip_schedule;
	private String trip_break;
	
	public String getTrip_schedule() {
		return trip_schedule;
	}
	public void setTrip_schedule(String trip_schedule) {
		this.trip_schedule = trip_schedule;
	}
	public String getTrip_break() {
		return trip_break;
	}
	public void setTrip_break(String trip_break) {
		this.trip_break = trip_break;
	}
	public String getItinerary_id() {
		return itinerary_id;
	}
	public void setItinerary_id(String itinerary_id) {
		this.itinerary_id = itinerary_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public Integer getTotal_day() {
		return total_day;
	}
	public void setTotal_day(Integer total_day) {
		this.total_day = total_day;
	}
	
	
	
}
