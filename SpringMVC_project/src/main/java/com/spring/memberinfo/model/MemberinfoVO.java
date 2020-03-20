package com.spring.memberinfo.model;

import java.sql.Date;

public class MemberinfoVO {
 
	private String group_id;
	private String member_id;
	private String address;
	private String identity;
	private String egc_contact;
	private String egc_phone;
	private String m_name;
	private Date birthday;
	private String cellphone;
	private String m_email;
	
	
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getEgc_contact() {
		return egc_contact;
	}
	public void setEgc_contact(String egc_contact) {
		this.egc_contact = egc_contact;
	}
	public String getEgc_phone() {
		return egc_phone;
	}
	public void setEgc_phone(String egc_phone) {
		this.egc_phone = egc_phone;
	}
	
	
}
