package com.equipment.model;

import java.sql.Date;
import java.util.List;

import com.rentoddetail.model.*;



public class EquipmentVO implements java.io.Serializable {
	private String eq_num;
	private String eq_name;
	private String eq_type;
	private String eq_size;
	private String eq_brand;
	private Integer eq_price;
	private Integer eq_status;
	private byte[] eq_pic;
	private String eq_detail;
	private String type_eq_num;
	private int quantity;


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getEq_num() {
		return eq_num;
	}

	public void setEq_num(String eq_num) {
		this.eq_num = eq_num;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getEq_size() {
		return eq_size;
	}

	public void setEq_size(String eq_size) {
		this.eq_size = eq_size;
	}

	public String getEq_brand() {
		return eq_brand;
	}

	public void setEq_brand(String eq_brand) {
		this.eq_brand = eq_brand;
	}

	public Integer getEq_price() {
		return eq_price;
	}

	public void setEq_price(Integer eq_price) {
		this.eq_price = eq_price;
	}

	public Integer getEq_status() {
		return eq_status;
	}

	public void setEq_status(Integer eq_status) {
		this.eq_status = eq_status;
	}

	public byte[] getEq_pic() {
		return eq_pic;
	}

	public void setEq_pic(byte[] eq_pic) {
		this.eq_pic = eq_pic;
	}

	public String getEq_detail() {
		return eq_detail;
	}

	public void setEq_detail(String eq_detail) {
		this.eq_detail = eq_detail;
	}

	public String getType_eq_num() {
		return type_eq_num;
	}

	public void setType_eq_num(String type_eq_num) {
		this.type_eq_num = type_eq_num;
	}

	public EquipmentVO(String eq_num, String eq_name, String eq_type, String eq_size, String eq_brand, Integer eq_price,
			Integer eq_status, byte[] eq_pic, String eq_detail, String type_eq_num) {
		this.eq_num = eq_num;
		this.eq_name = eq_name;
		this.eq_type = eq_type;
		this.eq_size = eq_size;
		this.eq_brand = eq_brand;
		this.eq_price = eq_price;
		this.eq_status = eq_status;
		this.eq_pic = eq_pic;
		this.eq_detail = eq_detail;
		this.type_eq_num = type_eq_num;
	}


	public EquipmentVO() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eq_name == null) ? 0 : eq_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipmentVO other = (EquipmentVO) obj;
		if (eq_name == null) {
			if (other.eq_name != null)
				return false;
		} else if (!eq_name.equals(other.eq_name))
			return false;
		return true;
	}

}