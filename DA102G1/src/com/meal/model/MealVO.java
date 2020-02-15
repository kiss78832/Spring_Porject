package com.meal.model;

public class MealVO implements java.io.Serializable{
	private String meal_id;
	private String meal_name;
	private Integer meal_price;
	private Integer meal_status;
	private String meal_content;
	private byte[] meal_pic;
		
	public MealVO() {
		super();
	}
	

	public String getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(String meal_id) {
		this.meal_id = meal_id;
	}
	public String getMeal_name() {
		return meal_name;
	}
	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}
	public Integer getMeal_price() {
		return meal_price;
	}
	public void setMeal_price(Integer meal_price) {
		this.meal_price = meal_price;
	}
	public Integer getMeal_status() {
		return meal_status;
	}
	public void setMeal_status(Integer meal_status) {
		this.meal_status = meal_status;
	}
	public String getMeal_content() {
		return meal_content;
	}
	public void setMeal_content(String meal_content) {
		this.meal_content = meal_content;
	}
	public byte[] getMeal_pic() {
		return meal_pic;
	}
	public void setMeal_pic(byte[] meal_pic) {
		this.meal_pic = meal_pic;
	}

	
}
