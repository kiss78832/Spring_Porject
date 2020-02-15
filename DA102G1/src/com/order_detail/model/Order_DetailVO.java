package com.order_detail.model;

import java.sql.Date;

public class Order_DetailVO implements java.io.Serializable {

	private String detail_id;
	private String order_id;
	private String location_id;
	private Integer bed_num;
	private Integer bedTotal_price;
	private Date checkin_date;
	private String meal_id;
	private Integer meal_quantity;
	private Integer mealTotal_price;
	
	public String getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public Integer getBed_num() {
		return bed_num;
	}
	public void setBed_num(Integer bed_num) {
		this.bed_num = bed_num;
	}
	public Integer getBedTotal_price() {
		return bedTotal_price;
	}
	public void setBedTotal_price(Integer bedTotal_price) {
		this.bedTotal_price = bedTotal_price;
	}
	public Date getCheckin_date() {
		return checkin_date;
	}
	public void setCheckin_date(Date checkin_date) {
		this.checkin_date = checkin_date;
	}
	public String getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(String meal_id) {
		this.meal_id = meal_id;
	}
	public Integer getMeal_quantity() {
		return meal_quantity;
	}
	public void setMeal_quantity(Integer meal_quantity) {
		this.meal_quantity = meal_quantity;
	}

	public Integer getMealTotal_price() {
		return mealTotal_price;
	}
	public void setMealTotal_price(Integer mealTotal_price) {
		this.mealTotal_price = mealTotal_price;
	}
	
	
	
}
