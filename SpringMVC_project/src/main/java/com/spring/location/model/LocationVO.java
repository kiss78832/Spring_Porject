package com.spring.location.model;

public class LocationVO implements java.io.Serializable{

	private String location_id;
	private String location_name;
	private Integer bedTotal_num;
	private Integer bed_price;
	private byte[] location_pic;
	private Integer loc_type;
	private Integer location_status;
	
	public LocationVO() {
		super();
	}
	
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public Integer getBedTotal_num() {
		return bedTotal_num;
	}
	public void setBedTotal_num(Integer bedTotal_num) {
		this.bedTotal_num = bedTotal_num;
	}
	public Integer getBed_price() {
		return bed_price;
	}
	public void setBed_price(Integer bed_price) {
		this.bed_price = bed_price;
	}
	public byte[] getLocation_pic() {
		return location_pic;
	}
	public void setLocation_pic(byte[] location_pic) {
		this.location_pic = location_pic;
	}

	public Integer getLocation_status() {
		return location_status;
	}
	public void setLocation_status(Integer location_status) {
		this.location_status = location_status;
	}
	public Integer getLoc_type() {
		return loc_type;
	}
	public void setLoc_type(Integer loc_type) {
		this.loc_type = loc_type;
	}
	
	
}
