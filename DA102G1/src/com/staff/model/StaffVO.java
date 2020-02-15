package com.staff.model;

public class StaffVO implements java.io.Serializable{
	private String sf_id;
	private Integer sf_status;
	private String sf_account;
	private String sf_password;
	private String sf_name;
	private Integer gender;
	private String cellphone;
	private String sf_email;
	
	
	public String getSf_email() {
		return sf_email;
	}
	public void setSf_email(String sf_email) {
		this.sf_email = sf_email;
	}
	public String getSf_account() {
		return sf_account;
	}
	public void setSf_account(String sf_account) {
		this.sf_account = sf_account;
	}
	public String getSf_id() {
		return sf_id;
	}
	public void setSf_id(String sf_id) {
		this.sf_id = sf_id;
	}
	public Integer getSf_status() {
		return sf_status;
	}
	public void setSf_status(Integer sf_status) {
		this.sf_status = sf_status;
	}
	public String getSf_password() {
		return sf_password;
	}
	public void setSf_password(String sf_password) {
		this.sf_password = sf_password;
	}
	public String getSf_name() {
		return sf_name;
	}
	public void setSf_name(String sf_name) {
		this.sf_name = sf_name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	

}
