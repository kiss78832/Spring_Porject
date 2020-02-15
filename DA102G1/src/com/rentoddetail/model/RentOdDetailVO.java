package com.rentoddetail.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RentOdDetailVO implements java.io.Serializable {
	private String rent_odnum;
	private String eq_num;
	private Integer quantity;

	public String getRent_odnum() {
		return rent_odnum;
	}

	public void setRent_odnum(String rent_odnum) {
		this.rent_odnum = rent_odnum;
	}

	public String getEq_num() {
		return eq_num;
	}

	public void setEq_num(String eq_num) {
		this.eq_num = eq_num;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


}