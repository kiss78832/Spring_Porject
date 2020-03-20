package com.spring.rentodlist.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RentOdListVO implements java.io.Serializable {
	private String rent_odnum;
	private String member_id;
	private Timestamp odlist_createdate;
	private String rent_payment;
	private Date rsved_rent_date;
	private Date real_rent_date;
	private Date ex_return_date;
	private Date real_return_date;
	private Integer od_status;
	private Integer od_total_price;
	private String rent_name;
	private String rent_phone;

	public String getRent_odnum() {
		return rent_odnum;
	}

	public void setRent_odnum(String rent_odnum) {
		this.rent_odnum = rent_odnum;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Timestamp getOdlist_createdate() {
		return odlist_createdate;
	}

	public void setOdlist_createdate(Timestamp odlist_createdate) {
		this.odlist_createdate = odlist_createdate;
	}

	public String getRent_payment() {
		return rent_payment;
	}

	public void setRent_payment(String rent_payment) {
		this.rent_payment = rent_payment;
	}

	public Date getRsved_rent_date() {
		return rsved_rent_date;
	}

	public void setRsved_rent_date(Date rsved_rent_date) {
		this.rsved_rent_date = rsved_rent_date;
	}

	public Date getReal_rent_date() {
		return real_rent_date;
	}

	public void setReal_rent_date(Date real_rent_date) {
		this.real_rent_date = real_rent_date;
	}

	public Date getEx_return_date() {
		return ex_return_date;
	}

	public void setEx_return_date(Date ex_return_date) {
		this.ex_return_date = ex_return_date;
	}

	public Date getReal_return_date() {
		return real_return_date;
	}

	public void setReal_return_date(Date real_return_date) {
		this.real_return_date = real_return_date;
	}

	public Integer getOd_status() {
		return od_status;
	}

	public void setOd_status(Integer od_status) {
		this.od_status = od_status;
	}

	public Integer getOd_total_price() {
		return od_total_price;
	}

	public void setOd_total_price(Integer od_total_price) {
		this.od_total_price = od_total_price;
	}

	public String getRent_name() {
		return rent_name;
	}

	public void setRent_name(String rent_name) {
		this.rent_name = rent_name;
	}

	public String getRent_phone() {
		return rent_phone;
	}

	public void setRent_phone(String rent_phone) {
		this.rent_phone = rent_phone;
	}

}
