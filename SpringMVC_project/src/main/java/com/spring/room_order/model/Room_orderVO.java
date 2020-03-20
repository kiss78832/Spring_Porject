package com.spring.room_order.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Room_orderVO implements java.io.Serializable {
	private String order_id;
	private String member_id;
	private String group_id;
	private Integer order_price;
	private Integer order_status;
	private Integer payment_status;
	private Timestamp order_time;
	private Integer booking_day;
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public Integer getOrder_price() {
		return order_price;
	}

	public void setOrder_price(Integer order_price) {
		this.order_price = order_price;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public Integer getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(Integer payment_status) {
		this.payment_status = payment_status;
	}

	public Timestamp getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}

	public Integer getBooking_day() {
		return booking_day;
	}

	public void setBooking_day(Integer booking_day) {
		this.booking_day = booking_day;
	}

	
}
