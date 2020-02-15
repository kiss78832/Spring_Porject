
package com.equtypetotal.model;

import java.sql.Date;

public class EquTotalVO implements java.io.Serializable {
	private String type_eq_num;
	private String type_eq_name;
	private Integer type_eq_qty;
	

	public String getType_eq_num() {
		return type_eq_num;
	}
	
	public void setType_eq_num(String type_eq_num) {
		this.type_eq_num = type_eq_num;
	}
	
	public String getType_eq_name() {
		return type_eq_name;
	}

	public void setType_eq_name(String type_eq_name) {
		this.type_eq_name = type_eq_name;
	}

	public Integer getType_eq_qty() {
		return type_eq_qty;
	}

	public void setType_eq_qty(Integer type_eq_qty) {
		this.type_eq_qty = type_eq_qty;
	}



}
